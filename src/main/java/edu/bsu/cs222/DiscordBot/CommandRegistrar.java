package edu.bsu.cs222.DiscordBot;

import discord4j.common.JacksonResources;
import discord4j.core.GatewayDiscordClient;
import discord4j.discordjson.json.ApplicationCommandData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommandRegistrar {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final RestClient restClient;

    //this directory is inside the "resources" folder
    private static final String commandsDirectory = "commands/";

    //only use the guildID for testing
    private static final long guildID = 1030503369867005973L; //this is the Guild ID for the developers' Discord server

    public CommandRegistrar(RestClient restClient) {
        this.restClient = restClient;
    }

    protected void registerCommands(List<String> fileNames) throws IOException {
        final JacksonResources d4jMapper = JacksonResources.create();

        final ApplicationService applicationService = restClient.getApplicationService();
        @SuppressWarnings("ConstantConditions") final long applicationID = restClient.getApplicationId().block();

        List<ApplicationCommandRequest> commands = new ArrayList<>();
        for (String json : getCommandsJson(fileNames)) {
            ApplicationCommandRequest request = d4jMapper.getObjectMapper()
                    .readValue(json, ApplicationCommandRequest.class);

            commands.add(request);
        }
            // This is for testing
//        applicationService.bulkOverwriteGlobalApplicationCommand(applicationID, commands)
//                .doOnNext(cmd -> LOGGER.debug("Successfully registered Global command " + cmd.name()))
//                .doOnError(e -> LOGGER.error("Failed to register global commands", e))
//                .subscribe();

        // This is only for testing
        applicationService.bulkOverwriteGuildApplicationCommand(applicationID, guildID, commands)
                .doOnNext(cmd -> LOGGER.debug("Successfully registered guild command " + cmd.name()))
                .doOnError(e -> LOGGER.error("Failed to register guild commands", e))
                .subscribe();
    }

    private static List<String> getCommandsJson(List<String> fileNames) throws IOException {
        URL url = CommandRegistrar.class.getClassLoader().getResource(commandsDirectory);
        Objects.requireNonNull(url, commandsDirectory + " could not be found");

        List<String> list = new ArrayList<>();
        for (String file : fileNames) {
            String resourceFileAsString = getResourceFileAsString(commandsDirectory + file);
            list.add(Objects.requireNonNull(resourceFileAsString, "Command file not found: " + file));
        }
        return list;
    }

    private static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream(fileName)) {
            if (resourceAsStream == null) return null;

            try (InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {

                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

    @SuppressWarnings("unused") // This could be useful later
    public static void deleteGlobalCommand(GatewayDiscordClient client, Long applicationId, String commandName){
        // Get the commands from discord as a Map
        Map<String, ApplicationCommandData> discordCommands = client.getRestClient()
                .getApplicationService()
                .getGlobalApplicationCommands(applicationId)
                .collectMap(ApplicationCommandData::name)
                .block();

        // Get the ID of our command
        long commandId = Long.parseLong(String.valueOf(Objects.requireNonNull(discordCommands).get(commandName).id()));

        // Delete it
        client.getRestClient().getApplicationService()
                .deleteGlobalApplicationCommand(applicationId, commandId)
                .subscribe();
    }

    @SuppressWarnings("unused") // This could be useful later
    public static void deleteGuildCommand(GatewayDiscordClient client, Long applicationId, String commandName) {
        // Get the commands from discord as a Map
        Map<String, ApplicationCommandData> discordCommands = client.getRestClient()
                .getApplicationService()
                .getGuildApplicationCommands(applicationId, guildID)
                .collectMap(ApplicationCommandData::name)
                .block();

        // Get the ID of our command
        long commandId = Long.parseLong(String.valueOf(Objects.requireNonNull(discordCommands).get(commandName).id()));

        // Delete it
        client.getRestClient().getApplicationService()
                .deleteGuildApplicationCommand(applicationId, guildID, commandId)
                .subscribe();
    }
}

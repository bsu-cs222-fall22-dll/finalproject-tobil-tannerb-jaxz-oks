package edu.bsu.cs222.DiscordBot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.ModalSubmitInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import edu.bsu.cs222.ReadConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// Much of the Discord code is based on the sample code located at
// https://github.com/Discord4J/example-projects/tree/master/gradle-simple-bot/src/main/java/com/novamaday/d4j/gradle/simplebot

public class DiscordBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordBot.class);

    static final String token = ReadConfigProperties.getBotToken();

    public static void main(String[] args){
        final GatewayDiscordClient client = DiscordClientBuilder.create(token).build()
                .login()
                .block();

        assert client != null;
        registerCommands(client);

        client.on(ChatInputInteractionEvent.class, SlashCommandListener::handleSlashCommand)
                .then(client.onDisconnect())
                .subscribe();

        client.on(ButtonInteractionEvent.class, SlashCommandListener::handleButtons)
                .then(client.onDisconnect())
                .subscribe();
        client.on(SelectMenuInteractionEvent.class, SlashCommandListener::handleSelectMenu)
                .then(client.onDisconnect())
                .subscribe();

        client.on(ModalSubmitInteractionEvent.class, SlashCommandListener::handleModal)
                .then(client.onDisconnect())
                .block();

    }

    private static void registerCommands(GatewayDiscordClient client) {
        final List<String> commandFiles = new ArrayList<>(
                List.of(
//                        The following two command file names are only used for testing
//                        "greet.json",
//                        "ping.json",
                        "customMeme.json"
                )
        );

        CommandRegistrar commandRegistrar = new CommandRegistrar(client.getRestClient());
        try {
            commandRegistrar.registerCommands(commandFiles);
        } catch (Exception e) {
            LOGGER.error("Error trying to register slash commands", e);
        }

        deleteOldCommands(client);
    }

    private static void deleteOldCommands(GatewayDiscordClient client){

        //There's no way to avoid getting a warning for these method calls in Intellij Idea without either modifying
        // the API itself or simply suppressing it.
        @SuppressWarnings("ConstantConditions") long applicationId = client.getRestClient().getApplicationId().block();

        List<String> commandsToDelete = new ArrayList<>(
                List.of(
                        "greet",
                        "ping"
                )
        );

        for (String command : commandsToDelete) {
            try {
                CommandRegistrar.deleteGlobalCommand(client, applicationId, command);
                LOGGER.debug("Global command '" + command + "' successfully deleted.");
            } catch (NullPointerException e) {
                LOGGER.debug("Unable to delete global command '" + command + "'.\n\tIt may not have been registered globally, in which case this message can be completely ignored.\n\tError code: " + e);
            }
        }

        for (String command : commandsToDelete) {
            try {
                CommandRegistrar.deleteGuildCommand(client, applicationId, command);
                LOGGER.debug("Guild command '" + command + "' successfully deleted.");
            } catch (NullPointerException e) {
                LOGGER.debug("Unable to delete guild command '" + command + "'.\n\tIt may not have been registered to the guild, in which case this message can be completely ignored.\n\tError code: " + e);
            }
        }
    }

}

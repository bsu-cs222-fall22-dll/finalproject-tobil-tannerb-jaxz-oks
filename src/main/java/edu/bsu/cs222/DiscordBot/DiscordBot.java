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

    @SuppressWarnings("unused") private static final Long applicationId = 1037141818275541133L;

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
                        "greet.json",
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

//        The following lines are used to delete commands used for testing.

//        CommandRegistrar.deleteGlobalCommand(client, applicationId, "greet");
//        CommandRegistrar.deleteGuildCommand(client, applicationId, "greet");

  //      CommandRegistrar.deleteGlobalCommand(client, applicationId, "ping");
    //    CommandRegistrar.deleteGuildCommand(client, applicationId, "ping");
    }

}

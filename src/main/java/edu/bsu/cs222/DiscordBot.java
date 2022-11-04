package edu.bsu.cs222;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// Much of the Discord code is based on the sample code located at
// https://github.com/Discord4J/example-projects/tree/master/gradle-simple-bot/src/main/java/com/novamaday/d4j/gradle/simplebot

public class DiscordBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordBot.class);

    static final String token1 = "MTAzNzE0MTgxODI3NTU0MTEzMw.GvPcsi.";
    static final String token2 = "3VK9szYRThoPAk_ev8TFbQP0zGvL-Vd0MywI4U";

    public static void main(String[] args){
        final GatewayDiscordClient client = DiscordClientBuilder.create(token1 + token2).build()
                .login()
                .block();

        registerCommands(client);

        client.on(ChatInputInteractionEvent.class, DiscordSlashCommandListener::handle)
                .then(client.onDisconnect())
                .block();

    }

    private static void registerCommands(GatewayDiscordClient client) {
        final List<String> commandFiles = new ArrayList<>(
                List.of(
                        "greet.json",
                        "ping.json",
                        "customMeme.json"
                )
        );

        DiscordBotCommandRegistrar commandRegistrar = new DiscordBotCommandRegistrar(client.getRestClient());
        try {
            commandRegistrar.registerCommands(commandFiles);
        } catch (Exception e) {
            LOGGER.error("Error trying to register slash commands", e);
        }
    }

}

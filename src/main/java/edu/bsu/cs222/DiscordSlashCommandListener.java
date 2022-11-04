package edu.bsu.cs222;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.ArrayList;

public class DiscordSlashCommandListener {
    private final static List<DiscordSlashCommand> commands = new ArrayList<>();

    static {
        commands.add(new DiscordPingCommand());
        commands.add(new DiscordGreetCommand());
        commands.add(new DiscordMakeMemeCommand());
    }

    public static Mono<Void> handle(ChatInputInteractionEvent event) {
        return Flux.fromIterable(commands)
                .filter(command -> command.getName().equals(event.getCommandName()))
                .next()
                .flatMap(command -> command.handle(event));
    }
}

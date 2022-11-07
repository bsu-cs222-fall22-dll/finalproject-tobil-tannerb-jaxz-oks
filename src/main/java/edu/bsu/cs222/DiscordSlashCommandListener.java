package edu.bsu.cs222;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.ModalSubmitInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
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

    public static Mono<Void> handleSlashCommand(ChatInputInteractionEvent event) {
        return Flux.fromIterable(commands)
                .filter(command -> command.getName().equals(event.getCommandName()))
                .next()
                .flatMap(command -> command.handle(event));
    }

    public static Mono<Void> handleSelectMenu(SelectMenuInteractionEvent event) {
        if (event.getCustomId().equals("meme-selection")) {
            return DiscordMakeMemeCommand.handleSelection(event);
        } else {
            return Mono.empty();
        }
    }

    public static Mono<Void> handleModal(ModalSubmitInteractionEvent event) {
        if (event.getCustomId().equals("text-boxes")) {
            return DiscordMakeMemeCommand.handleModal(event);
        } else {
            return Mono.empty();
        }
    }
}

package edu.bsu.cs222.DiscordBot;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.ModalSubmitInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.ArrayList;

public class SlashCommandListener {
    private final static List<SlashCommand> commands = new ArrayList<>();

    static {
        commands.add(new PingCommand());
        commands.add(new GreetCommand());
        commands.add(new MakeMemeCommand());
    }

    //Listen for slash commands
    public static Mono<Void> handleSlashCommand(ChatInputInteractionEvent event) {
        return Flux.fromIterable(commands)
                .filter(command -> command.getName().equals(event.getCommandName()))
                .next()
                .flatMap(command -> command.handle(event));

    }

    //Listen for dropdown menu selections
    public static Mono<Void> handleSelectMenu(SelectMenuInteractionEvent event) {
        if (event.getCustomId().equals("meme-selection")) {
            return MakeMemeCommand.handleSelection(event);
        }else {
            return Mono.empty();
        }
    }

    //Listen for modal submissions
    public static Mono<Void> handleModal(ModalSubmitInteractionEvent event) {
        if (event.getCustomId().equals("text-boxes")) {
            return MakeMemeCommand.handleModal(event);
        } else {
            return Mono.empty();
        }
    }

    //listen for button clicks
    public static Mono<Void> handleButtons(ButtonInteractionEvent event) {
        if (event.getCustomId().equals("choose")) {
            return MakeMemeCommand.handleChooseButton(event);
        } else if (event.getCustomId().equals("showmore")) {
            return MakeMemeCommand.handleShowMoreOptionsButton(event);
        } else {
            return Mono.empty();
        }
    }
}

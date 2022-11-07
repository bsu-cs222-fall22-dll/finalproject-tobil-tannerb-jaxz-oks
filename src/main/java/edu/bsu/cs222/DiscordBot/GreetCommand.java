package edu.bsu.cs222.DiscordBot;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import reactor.core.publisher.Mono;

public class GreetCommand implements SlashCommand {
    @Override
    public String getName() {
        return "greet";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        @SuppressWarnings("OptionalGetWithoutIsPresent") String name = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        return event.reply()
                .withContent("Hello, " + name);
    }
}

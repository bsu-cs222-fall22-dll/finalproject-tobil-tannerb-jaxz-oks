package edu.bsu.cs222;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import reactor.core.publisher.Mono;

public class DiscordGreetCommand implements DiscordSlashCommand{
    @Override
    public String getName() {
        return "greet";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String name = event.getOption("name")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        return event.reply()
                .withContent("Hello, " + name);
    }
}

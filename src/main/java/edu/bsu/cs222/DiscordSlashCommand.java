package edu.bsu.cs222;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Mono;

public interface DiscordSlashCommand {

    String getName();

    Mono<Void> handle(ChatInputInteractionEvent event);
}

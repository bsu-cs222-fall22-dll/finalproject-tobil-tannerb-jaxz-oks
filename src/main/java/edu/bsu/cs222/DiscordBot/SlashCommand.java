package edu.bsu.cs222.DiscordBot;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.apache.hc.core5.http.ParseException;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface SlashCommand {

    String getName();

    Mono<Void> handle(ChatInputInteractionEvent event) throws IOException, ParseException;
}

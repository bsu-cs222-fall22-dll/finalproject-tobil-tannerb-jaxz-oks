package edu.bsu.cs222;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.component.SelectMenu;
import reactor.core.publisher.Mono;

@SuppressWarnings("unused") //this class is a work-in-progress
public class DiscordMakeMemeCommand implements DiscordSlashCommand{
    @Override
    public String getName() {
        return "customMeme";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply()
                .withContent("This is a work in progress. Someday, this is where the meme-making logic will happen.");
    }

    private SelectMenu makeMemeMenu(){
        // TODO: 11/3/2022
        // create a select menu of the first 20 meme templates
        return null;
    }
}

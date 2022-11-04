package edu.bsu.cs222;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.SelectMenu;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class DiscordMakeMemeCommand implements DiscordSlashCommand{
    @Override
    public String getName() {
        return "makememe";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply()
                .withComponents(ActionRow.of(memeMenu()));
    }

    private SelectMenu memeMenu(){

        List<Template> templateList = JSONParser.getTemplateList().subList(0,20);

        List<SelectMenu.Option> optionList = new ArrayList<>();

        for (Template template : templateList) {
            SelectMenu.Option option = SelectMenu.Option.of(template.getMemeName(), template.getMemeID());
            optionList.add(option);
        }

        return SelectMenu.of("meme-selection", optionList);
    }
}

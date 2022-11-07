package edu.bsu.cs222;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.ModalSubmitInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.object.component.*;
import discord4j.core.spec.InteractionPresentModalSpec;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class DiscordMakeMemeCommand implements DiscordSlashCommand{

    private static Template template;
    private static Customization customizedMeme;
    @Override
    public String getName() {
        return "makememe";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return chooseTemplate(event);
    }

    private Mono<Void> chooseTemplate(ChatInputInteractionEvent event){
        return event.reply()
                .withEphemeral(true)
                .withComponents(ActionRow.of(templateMenu()));
    }

    private SelectMenu templateMenu(){

        List<Template> templateList = JSONParser.getTemplateList().subList(0,20);

        List<SelectMenu.Option> optionList = new ArrayList<>();

        for (Template template : templateList) {
            SelectMenu.Option option = SelectMenu.Option.of(template.getMemeName(), template.getMemeID());
            optionList.add(option);
        }

        return SelectMenu.of("meme-selection", optionList);
    }

    private static InteractionPresentModalSpec getText(){
        List<LayoutComponent> actionRowList = new ArrayList<>();

        for (int i = 0; i < template.getBoxCount(); i++) {
            ActionRow actionRow = ActionRow.of(TextInput.small("textbox" + i, "Text box " + (i + 1) + ":").required());
            actionRowList.add(actionRow);
        }

        return InteractionPresentModalSpec.builder()
                .title("Type the text you want to see on your meme")
                .customId("text-boxes")
                .addAllComponents(actionRowList)
                .build();
    }

    public static Mono<Void> handleSelection(SelectMenuInteractionEvent event) {
        String templateID = event.getValues().get(0);
        template = MemeAPIManager.getTemplateByID(templateID);
        customizedMeme = new Customization(template);
        return event.presentModal(getText());
    }

    public static Mono<Void> handleModal(ModalSubmitInteractionEvent event) {
        for (TextInput input : event.getComponents(TextInput.class)) {
            customizedMeme.addText(input.getValue().orElse(""));
        }
        try {
            String url = customizedMeme.getCustomMemeURL();
            return  event.reply().withContent(url);
        } catch (Exception e) {
            System.out.println("Modal Error: " + e);
            return Mono.empty();
        }
    }
}

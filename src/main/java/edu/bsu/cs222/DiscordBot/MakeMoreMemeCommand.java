package edu.bsu.cs222.DiscordBot;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.ModalSubmitInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.LayoutComponent;
import discord4j.core.object.component.SelectMenu;
import discord4j.core.object.component.TextInput;
import discord4j.core.spec.InteractionPresentModalSpec;
import edu.bsu.cs222.Customization;
import edu.bsu.cs222.JSONParser;
import edu.bsu.cs222.MemeAPIManager;
import edu.bsu.cs222.Template;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class MakeMoreMemeCommand implements SlashCommand{
    private static Template template;
    private static Customization customizedMeme;

    @Override
    public String getName() {
        return "makemorememes";
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

        List<Template> templateList = JSONParser.getTemplateList().subList(0,100);

        List<SelectMenu.Option> optionList = new ArrayList<>();

        for (Template template : templateList) {
            SelectMenu.Option option = SelectMenu.Option.of(template.getMemeName(), template.getMemeID());
            optionList.add(option);
        }
        return SelectMenu.of("more-meme-selection", optionList);
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

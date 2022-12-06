package edu.bsu.cs222.DiscordBot;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.ModalSubmitInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.object.component.*;
import discord4j.core.spec.InteractionPresentModalSpec;
import edu.bsu.cs222.Customization;
import edu.bsu.cs222.JSONParser;
import edu.bsu.cs222.MemeAPIManager;
import edu.bsu.cs222.Template;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

public class MakeMemeCommand implements SlashCommand {

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
                .withComponents(ActionRow.of(templateMenu(20)));
    }

    private SelectMenu templateMenu(int numTemplates){

        List<Template> templateList = JSONParser.getTemplateList().subList(0,numTemplates);

        List<SelectMenu.Option> optionList = new ArrayList<>();

        for (Template template : templateList) {
            SelectMenu.Option option = SelectMenu.Option.of(template.getMemeName(), template.getMemeID());
            optionList.add(option);
        }
        SelectMenu.Option randomOption = SelectMenu.Option.of("Random", "-1");
        optionList.add(randomOption);
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
        if (templateID .equals("-1")) {
            templateID = MemeAPIManager.getRandomMeme();
        }
        template = MemeAPIManager.getTemplateByID(templateID);
        customizedMeme = new Customization(template);
        return showPreview(event);
    }

    public static Mono<Void> showPreview (SelectMenuInteractionEvent event) {
        Button chooseTemplateButton = Button.primary("choose", "use this template");
        return event.reply()
                .withEphemeral(true)
                .withContent(MemeAPIManager.getMemeNumberTemplate(template))
                .withComponents(ActionRow.of(chooseTemplateButton));
//                .withContent("Use the previous select menu to choose a different template.");

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

    public static Mono<Void> handleChooseButton(ButtonInteractionEvent event) {
        return event.presentModal(getText());
    }

//    public static Mono<Void> handleDifferentButton(ButtonInteractionEvent event) {
//        return event.reply()
//                .withEphemeral(true)
//                .withContent("Use the previous select menu to choose a different template.");
//    }
}

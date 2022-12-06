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
import org.apache.hc.core5.http.ParseException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MakeMemeCommand implements SlashCommand {

    private static Template template;
    private static Customization customizedMeme;
    private static int templateListPage=0;

    @Override
    public String getName() {
        return "makememe";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) throws IOException, ParseException {
        return chooseTemplate(event);
    }

    private static Mono<Void> chooseTemplate(ChatInputInteractionEvent event) throws IOException, ParseException {
        return event.reply()
                .withEphemeral(true)
                .withComponents(ActionRow.of(templateMenu()));
    }

    private static SelectMenu templateMenu() throws IOException, ParseException {
        List<Template> fullTemplateList = JSONParser.getTemplateList();
        int sublistMinIndex = templateListPage * 20;
        int sublistMaxIndex = sublistMinIndex + 20;

        if (sublistMaxIndex >= fullTemplateList.size()) {
            sublistMaxIndex = fullTemplateList.size();
            templateListPage = -1;
        }

        List<Template> shortTemplateList = fullTemplateList.subList(sublistMinIndex,sublistMaxIndex);

        List<SelectMenu.Option> optionList = new ArrayList<>();

        for (Template template : shortTemplateList) {
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
        Button chooseTemplate = Button.primary("choose", "Use this template");
        Button showMoreOptionsButton = Button.secondary("showmore", "Show more templates");
        return event.reply()
                .withEphemeral(true)
                .withContent(MemeAPIManager.getMemeNumberTemplate(template))
                .withComponents(ActionRow.of(chooseTemplate, showMoreOptionsButton));

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

    public static Mono<Void> handleShowMoreOptionsButton(ButtonInteractionEvent event) {
        templateListPage += 1;
        try {
            return event.reply()
                    .withEphemeral(true)
                    .withComponents(ActionRow.of(templateMenu()));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

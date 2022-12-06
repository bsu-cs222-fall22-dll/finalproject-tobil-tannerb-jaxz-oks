package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CustomizationTest {
    //Only checks to make sure that it is an url because the url changes everytime a new custom meme is created
    @Test
    public void customMemeTest() {
        List<Template> templateList = JSONParser.getTemplateListForTest();
        Customization customization = new Customization(templateList.get(0));
        customization.addText("making memes with mematic");
        customization.addText("writing your own code to generate custom memes");
        String url = customization.getCustomMemeURL();
        Assertions.assertNotEquals(url.substring(0,4), "https");
    }
}
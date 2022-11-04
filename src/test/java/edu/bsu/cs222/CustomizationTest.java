package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

public class CustomizationTest {
    @Test
    public void customMemeTest() throws IOException, ParseException {
        List<Template> templateList = JSONParser.getTemplateList();
        Customization customization = new Customization(templateList.get(0));
        customization.addText("making memes with mematic");
        customization.addText("writing your own code to generate custom memes");
        String url = customization.getCustomMemeURL();
        System.out.println(url);
    }
}
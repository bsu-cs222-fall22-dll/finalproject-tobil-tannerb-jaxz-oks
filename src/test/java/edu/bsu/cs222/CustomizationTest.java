package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class CustomizationTest {
    @Test
    public void customMemeTest1() throws IOException, ParseException {
        Customization customization = new Customization("Drake Hot Line Bling");
        customization.addText("making memes with mematic");
        customization.addText("writing your own code to generate custom memes");
        String url = customization.getCustomMemeURL();
        System.out.println(url);
    }

}

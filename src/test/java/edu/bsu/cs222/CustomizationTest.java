package edu.bsu.cs222;

import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CustomizationTest {
    @Test
    //Test will fail because the url changes everytime however they are the same meme so thats a win
    public void customMemeTest1() throws IOException, ParseException {
        Customization customization = new Customization();
        String url = customization.customizeMeme("Drake Hotline Bling");
        Assertions.assertEquals("https://i.imgflip.com/6xzrae.jpg", url);
    }

}

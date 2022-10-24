package edu.bsu.cs222;

import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomizationTest {
    @Test
    public void customMemeTest1() throws IOException, ParseException {
        Customization customization = new Customization();
        ArrayList<String> text = new ArrayList<>();
        text.add("making memes with mematic");
        text.add("writing your own code to generate custom memes");
        String url = customization.customizeMeme("Drake Hotline Bling", text);
        System.out.println(url);
    }

}

package edu.bsu.cs222;

import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CustomizationTest {
    @Test
    public void customMemeTest1() throws IOException, ParseException {
        Customization customization = new Customization();
        String url = customization.customizeMeme("Drake Hotline Bling");
        System.out.println(url);
    }

}

package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class UITest {
    @Test
    public void testHeader(){
        String header = "Welcome to the Custom Meme Generator! \nWhich meme template do you want to choose?";
        String headerUI = UI.getHeader();
        Assertions.assertEquals(header, headerUI);
    }
}

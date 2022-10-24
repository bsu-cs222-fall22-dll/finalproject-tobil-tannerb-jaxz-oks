package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Customization customizedMeme = new Customization();
        System.out.println("Please enter the name of the meme you wish to select: \n");
        String memeName = scanner.nextLine();
        Template template = new Template(memeName);
        int boxCount = template.getBoxCount();
        ArrayList<String> memeText = new ArrayList<String>();

        for(int i = 1; i <= boxCount; i++){
            System.out.println("Please enter the text for text box " + i);
            String input = scanner.nextLine();
            memeText.add(input);
        }

        String memeUrl = customizedMeme.customizeMeme(memeName, memeText);

        System.out.println(memeUrl);
    }
}

package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) throws IOException, ParseException {
        TemplateFormatter templateFormatter = new TemplateFormatter();
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        Scanner scanner = new Scanner(System.in);
        Customization customizedMeme = new Customization();
        templateFormatter.formatTop20();
        System.out.println("Please enter the number of the meme you wish to select: ");
        int index = Integer.parseInt(scanner.nextLine());
        //scanner.close();
        String memeName = memeAPIManager.getName(index - 1);

        String memeUrl = customizedMeme.customizeMeme(memeName);

        System.out.println(memeUrl);
    }
}

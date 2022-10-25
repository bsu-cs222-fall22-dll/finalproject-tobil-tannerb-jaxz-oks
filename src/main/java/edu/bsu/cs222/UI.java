package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

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
        if (index > 20 || index <= 0){
            System.out.println("number is not in the range");

        }else{
            String memeName = MemeAPIManager.getName(index - 1);
            Customization customizedMeme = new Customization(memeName);
            int memeBoxCount = customizedMeme.getMemeBoxCount();

            System.out.println("You will have " + memeBoxCount + " text boxes to fill.");
            for(int i = 1; i <= memeBoxCount; i++){
                System.out.println("Please enter the text for text box " + i);
                customizedMeme.addText(scanner.nextLine());
            }

            scanner.close();

//            String memeUrl = customizedMeme.getCustomMemeURL();

            System.out.println(customizedMeme.getCustomMemeURL());
    }}
}

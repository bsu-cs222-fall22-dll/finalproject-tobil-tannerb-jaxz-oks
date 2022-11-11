package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) throws IOException, ParseException {
        List<Template> templateList = JSONParser.getTemplateList();
        MemeAPIManager memeAPIManager = new MemeAPIManager();

        Scanner scanner = new Scanner(System.in);

        System.out.println(TemplateFormatter.formatTop20(templateList));
        System.out.println("Please enter the number of the meme you wish to select: ");
        int index = Integer.parseInt(scanner.nextLine());

        if (index > 21 || index <= 0) {
            System.out.println("number is not in the range");

        }else{
            if (index == 21){
                index = memeAPIManager.getTemplateByName("Random");
            }
            Template template = templateList.get(index - 1);
            Customization customizedMeme = new Customization(template);

            int memeBoxCount = template.getBoxCount();

            System.out.println("You will have " + memeBoxCount + " text boxes to fill.");

            for(int i = 1; i <= memeBoxCount; i++){
                System.out.println("Please enter the text for text box " + i);
                customizedMeme.addText(scanner.nextLine());
            }
            System.out.println(customizedMeme.getCustomMemeURL());
        }
        scanner.close();
    }
}

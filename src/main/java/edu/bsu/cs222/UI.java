package edu.bsu.cs222;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        List<Template> templateList = JSONParser.getTemplateList();
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        int index = 101;

        Scanner scanner = new Scanner(System.in);
        System.out.println("(1) Pick form popular meme templates \n(2) Search for meme template");
        String line = scanner.nextLine().toLowerCase(Locale.ROOT);
        if(line.equals("1")) {
           System.out.println(TemplateFormatter.formatTop20(templateList));
           System.out.println("Please enter the number of the meme you wish to select: ");
            index = Integer.parseInt(scanner.nextLine());

           if (index > 21 || index <= 0) {
               System.out.println("number is not in the range");

           } else {
               if (index == 21) {
                   index = memeAPIManager.getTemplateByName("Random");
               }
           }
       }
        if(line.equals("2")){
            TemplateFormatter.formatTop100(templateList);
            System.out.println("Enter in the name of the meme you want. (You have to enter in the name exactly or it won't work)");
            String name = scanner.nextLine();
            for (int i = 1; i <= templateList.size(); i++) {
                Template search  = templateList.get(i-1);
                if (search.getMemeName().equals(name)) {
                    index = i-1;
                    break;
                }

            }
        }
        else {
            System.out.println("Entered wrong number");
        }
            if (index == 101){
                System.out.println("no meme was found");
            }
            else {
            Template template = templateList.get(index);
            Customization customizedMeme = new Customization(template);
            System.out.println(MemeAPIManager.getMemeNumberTemplate(template));

            int memeBoxCount = template.getBoxCount();

            System.out.println("You will have " + memeBoxCount + " text boxes to fill.");

            for(int i = 1; i <= memeBoxCount; i++){
                System.out.println("Please enter the text for text box " + i);
                customizedMeme.addText(scanner.nextLine());
            }
            System.out.println(customizedMeme.getCustomMemeURL());

        scanner.close();
    }}
}


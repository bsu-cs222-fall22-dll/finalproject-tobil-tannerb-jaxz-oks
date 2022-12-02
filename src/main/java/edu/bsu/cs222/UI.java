package edu.bsu.cs222;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        List<Template> templateList = JSONParser.getTemplateList();
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        Template template = templateList.get(0);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to use the top 20 or search by name? (enter in top20 or search exactly)");
        String line = scanner.nextLine().toLowerCase(Locale.ROOT);
        if(line.equals("top20")) {
           System.out.println(TemplateFormatter.formatTop20(templateList));
           System.out.println("Please enter the number of the meme you wish to select: ");
           int index = Integer.parseInt(scanner.nextLine());

           if (index > 21 || index <= 0) {
               System.out.println("number is not in the range");

           } else {
               if (index == 21) {
                   index = memeAPIManager.getTemplateByName("Random");
               }
               template = templateList.get(index - 1);
           }
       }
        if(line.equals("search")){
            TemplateFormatter.formatTop100(templateList);
            System.out.println("Enter in the name of the meme you want");
            String name = scanner.nextLine();
            for (int i = 1; i < templateList.size(); i++) {
                Template search  = templateList.get(i);
                if (search.getMemeName().equals(name)) {
                    template = templateList.get(i);
                    break;
                }

            }
        }
        else {
            System.out.println("Error in response");
        }
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
    }
}


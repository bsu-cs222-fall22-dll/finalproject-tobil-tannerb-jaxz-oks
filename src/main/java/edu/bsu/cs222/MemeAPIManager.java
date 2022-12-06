package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MemeAPIManager {
    private static final List<Template> templateList;

    static {
        try {
            templateList = JSONParser.getTemplateList();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Random random = new Random();

    public static String getMemeNumberTemplate(Template template) {
        Customization custom = new Customization(template);
        for(int i = 1; i <= template.getBoxCount(); i++){
            custom.addText(String.valueOf(i));
        }

        return (custom.getCustomMemeURL());
    }


    public int getTemplateByName(String name){
        int memeIndex = -1;
        if (name.equals("Random")){
            memeIndex = random.nextInt(20);
            return memeIndex;
        } else {

            for (Template template : templateList) {
                if (template.getMemeName().equals(name)){
                    memeIndex = template.getMemeRank() - 1;
                }
            }
        }


        return memeIndex;
    }



    public static Template getTemplateByID(String memeID) {

        for (Template template : templateList) {
            if (template.getMemeID().equals(memeID)){
                return template;
            }
        }

        return templateList.get(0);
    }

    public static String getRandomMeme(){
        Template template;
        int memeIndex = random.nextInt(20);
        template = templateList.get(memeIndex);
        return template.getMemeID();

    }



}
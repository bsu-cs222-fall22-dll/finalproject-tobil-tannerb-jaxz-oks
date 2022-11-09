package edu.bsu.cs222;

import java.util.List;
import java.util.Random;

public class MemeAPIManager {
    private static final List<Template> templateList = JSONParser.getTemplateList();
    public int getTemplateByName(String name){
        Random random = new Random();
        int memeIndex = 0;
        if (name == "Random"){
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

}
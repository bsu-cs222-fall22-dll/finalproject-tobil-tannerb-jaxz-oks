package edu.bsu.cs222;

import java.util.List;

public class MemeAPIManager {
    private static final List<Template> templateList = JSONParser.getTemplateList();
    public int getTemplateByName(String name){

        int memeIndex = 0;

        for (Template template : templateList) {
            if (template.getMemeName().equals(name)){
                memeIndex = template.getMemeRank() - 1;
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
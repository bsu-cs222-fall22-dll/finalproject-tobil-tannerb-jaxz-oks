package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONParser {
    public static List<Template> getTemplateList(){
        List<Template> templateList = new ArrayList<>();

        String jsonString = JSONFetcher.getLocalJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$.data.memes.*");

        int rank = 1;
        for (Object memeJson : jsonArray) {
            @SuppressWarnings("rawtypes") HashMap memeMap = (HashMap) memeJson;
            //I don't know how to set this up in a way that doesn't create a warning, so I'm ignoring it --OK
            Template template = new Template();

            template.setMemeRank(rank);
            template.setMemeName(memeMap.get("name").toString());
            template.setMemeID(memeMap.get("id").toString());
            template.setTemplateURL(memeMap.get("url").toString());
            template.setBoxCount((Integer) memeMap.get("box_count"));
            templateList.add(template);

            rank++;
        }

        return templateList;
    }
}
package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;

public class MemeAPIManager {
    private static final JSONParser jsonParser = new JSONParser();
    public int getTemplateByName(String name){
        String jsonString = jsonParser.getLocalJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..name");
        int memeIndex = 0;
        for(int i = 0; i < jsonArray.size(); i++) {
            String testName = jsonArray.get(i).toString();
            if(name.equals(testName)) {
                memeIndex = (i);
            }
        }
        return memeIndex;
    }

    public String getBoxCount(int memeIndex){
        String jsonString = jsonParser.getLocalJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..box_count");
        String boxCount = null;
        for(int i = 0; i < jsonArray.size(); i++){
            if(i == memeIndex){
                boxCount = jsonArray.get(i).toString();
                break;
            }
        }
        return boxCount;
    }
    public String getID(int memeIndex){
        String jsonString = jsonParser.getLocalJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..id");
        String id = null;
        for(int i = 0; i < jsonArray.size(); i++){
            if (i == memeIndex) {
                id = jsonArray.get(i).toString();
                break;
            } else {
                id = "Meme is out of range";
            }
         }
        return id;
    }
    public String getURL(int index){
        String jsonString = jsonParser.getLocalJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..url");
        String url = null;
        for (int i = 0; i < jsonArray.size(); i++){
            if(i == index) {
                url = jsonArray.get(i).toString();
                break;
            } else {
                url = "Meme is out of range";
            }
        }
        return url;
    }
    //Only used for Template Formatter
    public static String getName(int index){
        String jsonString = jsonParser.getLocalJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..name");
        String name = null;
        for (int i = 0; i < jsonArray.size(); i++){
            if(i == index){
                name = jsonArray.get(i).toString();
                break;
            }
        }
        return name;
    }
}
package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class MemeAPIManager {
    private JSONParser jsonParser = new JSONParser();
    public int getTemplateByName(String name) throws IOException, ParseException {
        String jsonString = jsonParser.getJSONString();
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

    public String getBoxCount(int memeIndex) throws IOException, ParseException {
        String jsonString = jsonParser.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..box_count");
        String boxCount = null;
        for(int i = 0; i < jsonArray.size(); i++){
            boxCount = jsonArray.get(i).toString();
        }

        return boxCount;

    }
    public String getID(int memeIndex) throws IOException, ParseException {
        String jsonString = jsonParser.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..id");
        String id = null;
        for(int i = 0; i < jsonArray.size(); i++){
            id = jsonArray.get(i).toString();
         }

        return id;

    }
    public String getURL(int index)throws IOException, ParseException{
        String jsonString = jsonParser.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..url");
        String url = null;
        for (int i = 0; i < jsonArray.size(); i++){
            url = jsonArray.get(i).toString();
        }
        return url;
    }
    //Only used for Template Formatter
    public String getName(int index)throws IOException, ParseException{
        String jsonString = jsonParser.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..name");
        String name = null;
        for (int i = 0; i < jsonArray.size(); i++){
            if(i == index){
                name = jsonArray.get(i).toString();
                break;
            } else {
                name = "Meme does not exist";
            }

        }
        return name;
    }


}

package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class MemeAPIManager {
    private JSONParser jsonParser = new JSONParser();
    public String getTemplateByName(String name) throws IOException, ParseException {
        String jsonString = jsonParser.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..name");
        String memeIndex = "0";
        //System.out.println(name);
        for(int i = 0; i < jsonArray.size(); i++) {
            String testName = jsonArray.get(i).toString();
            if(name.equals(testName)){
                memeIndex = Integer.toString(i);
                break;
            } else {
                memeIndex = "Meme Does Not Exist";
            }
        }
        return memeIndex;
    }

    public String getBoxCount(int memeIndex) throws IOException, ParseException {
        String jsonString = jsonParser.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..box_count");
        String boxCount = null;
        for(int i = 0; i < jsonArray.size(); i++){
            if(i == memeIndex){
                boxCount = jsonArray.get(i).toString();
                break;
            } else {
                boxCount = "Meme is out of range";
            }
        }

        return boxCount;

    }
    public String getID(int memeIndex) throws IOException, ParseException {
        String jsonString = jsonParser.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..id");
        String id = null;
        for(int i = 0; i < jsonArray.size(); i++){
            if(i == memeIndex){
                id = jsonArray.get(i).toString();
                break;
            } else {
                id = "Meme is out of range";
            }
         }

        return id;

    }
    public String getURL(int index)throws IOException, ParseException{
        String jsonString = jsonParser.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..url");
        String url = null;
        for (int i = 0; i < jsonArray.size(); i++){
            if(i == index){
                url = jsonArray.get(i).toString();
                break;
            } else {
                url = "Meme is out of range";
            }

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

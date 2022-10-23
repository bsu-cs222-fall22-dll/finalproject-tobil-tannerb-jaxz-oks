package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class DataFetcher {
    public String getTemplateByName(String name) throws IOException, ParseException {
        JSONStorage jsonStorage = new JSONStorage();
        String jsonString = jsonStorage.getJSONString();
        JSONArray jsonArray = JsonPath.read(jsonString, "$..name");
        String memeIndex = "0";
        System.out.println(name);
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
        JSONStorage jsonStorage = new JSONStorage();
        String jsonString = jsonStorage.getJSONString();
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

}

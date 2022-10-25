package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class JSONParser {
    public ArrayList<String> getMemeList() throws IOException, ParseException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.imgflip.com/get_memes");

        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        String jsonString = EntityUtils.toString(entity);

        JSONArray jsonArray = JsonPath.read(jsonString, "$..name");
        ArrayList<String> memeList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String meme = (jsonArray.get(i).toString());
            memeList.add(meme);
        }
        EntityUtils.consume(entity);
        return memeList;
    }

    public String getJSONString() throws IOException, ParseException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.imgflip.com/get_memes");

        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }
}

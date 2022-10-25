package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Customization {

    private String username = "edu.bsu.cs22.finalproject";
    private String password = "rZxJQmKsSht7eZk";
    private String memeID = "181913649";
    private int memeBoxCount;
    private Template template;
    private final List<String> memeText = new ArrayList<>();
    private final List<NameValuePair> parameters = new ArrayList<>();
    public Customization(String memeName) throws IOException, ParseException {
        template = new Template(memeName);
        memeID = template.getMemeID();
        memeBoxCount = template.getBoxCount();
        System.out.println(memeName + "   " + memeID);
    }

    public String getCustomMemeURL() throws IOException, ParseException {
        setParameters();
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.imgflip.com/caption_image");
        httpPost.setEntity(new UrlEncodedFormEntity(parameters));
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String url = decodeJSON(entity);
        EntityUtils.consume(entity);
        return url;
    }

    private String decodeJSON(HttpEntity encodedJSON) throws IOException, ParseException {
        String jsonString = EntityUtils.toString(encodedJSON);
        HashMap<String, String> jsonMap = JsonPath.read(jsonString, "$.data");
        return jsonMap.get("url");
    }

    @SuppressWarnings("unused")     //We plan to use this in future iterations.
    public void setUsername(String user){
        username = user;
    }

    @SuppressWarnings("unused")     //Again, we plan to use this in future iterations.
    public void setPassword(String pass){
        password = pass;
    }

    public void addText(String newText){
        memeText.add(newText);
    }

    public int getMemeBoxCount(){
        return memeBoxCount;
    }

    public void setParameters(){
        parameters.add(new BasicNameValuePair("template_id", memeID));
        parameters.add(new BasicNameValuePair("username", username));
        parameters.add(new BasicNameValuePair("password", password));
        for(int i = 0; i < memeBoxCount; i++ ){
            parameters.add(new BasicNameValuePair("boxes[" + i + "][text]", memeText.get(i)));
        }
    }

    @SuppressWarnings("unused")     //It's a nice method to keep in case we need it in future iterations.
    public List<NameValuePair> getParameters(){
        return parameters;
    }
}


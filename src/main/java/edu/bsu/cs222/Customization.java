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
    private List<String> memeText = new ArrayList<>();;
    private List<NameValuePair> parameters = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public String customizeMeme(String memeName) throws IOException, ParseException {
        Template template = new Template(memeName);

        memeID = template.getMemeID();
        memeBoxCount = template.getBoxCount();

        setParameters();

        String url = getCustomMemeURL();

        return url;
    }

    private String getCustomMemeURL() throws IOException, ParseException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.imgflip.com/caption_image");
        httpPost.setEntity(new UrlEncodedFormEntity(parameters));
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
        //System.out.println(response.getCode() + "     " + response.getReasonPhrase());
        HttpEntity entity = response.getEntity();
        String url = decodeJSON(entity);
        EntityUtils.consume(entity);
        return url;
    }

    private String decodeJSON(HttpEntity encodedJSON) throws IOException, ParseException {
        String jsonString = EntityUtils.toString(encodedJSON);
        System.out.println(jsonString);
        HashMap<String, String> jsonMap = JsonPath.read(jsonString, "$.data");
        //System.out.println(jsonMap.get("page_url"));
        String url = jsonMap.get("url");
        return url;
    }

    public void setUsername(String user){
        username = user;
    }

    public void setPassword(String pass){
        password = pass;
    }

    public void addText(String newText){
        memeText.add(newText);
    }

    public void setParameters(){
        parameters.add(new BasicNameValuePair("template_id", memeID));
        parameters.add(new BasicNameValuePair("username", username));
        parameters.add(new BasicNameValuePair("password", password));
        for(int i = 0; i < memeBoxCount; i++ ){
            parameters.add(new BasicNameValuePair("boxes[" + i + "][text]", memeText.get(i)));
        }
    }

    public List<NameValuePair> getParameters(){
        return parameters;
    }


    public void setMemeList(){
        for(int i = 1; i <= memeBoxCount; i++){
            System.out.println("Please enter the text for text box " + i);
            String input = scanner.nextLine();
            memeText.add(input);
            }

    }
}


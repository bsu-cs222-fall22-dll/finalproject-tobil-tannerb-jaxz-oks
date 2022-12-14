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

public class Customization {

    private final String username = ReadConfigProperties.getImgflipUser();
    private final String password = ReadConfigProperties.getImgflipPassword();
    private final String memeID;
    private final int memeBoxCount;
    private final List<String> memeText = new ArrayList<>();
    private final List<NameValuePair> parameters = new ArrayList<>();

    public Customization(Template template){
        String memeName = template.getMemeName();
        memeID = template.getMemeID();
        memeBoxCount = template.getBoxCount();
        System.out.println(memeName + "   " + memeID);
    }

    public String getCustomMemeURL() {
        setParameters();
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.imgflip.com/caption_image");
        httpPost.setEntity(new UrlEncodedFormEntity(parameters));
        CloseableHttpResponse response;
        String url = "Something went wrong getting the URL: ";
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            url = decodeJSON(entity);
            EntityUtils.consume(entity);
        } catch (Exception e) {
            url += e;
            System.out.println(url);
        }

        return url;
    }

    private String decodeJSON(HttpEntity encodedJSON) throws IOException, ParseException {
        String jsonString = EntityUtils.toString(encodedJSON);
        HashMap<String, String> jsonMap = JsonPath.read(jsonString, "$.data");
        return jsonMap.get("url");
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

}
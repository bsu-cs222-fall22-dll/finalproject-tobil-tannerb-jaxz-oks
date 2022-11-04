package edu.bsu.cs222;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LearningTest {


    //This was written referencing this Stack Exchange thread: https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java
    //and also this Apache quick start guide: https://hc.apache.org/httpcomponents-client-5.1.x/quickstart.html#
    @Test
    public void makeCustomMeme_test() throws IOException, ProtocolException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.imgflip.com/caption_image");

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("template_id", "61579"));
        parameters.add(new BasicNameValuePair("username", "edu.bsu.cs22.finalproject"));
        parameters.add(new BasicNameValuePair("password", "rZxJQmKsSht7eZk"));
        parameters.add(new BasicNameValuePair("text0", "one does not simply"));
        parameters.add(new BasicNameValuePair("text1", "make a custom meme with ImgFlip"));

        httpPost.setEntity(new UrlEncodedFormEntity(parameters));

        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
        System.out.println(response.getCode() + "     " + response.getReasonPhrase());
        HttpEntity entity = response.getEntity();
        System.out.println(entity);
        decode(entity);
        EntityUtils.consume(entity);

    }

    @Test
    public void makeMemeWithBoxes() throws IOException, ParseException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.imgflip.com/caption_image");

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("template_id", "181913649"));
        parameters.add(new BasicNameValuePair("username", "edu.bsu.cs22.finalproject"));
        parameters.add(new BasicNameValuePair("password", "rZxJQmKsSht7eZk"));
        parameters.add(new BasicNameValuePair("boxes[0][text]", "making memes with mematic"));
        parameters.add(new BasicNameValuePair("boxes[1][text]", "writing your own code to generate custom memes"));

        httpPost.setEntity(new UrlEncodedFormEntity(parameters));

        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
        System.out.println(response.getCode() + "     " + response.getReasonPhrase());
        HttpEntity entity = response.getEntity();
        System.out.println(entity);
        decode(entity);
        EntityUtils.consume(entity);
    }

    private void decode(HttpEntity encoded) throws IOException, ParseException {
        String jsonString = EntityUtils.toString(encoded);
        System.out.println(jsonString);
        HashMap<String, String> jsonMap = JsonPath.read(jsonString, "$.data");
        System.out.println(jsonMap.get("url"));
        System.out.println(jsonMap.get("page_url"));
    }

    @Test
    public void getMemeTemplateList_test() throws IOException, ParseException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.imgflip.com/get_memes");

        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
        System.out.println(response.getCode() + "     " + response.getReasonPhrase());
        HttpEntity entity = response.getEntity();

        String jsonString = EntityUtils.toString(entity);
        System.out.println(jsonString);

        JSONArray jsonArray = JsonPath.read(jsonString, "$.data.memes");
        @SuppressWarnings("rawtypes") HashMap jsonMap = (HashMap) jsonArray.get(0);
        System.out.println(jsonMap);
        System.out.println(jsonMap.get("url"));

        EntityUtils.consume(entity);
    }

    @Test
    public void templateList(){
        String jsonString = JSONFetcher.getLocalJSONString();

        JSONArray jsonArray = JsonPath.read(jsonString, "$.data.memes.*");

        List<Template> templateList = new ArrayList<>();

        for (Object memeJson : jsonArray) {
            @SuppressWarnings("rawtypes") HashMap memeMap = (HashMap) memeJson;
            Template template = new Template(memeMap.get("name").toString());
            template.setMemeID(memeMap.get("id").toString());
            template.setTemplateURL(memeMap.get("url").toString());
            template.setBoxCount((Integer) memeMap.get("box_count"));
            templateList.add(template);
        }

        for (Template template : templateList) {
            System.out.println(template.toString());
        }

    }
}
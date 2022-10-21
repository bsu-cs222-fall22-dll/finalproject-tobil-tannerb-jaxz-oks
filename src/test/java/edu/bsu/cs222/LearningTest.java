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
import org.apache.hc.core5.http.ProtocolException;
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

    private void decode(HttpEntity encoded) throws IOException, ParseException {
        String jsonString = EntityUtils.toString(encoded);
        System.out.println(jsonString);
        HashMap<String, String> jsonMap = JsonPath.read(jsonString, "$.data");
        System.out.println(jsonMap.get("url"));
        System.out.println(jsonMap.get("page_url"));
    }
}

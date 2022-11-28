package edu.bsu.cs222;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JSONFetcher {
    @SuppressWarnings("unused") //We will use this in the future when we decide to use live data
    public static String getJSONString() throws IOException, ParseException {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://api.imgflip.com/get_memes");

            CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
    }

    public static String getLocalJSONString() {
        InputStream jsonInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("memes.json");
        assert jsonInputStream != null;
        Scanner scanner = new Scanner(jsonInputStream, StandardCharsets.UTF_8);
        return scanner.useDelimiter("\\A").next();
    }
}

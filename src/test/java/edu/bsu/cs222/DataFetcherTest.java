package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DataFetcherTest {
    @Test
    public void nameToIndexTest() throws IOException, ParseException {
        DataFetcher dataFetcher = new DataFetcher();
        String memeIndex = dataFetcher.getTemplateByName("Drake Hotline Bling");
        Assertions.assertEquals("0", memeIndex);
    }

    @Test
    public void nameToIndexTestTwo() throws IOException, ParseException {
        DataFetcher dataFetcher = new DataFetcher();
        String memeIndex = dataFetcher.getTemplateByName("One Does Not Simply");
        Assertions.assertEquals("15", memeIndex);
    }

    @Test
    public void nameToIndexWrongNameTest() throws IOException, ParseException {
        DataFetcher dataFetcher = new DataFetcher();
        String memeIndex = dataFetcher.getTemplateByName("drake hotline");
        Assertions.assertEquals("Meme Does Not Exist", memeIndex);
    }

    @Test
    public void nameToIndexNameThatDoesNotExist() throws IOException, ParseException {
        DataFetcher dataFetcher = new DataFetcher();
        String memeIndex = dataFetcher.getTemplateByName("CS222 Meme");
        Assertions.assertEquals("Meme Does Not Exist", memeIndex);
    }

    @Test
    public void boxCountTest() throws IOException, ParseException{
        DataFetcher dataFetcher = new DataFetcher();
        String memeIndex = dataFetcher.getBoxCount(0);
        Assertions.assertEquals("2",memeIndex);
    }

    @Test
    public void boxCountTesttwo() throws IOException, ParseException{
        DataFetcher dataFetcher = new DataFetcher();
        String memeIndex = dataFetcher.getBoxCount(3);
        Assertions.assertEquals("5",memeIndex);
    }
    @Test
    public void idTest() throws IOException,ParseException{
        DataFetcher dataFetcher = new DataFetcher();
        String id = dataFetcher.getID(0);
        Assertions.assertEquals("181913649",id);
    }
    @Test
    public void idTesttwo() throws IOException,ParseException{
        DataFetcher dataFetcher = new DataFetcher();
        String id = dataFetcher.getID(101);
        Assertions.assertEquals("Meme is out of range",id);
    }

    @Test
    public void urlTest() throws IOException,ParseException{
        DataFetcher dataFetcher = new DataFetcher();
        String url = dataFetcher.getURL(0);
        Assertions.assertEquals("https://i.imgflip.com/30b1gx.jpg", url);
    }
    @Test
    public void urlTesttwo() throws IOException,ParseException{
        DataFetcher dataFetcher = new DataFetcher();
        String url = dataFetcher.getURL(102);
        Assertions.assertEquals("Meme is out of range", url);
     }
}

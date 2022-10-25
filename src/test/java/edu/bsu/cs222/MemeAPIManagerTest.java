package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemeAPIManagerTest {
    @Test
    public void nameToIndexTest(){
        MemeAPIManager dataFetcher = new MemeAPIManager();
        int memeIndex = dataFetcher.getTemplateByName("Drake Hotline Bling");
        Assertions.assertEquals(0, memeIndex);
    }

    @Test
    public void nameToIndexTestTwo(){
        MemeAPIManager dataFetcher = new MemeAPIManager();
        int memeIndex = dataFetcher.getTemplateByName("One Does Not Simply");
        Assertions.assertEquals(16, memeIndex);
    }


    @Test
    public void boxCountTest(){
        MemeAPIManager dataFetcher = new MemeAPIManager();
        String memeIndex = dataFetcher.getBoxCount(0);
        Assertions.assertEquals("2",memeIndex);
    }

    @Test
    public void boxCountTesttwo(){
        MemeAPIManager dataFetcher = new MemeAPIManager();
        String memeIndex = dataFetcher.getBoxCount(3);
        Assertions.assertEquals("5",memeIndex);
    }
    @Test
    public void idTest(){
        MemeAPIManager dataFetcher = new MemeAPIManager();
        String id = dataFetcher.getID(0);
        Assertions.assertEquals("181913649",id);
    }
    @Test
    public void idTestTwo(){
        MemeAPIManager dataFetcher = new MemeAPIManager();
        String id = dataFetcher.getID(101);
        Assertions.assertEquals("Meme is out of range",id);
    }

    @Test
    public void urlTest(){
        MemeAPIManager dataFetcher = new MemeAPIManager();
        String url = dataFetcher.getURL(0);
        Assertions.assertEquals("https://i.imgflip.com/30b1gx.jpg", url);
    }
    @Test
    public void urlTestTwo(){
        MemeAPIManager dataFetcher = new MemeAPIManager();
        String url = dataFetcher.getURL(102);
        Assertions.assertEquals("Meme is out of range", url);
     }
}
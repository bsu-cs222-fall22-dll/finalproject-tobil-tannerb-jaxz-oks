package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemeAPIManagerTest {
    @Test
    public void nameToIndexTest(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        int memeIndex = memeAPIManager.getTemplateByName("Drake Hotline Bling");
        Assertions.assertEquals(0, memeIndex);
    }

    @Test
    public void nameToIndexTestLargerIndex(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        int memeIndex = memeAPIManager.getTemplateByName("One Does Not Simply");
        Assertions.assertEquals(16, memeIndex);
    }


    @Test
    public void boxCountTest(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        String memeIndex = memeAPIManager.getBoxCount(0);
        Assertions.assertEquals("2",memeIndex);
    }

    @Test
    public void boxCountTestLargerIndex(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        String memeIndex = memeAPIManager.getBoxCount(3);
        Assertions.assertEquals("5",memeIndex);
    }
    @Test
    public void idTest(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        String id = memeAPIManager.getID(0);
        Assertions.assertEquals("181913649",id);
    }
    @Test
    public void idTestWithIndexOutOfRange(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        String id = memeAPIManager.getID(101);
        Assertions.assertEquals("Meme is out of range",id);
    }

    @Test
    public void urlTest(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        String url = memeAPIManager.getURL(0);
        Assertions.assertEquals("https://i.imgflip.com/30b1gx.jpg", url);
    }
    @Test
    public void urlTestWithIndexOutOfRange(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        String url = memeAPIManager.getURL(102);
        Assertions.assertEquals("Meme is out of range", url);
     }
}
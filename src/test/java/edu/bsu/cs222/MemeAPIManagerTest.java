package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MemeAPIManagerTest {
    @Test
    public void nameToIndexTest(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        int memeIndex = memeAPIManager.getTemplateByName("Woman Yelling At Cat");
        Assertions.assertNotEquals(-1, memeIndex);
    }

    @Test
    public void nameToIndexTestLargerIndex(){
        MemeAPIManager memeAPIManager = new MemeAPIManager();
        int memeIndex = memeAPIManager.getTemplateByName("One Does Not Simply");
        Assertions.assertNotEquals(-1, memeIndex);
    }

    @Test
    public void getRandomMemeTest(){
        String memeID = MemeAPIManager.getRandomMeme();
        Assertions.assertNotEquals("12345", memeID);
    }
}
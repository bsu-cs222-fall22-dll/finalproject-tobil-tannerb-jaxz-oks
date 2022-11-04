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

}
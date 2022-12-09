package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

    //Unless the variables in your config.properties perfectly match your environment variables, THESE TESTS WILL FAIL!!!
    // You may need to set individual environment variables for each individual test function in this class.

public class ReadConfigTest {

    @Test
    public void testReadToken() {
        Assertions.assertEquals(ReadConfigProperties.getBotToken(), System.getenv("TOKEN"));
    }

    @Test
    public void testReadGuildID(){
        Assertions.assertEquals(ReadConfigProperties.getGuildID(), System.getenv("GUILD_ID"));
    }

    @Test
    public void testReadUsername(){
        Assertions.assertEquals(ReadConfigProperties.getImgflipUser(), System.getenv("USER"));
    }

    @Test
    public void testReadPassword(){
        Assertions.assertEquals(ReadConfigProperties.getImgflipPassword(), System.getenv("PASSWORD"));
    }
}

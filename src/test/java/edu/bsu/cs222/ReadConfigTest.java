package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

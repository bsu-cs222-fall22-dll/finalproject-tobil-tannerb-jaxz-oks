package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

public class JSONParserTest {
    @Test
    public void getTop20MemeListTest() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<String> jsonArray = jsonParser.getMemeList();
        Assertions.assertEquals(20, jsonArray.size());
    }
}
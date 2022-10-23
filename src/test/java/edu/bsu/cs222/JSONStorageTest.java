package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONStorageTest {
    @Test
    public void getTop20MemeListTest() throws IOException, ParseException {
        JSONStorage jsonStorage = new JSONStorage();
        ArrayList jsonArray = jsonStorage.getMemeList();
        System.out.println(jsonArray);
        Assertions.assertEquals(20, jsonArray.size());
    }
}

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
}

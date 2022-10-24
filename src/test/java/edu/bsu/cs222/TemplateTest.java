package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TemplateTest {
    @Test
    public void TemplateFormatterTest() throws IOException, ParseException {
        TemplateFormatter formatter = new TemplateFormatter();
        formatter.formatTop20();
    }
}

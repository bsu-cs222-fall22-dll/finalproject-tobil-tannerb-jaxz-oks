package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class JSONParserTest {
    @Test
    public void DrakeTest() {
        List<Template> templateList = JSONParser.getTemplateListForTest();
        Assertions.assertEquals("Drake Hotline Bling", templateList.get(0).getMemeName());
    }
}
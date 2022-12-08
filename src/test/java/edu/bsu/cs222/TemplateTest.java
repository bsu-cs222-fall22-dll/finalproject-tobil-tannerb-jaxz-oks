package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TemplateTest {
    @Test
    public void TemplateFormatterTest() {
        List<Template> templateList = JSONParser.getTemplateListForTest();
        System.out.println(templateList.get(1));
        Assertions.assertEquals("87743020, Two Buttons, https://i.imgflip.com/1g8my4.jpg", templateList.get(1).toString());
    }
}
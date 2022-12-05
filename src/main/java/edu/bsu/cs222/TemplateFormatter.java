package edu.bsu.cs222;

import java.util.List;

public class TemplateFormatter {
    public static String formatTop20(List<Template> templateList) {
        StringBuilder top20 = new StringBuilder();
        for(Template template : templateList.subList(0, 20)){
            int order = template.getMemeRank();
            String name = template.getMemeName();
            String url = template.getUrl();
            top20.append(String.format("(%s) Name: %-50s Example: %20s\n", order, name, url));
        }
        top20.append("(21) Random\n");
        return top20.toString();
    }
    public static String formatTop100(List<Template> templateList){
        StringBuilder top100 = new StringBuilder();
        for(Template template : templateList.subList(0, 100)){
            int order = template.getMemeRank();
            String name = template.getMemeName();
            String url = template.getUrl();
            top100.append(String.format("(%s) Name: %-50s Example: %20s\n", order, name, url));
        }
        return top100.toString();

    }
}
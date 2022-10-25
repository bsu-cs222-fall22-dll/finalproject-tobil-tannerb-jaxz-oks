package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import java.util.ArrayList;

public class TemplateFormatter {
    public void formatTop20() throws IOException, ParseException {
        ArrayList<String> list = new  JSONParser().getMemeList();
        MemeAPIManager data = new MemeAPIManager();
        for(int i = 0; i < list.size(); i++){
            String order = String.valueOf(i+1);
            String name = data.getName(i);
            String url = data.getURL(i);
            System.out.printf("(%s) Name: %-50s Example: %20s\n", order,name,url);
        }
    }
}
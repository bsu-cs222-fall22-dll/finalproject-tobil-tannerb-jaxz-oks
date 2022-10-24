package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class TemplateFormatter {
    public void formatTop20() throws IOException, ParseException {
        ArrayList list = new  JSONStorage().getMemeList();
        DataFetcher data = new DataFetcher();
        for(int i = 0; i < list.size(); i++){
            String name = data.getName(i);
            String box = data.getBoxCount(i);
            String id = data.getID(i);
            String url = data.getURL(i);
            System.out.printf("Name: %s BoxCount:%s Id:%s Url: %s\n", name,box,id,url);
        }
    }
}

package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class Template {

    public Template (String memeName){
        this.memeName = memeName;
    }

    public String memeName;
    public String memeID;
    public String url;
    public int boxCount;
    public int memeIndex;
    public DataFetcher dataFetcher = new DataFetcher();

    public int getMemeIndex() throws IOException, ParseException {
        dataFetcher.getTemplateByName(memeName);
        return memeIndex;
    }


    public String getMemeID() throws IOException, ParseException {
        memeID = dataFetcher.getID(getMemeIndex());
        return memeID;
    }

    public String getUrl() {

        return url;
    }

    public int getBoxCount() throws IOException, ParseException {
        String results = dataFetcher.getBoxCount(getMemeIndex());
        if (results.equals("Meme is out of range")){
            //Need to figure this out
            results = "Meme is out of range";
        } else {
            boxCount = Integer.parseInt(results);
        }
;        return boxCount;
    }
}

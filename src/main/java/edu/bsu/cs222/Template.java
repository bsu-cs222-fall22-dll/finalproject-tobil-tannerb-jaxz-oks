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
    public MemeAPIManager memeAPIManager = new MemeAPIManager();

    public int getMemeIndex() throws IOException, ParseException {
        memeAPIManager.getTemplateByName(memeName);
        return memeIndex;
    }


    public String getMemeID() throws IOException, ParseException {
        memeID = memeAPIManager.getID(getMemeIndex());
        return memeID;
    }

    public String getUrl() throws IOException, ParseException {
        memeID = memeAPIManager.getURL(getMemeIndex());
        return url;
    }

    public int getBoxCount() throws IOException, ParseException {
        String results = memeAPIManager.getBoxCount(getMemeIndex());
        if (results.equals("Meme is out of range")){
            //Need to figure this out
            results = "Meme is out of range";
        } else {
            boxCount = Integer.parseInt(results);
        }
;        return boxCount;
    }
}

package edu.bsu.cs222;


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

    public int getMemeIndex(){
        memeIndex = memeAPIManager.getTemplateByName(memeName);
        return memeIndex;
    }

    public String getMemeID(){
        memeID = memeAPIManager.getID(getMemeIndex());
        return memeID;
    }
    @SuppressWarnings("unused")     //Could be used in future iterations.
    public String getUrl(){
        url = memeAPIManager.getURL(getMemeIndex());
        return url;
    }

    public int getBoxCount(){
        String results = memeAPIManager.getBoxCount(getMemeIndex());
        boxCount = Integer.parseInt(results);

        return boxCount;
    }
}
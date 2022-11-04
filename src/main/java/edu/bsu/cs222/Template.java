package edu.bsu.cs222;

public class Template {

    private String memeName;
    private String memeID;
    private String templateURL;
    private int boxCount;
    private int memeRank;

    public Template(){}

    public Template (String memeName){
        this.memeName = memeName;
    }

    public void setMemeName(String memeName){
        this.memeName = memeName;
    }

    public void setMemeID(String memeID){
        this.memeID = memeID;
    }

    public void setTemplateURL(String templateURL){
        this.templateURL = templateURL;
    }

    public void setBoxCount(int boxCount){
        this.boxCount = boxCount;
    }

    public void setMemeRank(int memeRank){
        this.memeRank = memeRank;
    }

    public String getMemeName(){
        return memeName;
    }

    public int getMemeRank(){
        return memeRank;
    }

    public String getMemeID(){
        return memeID;
    }

    public String getUrl(){
        return templateURL;
    }

    public int getBoxCount(){
        return boxCount;
    }

    @Override
    public String toString(){
        return memeID + ", " + memeName + ", " + templateURL;
    }
}
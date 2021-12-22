package org.techtown.ffinalproject;

public class ListViewAdapterData {

    private int num;
    private String date;
    private String happy;
    private String sad;
    private String emotion;
    private int color;

    public void setNum(int num){this.num = num;}
    public void setDate(String date){this.date = date;}
    public void setHappy(String happy){this.happy = happy;}
    public void setSad(String sad){this.sad = sad;}
    public void setEmotion(String emotion){this.emotion = emotion;}
    public void setColor(int color){this.color = color;}


    public int getNum(){return this.num;}
    public String getDate(){return this.date;}
    public String getHappy(){return this.happy;}
    public String getSad(){return this.sad;}
    public String getEmotion(){return this.emotion;}
    public int getColor(){return this.color;}

}

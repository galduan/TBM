package com.example.tbm;

import java.util.ArrayList;

public class ItemCard {
    public static enum Reaction {
        love,
        like,
        sad,
        happy,
        brave
    }
    Reaction reaction;
    private int mImageResource;
    private String mCaption;
    private int[] mReactionNotClicked;//love,like,sad,happy,brave
    private int[] mReactionYesClicked;//love,like,sad,happy,brave
    private int likeCounter;
    private int loveCounter;
    private int braveCounter;
    private int sadCounter;
    private int happyCounter;
    private String mHashtages;
    private String mUrl;
    private ArrayList<String> mHashtagsList;
    private boolean isLikeClicked = false;
    private boolean isLoveClicked = false;
    private boolean isBraveClicked = false;
    private boolean isSadCClicked = false;
    private boolean isHappyClicked = false;

    private int startLikeIMG   = R.drawable.facebook_like;// love ;
    private int startLoveIMG   = R.drawable.icon_heart;// like ;
    private int startHappyIMG  = R.drawable.icon_happy;// sad ;
    private int startBraveIMG  = R.drawable.icon_muscle;// happy ;
    private int startSadIMG    = R.drawable.icon_sad;// brave;


    private int endLikeIMG   = R.drawable.facebook_like_color;// love ;
    private int endLoveIMG   = R.drawable.icon_heart_color;// like ;
    private int endHappyIMG  = R.drawable.icon_happy_color;// sad ;
    private int endBraveIMG  = R.drawable.icon_muscle_color;// happy  ;
    private int endSadIMG    = R.drawable.icon_sad_color;// brave;


    public ItemCard(int imageResource, String caption) {
        this.mImageResource = imageResource;
        this.mCaption = caption;
    }

    public ItemCard(String url, String caption) {
        this.mUrl = url;
        this.mCaption = caption;
        this.braveCounter = 0;
        this.happyCounter = 0;
        this.likeCounter = 0;
        this.sadCounter = 0;
        this.loveCounter = 0;


    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getCaption() {
        return mCaption;
    }
    public String getUrl() {
        return mUrl;
    }


    public String getHashtag() {
        return mHashtages;
    }

    public int getLikeCounter() {
        return likeCounter;
    }

    public int getLoveCounter() {
        return loveCounter;
    }

    public int getBraveCounter() {
        return braveCounter;
    }

    public int getSadCounter() {
        return sadCounter;
    }

    public int getHappyCounter() {
        return happyCounter;
    }


    public void setLikeCounter(int likeCounter) {
        this.likeCounter = likeCounter;
    }

    public void setLoveCounter(int loveCounter) {
        this.loveCounter = loveCounter;
    }

    public void setBraveCounter(int braveCounter) {
        this.braveCounter = braveCounter;
    }

    public void setSadCounter(int sadCounter) {
        this.sadCounter = sadCounter;
    }

    public void setHappyCounter(int happyCounter) {
        this.happyCounter = happyCounter;
    }

    public int increaseLikeCounter() {
        isLikeClicked = true;
        return this.likeCounter++;

    }

    public int increaseLoveCounter() {
        isLoveClicked = true;
        return this.loveCounter++;
    }

    public int increaseBraveCounter() {
        isBraveClicked = true;
        return this.braveCounter++;
    }

    public int increaseSadCounter() {
        isSadCClicked = true;
        return this.sadCounter++;
    }

    public int increaseHappyCounter() {
        isHappyClicked = true;
        return this.happyCounter++;
    }
    public int decreaseLikeCounter() {
        isLikeClicked = false;
        return this.likeCounter--;
    }

    public int decreaseLoveCounter() {
        isLoveClicked = false;
        return this.loveCounter--;
    }

    public int decreaseBraveCounter() {
        isBraveClicked = false;
        return this.braveCounter--;
    }

    public int decreaseSadCounter() {
        isSadCClicked = false;
        return this.sadCounter--;
    }

    public int decreaseHappyCounter() {
        isHappyClicked = false;
        return this.happyCounter--;
    }

    public boolean isLikeClicked() {
        return isLikeClicked;
    }

    public boolean isLoveClicked() {
        return isLoveClicked;
    }

    public boolean isBraveClicked() {
        return isBraveClicked;
    }

    public boolean isSadCClicked() {
        return isSadCClicked;
    }

    public boolean isHappyClicked() {
        return isHappyClicked;
    }

    public int getStartLikeIMG() {
        return startLikeIMG;
    }

    public void setStartLikeIMG(int startLikeIMG) {
        this.startLikeIMG = startLikeIMG;
    }

    public int getStartLoveIMG() {
        return startLoveIMG;
    }

    public void setStartLoveIMG(int startLoveIMG) {
        this.startLoveIMG = startLoveIMG;
    }

    public int getStartHappyIMG() {
        return startHappyIMG;
    }

    public void setStartHappyIMG(int startHappyIMG) {
        this.startHappyIMG = startHappyIMG;
    }

    public int getStartBraveIMG() {
        return startBraveIMG;
    }

    public void setStartBraveIMG(int startBraveIMG) {
        this.startBraveIMG = startBraveIMG;
    }

    public int getStartSadIMG() {
        return startSadIMG;
    }

    public void setStartSadIMG(int startSadIMG) {
        this.startSadIMG = startSadIMG;
    }

    public int getEndLikeIMG() {
        return endLikeIMG;
    }

    public void setEndLikeIMG(int endLikeIMG) {
        this.endLikeIMG = endLikeIMG;
    }

    public int getEndLoveIMG() {
        return endLoveIMG;
    }

    public void setEndLoveIMG(int endLoveIMG) {
        this.endLoveIMG = endLoveIMG;
    }

    public int getEndHappyIMG() {
        return endHappyIMG;
    }

    public void setEndHappyIMG(int endHappyIMG) {
        this.endHappyIMG = endHappyIMG;
    }

    public int getEndBraveIMG() {
        return endBraveIMG;
    }

    public void setEndBraveIMG(int endBraveIMG) {
        this.endBraveIMG = endBraveIMG;
    }

    public int getEndSadIMG() {
        return endSadIMG;
    }

    public void setEndSadIMG(int endSadIMG) {
        this.endSadIMG = endSadIMG;
    }
}

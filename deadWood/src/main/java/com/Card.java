package com;

import java.io.File;

public class Card {
    private String  cardName;
    private File    image;
    private int     budget;
    private int     sceneNum;
    private String  sceneDesc;
    private Part[]  cardParts;
    private String  location;

    public Card(String cardName, File image, int budget, int sceneNum, String sceneDesc, Part[] cardParts, String location) {
        this.cardName  = cardName;
        this.image     = image;
        this.budget    = budget;
        this.sceneNum  = sceneNum;
        this.sceneDesc = sceneDesc;
        this.cardParts = cardParts;
        this.location  = location;
    }

    public String getCardName() {
        return this.cardName;
    }

    public File getImage() {
        return this.image;
    }

    public int getBudget() {
        return this.budget;
    }

    public int getSceneNum() {
        return this.sceneNum;
    }

    public String getSceneDesc() {
        return this.sceneDesc;
    }

    public Part[] getCardParts() {
        return this.cardParts;
    }

    public String getLocation() {
        return this.location;
    }

}
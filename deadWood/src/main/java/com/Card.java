package com;

import java.io.File;

public class Card {

    /*
     * CardBuilder
     * used to create Card objects instead of a 
     * constructor with many parameters
     */
    static final class CardBuilder {
        private String  name;
        private File    image;
        private int     budget;
        private int     sceneNum;
        private String  description;
        private Part[]  parts;
        private String  location;

        public CardBuilder() {
            this.budget   = 0;
            this.sceneNum = 0;
        }


        public CardBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CardBuilder image(File image) {
            this.image = image;
            return this;
        }

        public CardBuilder budget(int budget) {
            this.budget = budget;
            return this;
        }

        public CardBuilder sceneNum(int sceneNum) {
            this.sceneNum = sceneNum;
            return this;
        }

        public CardBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CardBuilder parts(Part[] parts) {
            this.parts = parts;
            return this;
        }

        public CardBuilder location(String location) {
            this.location = location;
            return this;
        }



        public Card build() {
            if (name == null) 
                throw new IllegalStateException("Missing partName");
            if (image == null)
                throw new IllegalStateException("Missing image");
            if (budget <= 0)
                throw new IllegalStateException("Missing or invalid budget"); 
            if (sceneNum < 0)
                throw new IllegalStateException("Missing or invalid sceneNum"); 
            if (description == null)
                throw new IllegalStateException("Missing description"); 
            if (parts == null)
                throw new IllegalStateException("Missing parts");
            if (location == null) 
                throw new IllegalStateException("Missing location");
            return new Card(this);
        }
    }

    private String  name;
    private File    image;
    private int     budget;
    private int     sceneNum;
    private String  description;
    private Part[]  parts;
    private String  location;

    private Card(CardBuilder builder) {
        this.name        = builder.name;
        this.image       = builder.image;
        this.budget      = builder.budget;
        this.sceneNum    = builder.sceneNum;
        this.description = builder.description;
        this.parts       = builder.parts;
        this.location    = builder.location;
    }


    public String getName() {
        return this.name;
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

    public String getDescription() {
        return this.description;
    }

    public Part[] getParts() {
        return this.parts;
    }

    public String getLocation() {
        return this.location;
    }

}
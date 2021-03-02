package com;

import java.util.Arrays;

public class BoardLocation {

    /*
     * BoardLocationBuilder
     * used to create BoardLocation objects instead 
     * of a constructor with many parameters
     */
    static final class BoardLocationBuilder {
        private String   name;
        private String[] neighbors;
        private Area     area;
        private Take[]   takes;
        private Part[]   parts;
        
        public BoardLocationBuilder() {

        }


        public BoardLocationBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BoardLocationBuilder neighbors(String[] neighbors) {
            this.neighbors = neighbors;
            return this;
        }

        public BoardLocationBuilder area(Area area) {
            this.area = area;
            return this;
        }

        public BoardLocationBuilder takes(Take[] takes) {
            this.takes = takes;
            return this;
        }

        public BoardLocationBuilder parts(Part[] parts) {
            this.parts = parts;
            return this;
        }


        public BoardLocation build() {
            if (name == null) 
                throw new IllegalStateException("Missing name");
            if (neighbors == null)
                throw new IllegalStateException("Missing neighbors");
            if (area == null)
                throw new IllegalStateException("Missing area"); 
            return new BoardLocation(this);
        }
    }

    private String   name;
    private String[] neighbors;
    private Area     area;
    private Take[]   takes;
    private Part[]   parts;
    private Card     card;
    private int      takeIterator;

    private BoardLocation(BoardLocationBuilder builder) {
        this.name         = builder.name;
        this.neighbors    = builder.neighbors;
        this.area         = builder.area;
        this.takes        = builder.takes;
        this.parts        = builder.parts;
        this.takeIterator = 0;
    }


    public String getName() {
        return this.name;
    }

    public String[] getNeighbors() {
        return this.neighbors;
    }

    public Area getArea() {
        return this.area;
    }

    public Take[] getTakes() {
        return this.takes;
    }

    public Part[] getParts() {
        return this.parts;
    }

    public Part getPartByName(String name) {
        for (Part part : parts) {
            if (part.getName().equals(name)) {return part;}
        }

        for (Part part : card.getParts()) {
            if (part.getName().equals(name)) {return part;}
        }
        throw new IllegalArgumentException("part not found!");
    }

    public Card getCard(){
        return this.card;
    }

    public void setCard(Card newCard){
        this.card = newCard;
    }

    public boolean finishShot() {
        takes[takeIterator].setShotCompleted(true);
        takeIterator++;
        return takeIterator >= takes.length;
    }

    public void resetTakeIncrement() {
        takeIterator = 0;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", neighbors='" + Arrays.toString(getNeighbors()) + "'" +
            ", area='" + getArea() + "'" +
            ", takes='" + Arrays.toString(getTakes()) + "'" +
            ", parts='" + Arrays.toString(getParts()) + "'" +
            "}";
    }


}

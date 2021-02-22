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
        private Area[]   takeAreas;
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

        public BoardLocationBuilder takeAreas(Area[] takeAreas) {
            this.takeAreas = takeAreas;
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
    private Area[]   takeAreas;
    private Part[]   parts;
    private Card     card;

    private BoardLocation(BoardLocationBuilder builder) {
        this.name      = builder.name;
        this.neighbors = builder.neighbors;
        this.area      = builder.area;
        this.takeAreas = builder.takeAreas;
        this.parts     = builder.parts;
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

    public Area[] getTakeAreas() {
        return this.takeAreas;
    }

    public Part[] getParts() {
        return this.parts;
    }

    public Card getCard(){
        return this.card;
    }

    public void setCard(Card newCard){
        this.card = newCard;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", neighbors='" + Arrays.toString(getNeighbors()) + "'" +
            ", area='" + getArea() + "'" +
            ", takeAreas='" + Arrays.toString(getTakeAreas()) + "'" +
            ", parts='" + Arrays.toString(getParts()) + "'" +
            "}";
    }


}

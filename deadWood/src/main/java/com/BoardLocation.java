package com;

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
        private Part[]   setParts;

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

        public BoardLocationBuilder setParts(Part[] setParts) {
            this.setParts = setParts;
            return this;
        }


        public BoardLocation build() {
            if (name == null) 
                throw new IllegalStateException("Missing partName");
            if (neighbors == null)
                throw new IllegalStateException("Missing line");
            if (area == null)
                throw new IllegalStateException("Missing area"); 
            if (takeAreas == null)
                throw new IllegalStateException("Missing takeAreas"); 
            if (setParts == null)
                throw new IllegalStateException("Missing setParts"); 
            return new BoardLocation(this);
        }
    }

    private String   name;
    private String[] neighbors;
    private Area     area;
    private Area[]   takeAreas;
    private Part[]   setParts;
    

    private BoardLocation(BoardLocationBuilder builder) {
        builder.name = name;
        builder.neighbors = neighbors;
        builder.area = area;
        builder.takeAreas = takeAreas;
        builder.setParts = setParts;
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

    public Part[] getSetParts() {
        return this.setParts;
    }

}
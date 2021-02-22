package com;

public class Part{

    /*
     * PartBuilder
     * used to create Part objects instead of a 
     * constructor with many parameters
     */
    static final class PartBuilder {
        private String  name;
        private String  line;
        private int     level;
        private Area    area;
        private boolean onCard = false;

        public PartBuilder() {

        }

        public PartBuilder name (String name) {
            this.name = name;
            return this;
        }

        public PartBuilder line (String line) {
            this.line = line;
            return this;
        }

        public PartBuilder level (int level) {
            this.level = level;
            return this;
        }

        public PartBuilder area (Area area) {
            this.area = area;
            return this;
        }

        public PartBuilder onCard (boolean onCard) {
            this.onCard = onCard;
            return this;
        }

        public Part build() {
            if (name == null) 
                throw new IllegalStateException("Missing name");
            if (line == null)
                throw new IllegalStateException("Missing line");
            if (level < 0 || level > 6) 
                throw new IllegalStateException("Missing or invalid level");
            if (area == null)
                throw new IllegalStateException("Missing area"); 
            return new Part(this);
        }
    }

    private String  name;
    private String  line;
    private int     level;
    private Area    area;
    private boolean onCard;
    private int     practiceChips;
    private int     shotCounters;
    
    private Part(PartBuilder builder) {
        this.name   = builder.name;
        this.level  = builder.level;
        this.area   = builder.area;
        this.line   = builder.line;
        this.onCard = builder.onCard;
        practiceChips = 0;
        shotCounters = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getLine() {
        return this.line;
    }

    public int getLevel() {
        return this.level;
    }

    public Area getArea() {
        return this.area;
    }

    public int getPracticeChips() {
        return this.practiceChips;
    }

    public int getShotCounters() {
        return this.shotCounters;
    }

    public boolean getOnCard() {
        return this.onCard;
    }

    public void addShotCounter() {
        shotCounters++;
    }

    public void addPracticeChip() {
        practiceChips++;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", line='" + getLine() + "'" +
            ", level='" + getLevel() + "'" +
            ", area='" + getArea() + "'" +
            ", onCard='" + getOnCard() + "'" +
            ", practiceChips='" + getPracticeChips() + "'" +
            ", shotCounters='" + getShotCounters() + "'" +
            "}";
    }

}

package com;

import java.awt.*;

public class Part{

    /*
     * PartBuilder
     * used to create Part objects instead of a 
     * constructor with many parameters
     */
    static final class PartBuilder {
        private String     name;
        private String     line;
        private int        level;
        private Rectangle  rectangle;
        private boolean    onCard = false;
        private boolean    partComplete = false;

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

        public PartBuilder rectangle (Rectangle rectangle) {
            this.rectangle = rectangle;
            return this;
        }

        public PartBuilder onCard (boolean onCard) {
            this.onCard = onCard;
            return this;
        }

        public PartBuilder partComplete(boolean partComplete){
            this.partComplete = partComplete;
            return this;
        }

        public Part build() {
            if (name == null) 
                throw new IllegalStateException("Missing name");
            if (line == null)
                throw new IllegalStateException("Missing line");
            if (level < 0 || level > 6) 
                throw new IllegalStateException("Missing or invalid level");
            if (rectangle == null)
                throw new IllegalStateException("Missing rectangle");
            return new Part(this);
        }
    }

    private String    name;
    private String    line;
    private int       level;
    private Rectangle rectangle;
    private boolean   onCard;
    private boolean   partComplete;
    private int       practiceChips;
    
    private Part(PartBuilder builder) {
        this.name   = builder.name;
        this.level  = builder.level;
        this.rectangle   = builder.rectangle;
        this.line   = builder.line;
        this.onCard = builder.onCard;
        this.partComplete = builder.partComplete;
        practiceChips = 0;
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

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public int getPracticeChips() {
        return this.practiceChips;
    }

    public boolean getOnCard() {
        return this.onCard;
    }

    public boolean getPartComplete() { return this.partComplete; }

    public void addPracticeChip() {
        practiceChips++;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", line='" + getLine() + "'" +
            ", level='" + getLevel() + "'" +
            ", rectangle='" + getRectangle() + "'" +
            ", onCard='" + getOnCard() + "'" +
            ", practiceChips='" + getPracticeChips() + "'" +
            "}";
    }

}

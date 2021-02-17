package com;

public class Part{

    static final class PartBuilder {
        private String partName;
        private String line;
        private int    level;
        private Area   area;

        public PartBuilder() {

        }

        public PartBuilder partName (String partName) {
            this.partName = partName;
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

        public Part build() {
            if (partName == null) 
                throw new IllegalStateException("Missing partName");
            if (line == null)
                throw new IllegalStateException("Missing line");
            if (level < 0 || level > 6) 
                throw new IllegalStateException("Missing or invalid level");
            if (area == null)
                throw new IllegalStateException("Missing area"); 
            return new Part(this);
        }
    }

    private String partName;
    private String line;
    private int    level;
    private Area   area;
    private int    practiceChips;
    private int    shotCounters;
    

    private Part(PartBuilder builder) {
        builder.partName = partName;
        builder.level = level;
        builder.area = area;
        builder.line = line;
        practiceChips = 0;
        shotCounters = 0;
    }

    public String getPartName() {
        return this.partName;
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

    public void addShotCounter() {
        shotCounters++;
    }

    public void addPracticeChip() {
        practiceChips++;
    }
}

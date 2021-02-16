package com;

/*
 * Part
 */
public class Part{

    private String partName;
    private String line;
    private int    level;
    private Area   area;
    private int    practiceChips = 0;
    private int    shotCounters  = 0;
    

    public Part(String partName, int level, Area area, String line) {
        this.partName = partName;
        this.level = level;
        this.area = area;
        this.line = line;
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

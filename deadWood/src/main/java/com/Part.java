package com;

/*
 * Part
 */
public class Part{

    private String partName;
    private String line;
    private int    level;
    private int    practiceChips;
    private int    shotCounters;
    private int[]  area;

    public Part(String partName, int level, int [] area, String line) {
        this.partName = partName;
        this.level = level;
        this.area = area;
        this.line = line;
    }

    public void addShotCounter() {
        shotCounters++;
    }

    public void addPracticeChips() {
        practiceChips++;
    }


    
}

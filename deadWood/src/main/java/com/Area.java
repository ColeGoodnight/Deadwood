package com;

public class Area {

    private int x1;
    private int x2;
    private int h;
    private int w;

    public int getX1() {
        return this.x1;
    }

    public int getX2() {
        return this.x2;
    }

    public int getH() {
        return this.h;
    }

    public int getW() {
        return this.w;
    }


    public Area (int x1, int x2, int h, int w) {
        this.x1 = x1;
        this.x2 = x2;
        this.h = h;
        this.w = w;
    }


    @Override
    public String toString() {
        return "{" +
            " x1='" + getX1() + "'" +
            ", x2='" + getX2() + "'" +
            ", h='" + getH() + "'" +
            ", w='" + getW() + "'" +
            "}";
    }
    
}
package com;

public class Area {

    private final int x;
    private final int y;
    private final int h;
    private final int w;

    public Area (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }

    public int getH() {
        return this.h;
    }

    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", w='" + getW() + "'" +
            ", h='" + getH() + "'" +
            "}";
    }
    
}
package com;

public class Take {

    Area    area;
    boolean shotCompleted;

    public Take (Area area, boolean shotCompleted) {
        this.area          = area;
        this.shotCompleted = shotCompleted;
    }

    public Area getArea() {
        return this.area;
    }

    public boolean getShotCompleted() {
        return this.shotCompleted;
    }

    public void setShotCompleted(boolean shotCompleted) {
        this.shotCompleted = shotCompleted;
    }

    @Override
    public String toString() {
        return "{" +
            " area='" + getArea() + "'" +
            ", shotCompleted='" + getShotCompleted() + "'" +
            "}";
    }
}
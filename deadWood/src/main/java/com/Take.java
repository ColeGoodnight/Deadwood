package com;

import java.awt.*;

public class Take {

    Rectangle rectangle;
    boolean shotCompleted;

    public Take (Rectangle rectangle, boolean shotCompleted) {
        this.rectangle          = rectangle;
        this.shotCompleted = shotCompleted;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
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
            " rectangle='" + getRectangle() + "'" +
            ", shotCompleted='" + getShotCompleted() + "'" +
            "}";
    }
}
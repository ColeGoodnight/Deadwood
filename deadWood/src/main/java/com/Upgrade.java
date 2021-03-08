package com;

import java.awt.*;

public class Upgrade {

    /*
        * UpgradeBuilder
        * used to create Upgrade objects instead of a 
        * constructor with many parameters
        */
    static final class UpgradeBuilder {
        private int    level;
        private String currency;
        private int    amt;
        private Rectangle rectangle;

        public UpgradeBuilder() {

        }

        public UpgradeBuilder level (int level) {
            this.level = level;
            return this;
        }

        public UpgradeBuilder currency (String currency) {
            this.currency = currency;
            return this;
        }

        public UpgradeBuilder amt (int amt) {
            this.amt = amt;
            return this;
        }

        public UpgradeBuilder rectangle (Rectangle rectangle) {
            this.rectangle = rectangle;
            return this;
        }

        public Upgrade build() {
            if (level < 0 || level > 6) 
                throw new IllegalStateException("Missing level");
            if (currency == null)
                throw new IllegalStateException("Missing currency");
            if (amt < 0) 
                throw new IllegalStateException("Missing or invalid amt");
            if (rectangle == null)
                throw new IllegalStateException("Missing rectangle");
            return new Upgrade(this);
        }
    }

    private String currency;
    private int    amt;
    private Rectangle   rectangle;
    private int    level;
    
    private Upgrade(UpgradeBuilder builder) {
        this.level    = builder.level;
        this.amt      = builder.amt;
        this.rectangle     = builder.rectangle;
        this.currency = builder.currency;
    }

    public int getLevel() {
        return this.level;
    }

    public String getCurrency() {
        return this.currency;
    }

    public int getAmt() {
        return this.amt;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    @Override
    public String toString() {
        return "{" +
            " level='" + getLevel() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", amt='" + getAmt() + "'" +
            ", rectangle='" + getRectangle() + "'" +
            "}";
    }
}
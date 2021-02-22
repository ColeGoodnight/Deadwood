package com;

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
        private Area   area;

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

        public UpgradeBuilder area (Area area) {
            this.area = area;
            return this;
        }

        public Upgrade build() {
            if (level < 0 || level > 6) 
                throw new IllegalStateException("Missing level");
            if (currency == null)
                throw new IllegalStateException("Missing currency");
            if (amt < 0) 
                throw new IllegalStateException("Missing or invalid amt");
            if (area == null)
                throw new IllegalStateException("Missing area"); 
            return new Upgrade(this);
        }
    }

    private String currency;
    private int    amt;
    private Area   area;
    private int    level;
    
    private Upgrade(UpgradeBuilder builder) {
        this.level    = builder.level;
        this.amt      = builder.amt;
        this.area     = builder.area;
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

    public Area getArea() {
        return this.area;
    }

    @Override
    public String toString() {
        return "{" +
            " level='" + getLevel() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", amt='" + getAmt() + "'" +
            ", area='" + getArea() + "'" +
            "}";
    }
}
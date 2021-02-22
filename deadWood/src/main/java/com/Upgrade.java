package com;

public class Upgrade {

    /*
        * UpgradeBuilder
        * used to create Upgrade objects instead of a 
        * constructor with many parameters
        */
    static final class UpgradeBuilder {
        private String name;
        private String currency;
        private int    amt;
        private Area   area;

        public UpgradeBuilder() {

        }

        public UpgradeBuilder name (String name) {
            this.name = name;
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
            if (name == null) 
                throw new IllegalStateException("Missing name");
            if (currency == null)
                throw new IllegalStateException("Missing currency");
            if (amt < 0) 
                throw new IllegalStateException("Missing or invalid amt");
            if (area == null)
                throw new IllegalStateException("Missing area"); 
            return new Upgrade(this);
        }
    }

    private String name;
    private String currency;
    private int    amt;
    private Area   area;
    
    private Upgrade(UpgradeBuilder builder) {
        this.name = builder.name;
        this.amt = builder.amt;
        this.area = builder.area;
        this.currency = builder.currency;
    }

    public String getName() {
        return this.name;
    }

    public String getcurrency() {
        return this.currency;
    }

    public int getamt() {
        return this.amt;
    }

    public Area getArea() {
        return this.area;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", currency='" + getcurrency() + "'" +
            ", amt='" + getamt() + "'" +
            ", area='" + getArea() + "'" +
            "}";
    }
}
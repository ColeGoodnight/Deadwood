package com;

public class UpgradeManager {

    Upgrade[] upgrades;

    public UpgradeManager (Upgrade[] upgrades) {
        this.upgrades = upgrades;
    }

    public Upgrade getUpgrade(String currency, int desiredRank) {
        for (int i = 0; i < upgrades.length; i++) {
            if (upgrades[i].getCurrency().equals(currency) &&
                upgrades[i].getLevel() == desiredRank) {
                    return upgrades[i];
            }
        }
        return null;
    }
}

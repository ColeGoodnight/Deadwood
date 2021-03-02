package com;

import java.util.NoSuchElementException;

public class UpgradeManager {

    Upgrade[] upgrades;

    public UpgradeManager (Upgrade[] upgrades) {
        this.upgrades = upgrades;
    }

    public Upgrade getUpgrade(String currency, int desiredRank) {
        for (Upgrade upgrade : upgrades) {
            if (upgrade.getCurrency().equals(currency) &&
                    upgrade.getLevel() == desiredRank) {
                return upgrade;
            }
        }
        throw new NoSuchElementException("no such upgrade found");
    }
}

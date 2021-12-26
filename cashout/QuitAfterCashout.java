package org.lostclient.cashout;

import org.lostclient.api.utilities.container.OwnedItems;
import org.lostclient.api.utilities.math.Calculations;
import org.lostclient.framework.Leaf;

public class QuitAfterCashout extends Leaf {

    @Override
    public boolean isValid() {
        return !OwnedItems.contains("Coins") && !OwnedItems.contains("Blue partyhat");
    }
    @Override
    public int onLoop() {
    	System.exit(0);
        return (int) Calculations.nextGaussianRandom(1000, 500);
    }
    
}

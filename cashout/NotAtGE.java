package org.lostclient.cashout;

import org.lostclient.api.containers.bank.BankLocation;
import org.lostclient.api.utilities.math.Calculations;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.Sleep;

public class NotAtGE extends Leaf {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
    	//return Sleep.Calculate(1111,1111);//sleep return here if you want to blast GE at once
    	Sleep.Calculate(1111,1111);
    	if (Walking.shouldWalk(6)) {
            Walking.walk(BankLocation.GRAND_EXCHANGE.getTile());
        }
        return (int) Calculations.nextGaussianRandom(350, 250);
    }
}

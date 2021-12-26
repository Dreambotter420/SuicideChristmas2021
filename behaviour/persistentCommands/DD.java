package org.lostclient.behaviour.persistentCommands;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class DD extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.DD;
    }

    @Override
    public int onLoop() {
		//re-get player from name every loop to refresh player object
    	Player potentialMatch = Players.closest(p -> p.getName().toLowerCase().contains(API.callerName.toLowerCase()));
		if(potentialMatch != null && !potentialMatch.equals(Players.localPlayer()))
		{
			API.interactTarget = potentialMatch;
		}
		else return 1000;
		if(API.interactTarget.distance() >= 45)
		{
			API.exitstop();
			return Sleep.Calculate(111,1111);
		}
		if(API.interactTarget.distance() > 0)
		{
			
			if((Walking.getDestination() != null && Walking.getDestination().distance(API.interactTarget) > 0) ||
				!Walking.isDestinationSet())
			{
				Walking.setWalkFlag(API.interactTarget.getTile());
				MethodProvider.tickSleep(1);
			}
		}
    	return Sleep.Calculate(111,1111);
    }
}

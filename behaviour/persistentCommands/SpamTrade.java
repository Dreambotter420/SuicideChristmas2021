package org.lostclient.behaviour.persistentCommands;

import java.util.List;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class SpamTrade extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.TRADE_ONSLAUGHT;
    }

    @Override
    public int onLoop() {	
    	String[] namechars = {""};
		namechars = API.callerCmd.split(" ", 2);
		String tempName = "";
		if(namechars.length >= 2) tempName = namechars[1];
		if(tempName.isEmpty() || tempName == null || (tempName != null && tempName.contentEquals("me")))
		{
			//leave callername alone
		} 
		else 
		{
			API.callerName = tempName.toLowerCase();
		}
		List<Player> potentialMatch = Players.all(p->p.getName().toLowerCase().contains(API.callerName.toLowerCase()));
		if(potentialMatch.isEmpty())
		{
			MethodProvider.log("trade target: " + API.callerName + " not found in area! Waiting...");
			return (int) ((double) 666 + API.rand2.nextInt(666) * API.sleepMod);
		}
		else
		{
			for(Player player : potentialMatch) 
			{
				//if caller is the local player just stop
				if(player.equals(Players.localPlayer()))
				{
					API.exitstop();
					MethodProvider.log("Stopping calling ourselves!!");
					return Sleep.Calculate(111,1111);
				}
				
				//Trade target found
				API.interactTarget = player;
			}
		}
		if(API.interactTarget == null)
    	{
    		MethodProvider.log("Spam Trade target null/not existing! Waiting...");
    		return Sleep.Calculate(111,1111);
    	}
    	if(API.interactTarget != null &&
    			!API.interactTarget.getName().toLowerCase().contentEquals(Players.localPlayer().getName().toLowerCase()))
    	{
    		//need to open doors etc. to get to target
    		if(!API.interactTarget.canReach() || API.interactTarget.distance() > 15)
    		{
    			Walking.walk(API.attackTargetPlayer.getTile(), ()->{
    				return API.attackTargetPlayer.canReach() || API.interactTarget.distance() > 15;
    			});
    			return Sleep.Calculate(333,555);
    		}
    		
    		//can trade
			if(new EntityInteractEvent(API.interactTarget,"Trade with").executed())
			{
				MethodProvider.tickSleep(1);
			}
    	}
    	return (int) ((double) 666 + API.rand2.nextInt(666) * API.sleepMod);
    }
}

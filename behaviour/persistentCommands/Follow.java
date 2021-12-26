package org.lostclient.behaviour.persistentCommands;


import org.lostclient.api.accessors.Players;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class Follow extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.FOLLOW;
    }

    @Override
    public int onLoop() {		
    	String[] namechars = API.callerCmd.split(" ", 2);
    	String tempname = null;
    	if(namechars != null && namechars.length>1)
    	{
    		tempname = namechars[1];
    	}
		if(tempname == null || tempname.isEmpty() || (tempname != null && tempname.contentEquals("me")))
		{
			//follow caller by default or says "Follow me"
		} 
		else 
		{
			API.callerName = tempname.toLowerCase();
		}
		Player playerToFollow = Players.closest(p -> p.getName().toLowerCase().contains(API.callerName.toLowerCase()));
		if(playerToFollow == null)
		{
			MethodProvider.log("follow target: " + API.callerName + " not found! Waiting...");
			return (int) ((double) 600 + API.rand2.nextInt(500) * API.sleepMod);
		}
		if(playerToFollow != null && 
				playerToFollow.equals(Players.localPlayer())) {
			API.exitstop();
			MethodProvider.log("Stopping calling ourselves!!");
			return (int) ((double) 600 + API.rand2.nextInt(300) * API.sleepMod);
		}
		API.interactTarget = playerToFollow;
    	//check if following already
		if(Players.localPlayer().getTarget() != null &&
				Players.localPlayer().getTarget().equals(API.interactTarget)) 
		{
			API.callerCmd = "";
			return 10;
		}
		if(Players.localPlayer().getTarget() == null || !Players.localPlayer().getTarget().equals(API.interactTarget))
    	{
			if(API.interactTarget.distance() >= 45)
			{
				API.exitstop();
				return Sleep.Calculate(111,1111);
			}
			//need to open doors etc. to get to target
    		if(!API.interactTarget.canReach() || API.interactTarget.distance() > 15)
    		{
    			Walking.walk(API.attackTargetPlayer.getTile(), ()->{
    				return API.attackTargetPlayer.canReach() || API.interactTarget.distance() > 15;
    			});
    			return Sleep.Calculate(333,555);
    		}
    		
    		//can follow
			if(new EntityInteractEvent(API.interactTarget,"Follow").executed())
			{
				MethodProvider.tickSleep(1);
			}
		}
		return Sleep.Calculate(111, 1111);
    }
}

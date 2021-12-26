package org.lostclient.behaviour.persistentCommands;

import java.util.List;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class JamaicanBobsled extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.BOBSLED;
    }

    @Override
    public int onLoop() {
    	//We must identify the new kgbAgent
    	if(API.kgbAgent == null)
		{
    		if(API.callerName.equals(Players.localPlayer().getName()))
    		{
    			MethodProvider.log("Setting ourselfs as KGB agent");
    			API.kgbAgent = Players.localPlayer();
    			API.callerCmd = "announceBobsled";
    			return Sleep.Calculate(111,111);
    		}
    		List<Player> playerstemp = Players.all(p->p.getName().contains(API.callerName));
    		if(playerstemp.isEmpty())
    		{
    			MethodProvider.log("KGB Agent: " + API.callerName + " not found! Waiting...");
    			return Sleep.Calculate(111,1111);
    		}
    		else
    		{
    			for(Player player : playerstemp) 
    			{
    				MethodProvider.log("KGB Agent: " + API.callerName + " found!");
    				API.kgbAgent = player;
    				API.callerCmd = "announceBobsled";
    				break;
    			}
    		}
		}
    	return (int) ((double) 50 + API.rand2.nextInt(25) * API.sleepMod);
    }
}

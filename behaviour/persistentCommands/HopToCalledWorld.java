package org.lostclient.behaviour.persistentCommands;

import org.lostclient.api.Client;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.accessors.Worlds;
import org.lostclient.api.events.worldhopper.WorldHopperEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.input.Keyboard;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.api.wrappers.world.World;
import org.lostclient.api.wrappers.world.WorldHopper;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class HopToCalledWorld extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.HOP;
    }

    @Override
    public int onLoop() {
    	String temptext1 = null;
		if(API.callerCmd.toLowerCase().startsWith("hop world ")) temptext1 = API.callerCmd.toLowerCase().replace("hop world ", "");
		if(temptext1 == null && API.callerCmd.toLowerCase().startsWith("hop to world ")) temptext1 = API.callerCmd.toLowerCase().replace("hop to world ", "");
		if(temptext1 == null && API.callerCmd.toLowerCase().startsWith("hop to w")) temptext1 = API.callerCmd.toLowerCase().replace("hop to w", "");
		if(temptext1 == null && API.callerCmd.toLowerCase().startsWith("hop to ")) temptext1 = API.callerCmd.toLowerCase().replace("hop to ", "");
		if(temptext1 == null && API.callerCmd.toLowerCase().startsWith("hop ")) temptext1 = API.callerCmd.toLowerCase().replace("hop ", "");
		if(temptext1 == null && API.callerCmd.toLowerCase().startsWith("hop worlds to ")) temptext1 = API.callerCmd.toLowerCase().replace("hop worlds to ", "");
		if(temptext1 == null && API.callerCmd.toLowerCase().startsWith("w")) temptext1 = API.callerCmd.toLowerCase().replace("w", "");
		int i = Integer.parseInt(temptext1);
		API.callerCmd = "hop to world " + i;
		if(Worlds.getCurrentWorld() == i)
		{
			Keyboard.type("I\'m already on that world dumbass!!!",true);
			API.exitstop();
			return (int) ((double) 100 + API.rand2.nextInt(100) * API.sleepMod);
		}
		for(World w:Worlds.f2p())
		{
			if(w.getId() == i)
			{
				//check for un-logginable worlds, at least for fresh lvl 3 F2P accs
				if(Worlds.getWorld(w.getId()).isPvp() || 
						Worlds.getWorld(w.getId()).isHighRisk() ||
						Worlds.getWorld(w.getId()).isMember() || 
						Worlds.getWorld(w.getId()).isTournamentWorld() ||
						Worlds.getWorld(w.getId()).isLeagues() || 
						Worlds.getWorld(w.getId()).isDmm())
				{
					Keyboard.type("I'm not hopping to W"+i+"...",true);
					API.exitstop();
					return Sleep.Calculate(111,111);
				}
				
				if(Players.localPlayer().getTarget() != null)
				{
					Walking.setWalkFlag(Players.localPlayer().getTile());
					MethodProvider.tickSleep(1);
				}
				Widgets.closeAll();
				new WorldHopperEvent(i).setEventTimeout(20000).execute();
				Sleep.sleep(1111,111);
		    	if(!Client.isLoggedIn())
		    	{
		    		MethodProvider.sleepUntil(() -> Client.isLoggedIn(), 20000);
		    	}
		    	if(Worlds.getCurrentWorld() == i)
		    	{
		    		API.exitstop();
		    	}
			}
		}
    	return (int) ((double) 1111 + API.rand2.nextInt(3333) * API.sleepMod);
    }
}

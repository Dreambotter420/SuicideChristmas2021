package org.lostclient.behaviour.persistentCommands;

import java.util.List;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;

public class LineUp extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.LINEUP;
    }

    @Override
    public int onLoop() {		
		List<Player> followID = Players.all(p->p.getName().toLowerCase().contains(API.callerName.toLowerCase()));
		if(followID.isEmpty())
		{
			MethodProvider.log("line target: " + API.callerName + " not found! Waiting...");
			return (int) ((double) 600 + API.rand2.nextInt(300) * API.sleepMod);
		}
		else
		{
			for(Player player : followID) 
			{
				//if caller is the local player just stop
				if(player.equals(Players.localPlayer()))
				{
					API.exitstop();
					MethodProvider.log("Stopping calling ourselves!!");
					return (int) ((double) 600 + API.rand2.nextInt(300) * API.sleepMod);
				}
				API.interactTarget = player;
				API.callerCmd = "Line";
			}
		}
		
		//if cannot find Player object associated with callername, attempt to go to last tile or just stop
		if(API.interactTarget == null) 
		{
			MethodProvider.log("line target left! Exiting line...");
			API.exitstop(); 
    		return (int) ((double) 600 + API.rand2.nextInt(300) * API.sleepMod);
		}
    	if(API.newShape == true)
		{
			
    		int randint = API.rand2.nextInt(7);
    		switch(randint)
    		{
    		case(0):{API.yn = -3; break;}
    		case(1):{API.yn = -2; break;}
    		case(2):{API.yn = -1; break;}
    		case(3):{API.yn = 0; break;}
    		case(4):{API.yn = 1; break;}
    		case(5):{API.yn = 2; break;}
    		case(6):{API.yn = 3; break;}
    		}
    		API.newShape = false;
		}
    	int newX = API.interactTarget.getTile().getX();
    	int newY = API.interactTarget.getTile().getY() + API.yn;
    	int newZ = API.interactTarget.getTile().getPlane();
    	Tile followTile = new Tile(newX,newY,newZ);
    	if(!followTile.canReach())
    	{
    		API.newShape = true; 
    		return 50;
    	}
    	if(followTile.getX() != Players.localPlayer().getTile().getX() || followTile.getY() != Players.localPlayer().getTile().getY())
		{
			if(!Players.localPlayer().isMoving())
			{
				Walking.setWalkFlag(followTile);
				MethodProvider.tickSleep(1);
			}
		}
    	return (int) ((double) 50 + API.rand2.nextInt(25) * API.sleepMod);
    }
}

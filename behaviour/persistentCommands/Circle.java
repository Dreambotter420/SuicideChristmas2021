package org.lostclient.behaviour.persistentCommands;

import java.util.List;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;

public class Circle extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.CIRCLE;
    }

    @Override
    public int onLoop() {		
		List<Player> followID = Players.all(p -> p.getName().toLowerCase().contains(API.callerName.toLowerCase()));
		if(followID.isEmpty())
		{
			MethodProvider.log("circle target: " + API.callerName + " not found! Waiting...");
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
				API.callerCmd = "circle";
			}
		}
		
		//if cannot find Player object associated with callername, attempt to go to last tile or just stop
		if(API.interactTarget == null) 
		{
			MethodProvider.log("circle target left! Exiting circle...");
			API.exitstop();
    		return (int) ((double) 600 + API.rand2.nextInt(300) * API.sleepMod);
		}
    	if(API.newShape == true)
		{
			
    		int randint = API.rand2.nextInt(16);
    		switch(randint)
    		{
    		case(0):{API.xn = -2; API.yn = 2; break;}
    		case(1):{API.xn = -1; API.yn = 3; break;}
    		case(2):{API.xn = 0; API.yn = 3; break;}
    		case(3):{API.xn = 1; API.yn = 3; break;}
    		case(4):{API.xn = 2; API.yn = 2; break;}
    		case(5):{API.xn = 3; API.yn = 1; break;}
    		case(6):{API.xn = 3; API.yn = 0; break;}
    		case(7):{API.xn = -3; API.yn = -1; break;}
    		case(8):{API.xn = 2; API.yn = -2; break;}
    		case(9):{API.xn = 1; API.yn = -3; break;}
    		case(10):{API.xn = 0; API.yn = -3; break;}
    		case(11):{API.xn = -1; API.yn = -3; break;}
    		case(12):{API.xn = -2; API.yn = -2; break;}
    		case(13):{API.xn = -3; API.yn = -1; break;}
    		case(14):{API.xn = -3; API.yn = 0; break;}
    		case(15):{API.xn = -3; API.yn = 1; break;}
    		}
    		API.newShape = false;
		}
    	int newX = API.interactTarget.getTile().getX() + API.xn;
    	int newY = API.interactTarget.getTile().getY() + API.yn;
    	int newZ = API.interactTarget.getTile().getPlane();
    	Tile followTile = new Tile(newX,newY,newZ).getWalkableTile();
    	if(!followTile.canReach())
    	{
    		API.newShape = true; 
    		return (int) ((double) 50 + API.rand2.nextInt(25) * API.sleepMod);
    	}
    	if(followTile.getX() != Players.localPlayer().getTile().getX() || followTile.getY() != Players.localPlayer().getTile().getY())
		{
			if(!Players.localPlayer().isMoving())
			{
				Walking.setWalkFlag(followTile);
				MethodProvider.tickSleep(1);
				MethodProvider.sleep((int) ((double) 250 + API.rand2.nextInt(345) * API.sleepMod));
				
			}
		}
    	return (int) ((double) 50 + API.rand2.nextInt(25) * API.sleepMod);
    }
}

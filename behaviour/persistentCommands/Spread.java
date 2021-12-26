package org.lostclient.behaviour.persistentCommands;

import java.util.List;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class Spread extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.SPREAD;
    }

    @Override
    public int onLoop() {		
		List<Player> followID = Players.all(p -> p.getName().toLowerCase().contains(API.callerName.toLowerCase()));
		if(followID.isEmpty())
		{
			MethodProvider.log("SPREAD target: " + API.callerName + " not found! Waiting...");
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
				API.callerCmd = "spread";
			}
		}
		
		//if cannot find Player object associated with callername, attempt to go to last tile or just stop
		if(API.interactTarget == null) 
		{
			MethodProvider.log("SPREAD target left! Exiting SPREAD...");
			API.exitstop();
    		return Sleep.Calculate(111,1111);
		}
    	if(API.newShape == true)
		{
			
    		int randint = API.rand2.nextInt(25);
    		switch(randint)
    		{
    		case(0):{API.xn = -2; API.yn = -2; break;}
    		case(1):{API.xn = -1; API.yn = -2; break;}
    		case(2):{API.xn = 0; API.yn = -2; break;}
    		case(3):{API.xn = 1; API.yn = -2; break;}
    		case(4):{API.xn = 2; API.yn = -2; break;}
    		case(5):{API.xn = -2; API.yn = 1; break;}
    		case(6):{API.xn = 2; API.yn = 1; break;}
    		case(7):{API.xn = -2; API.yn = 0; break;}
    		case(8):{API.xn = -2; API.yn = 0; break;}
    		case(9):{API.xn = 2; API.yn = 1; break;}
    		case(10):{API.xn = -2; API.yn = 1; break;}
    		case(11):{API.xn = -2; API.yn = 2; break;}
    		case(12):{API.xn = -1; API.yn = 2; break;}
    		case(13):{API.xn = 0; API.yn = -2; break;}
    		case(14):{API.xn = 1; API.yn = -2; break;}
    		case(15):{API.xn = 2; API.yn = -2; break;}
    		case(16):{API.xn = 1; API.yn = 1; break;}
    		case(17):{API.xn = 1; API.yn = 0; break;}
    		case(18):{API.xn = 1; API.yn = -1; break;}
    		case(19):{API.xn = 0; API.yn = 1; break;}
    		case(20):{API.xn = 0; API.yn = -1; break;}
    		case(21):{API.xn = -1; API.yn = -1; break;}
    		case(22):{API.xn = -1; API.yn = 0; break;}
    		case(23):{API.xn = -1; API.yn = 1; break;}
    		case(24):{API.xn = 0; API.yn = 0; break;}
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
    		return (int) ((double) 50 + API.rand2.nextInt(20) * API.sleepMod);
    	}
    	if(followTile.getX() != Players.localPlayer().getTile().getX() || followTile.getY() != Players.localPlayer().getTile().getY())
		{
			if(!Players.localPlayer().isMoving())
			{
				Walking.setWalkFlag(followTile);
				MethodProvider.tickSleep(1);
			}
		}
    	return Sleep.Calculate(111, 333);
    }
}

package org.lostclient.utilities;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.Timer;
import org.lostclient.api.wrappers.map.Area;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;

public class Walkz {
	private static int destLimit = Sleep.Calculate(4, 2);
	public static boolean walkToArea(Area area,Tile walkableTile)
	{
		if(area.contains(Players.localPlayer())) return true;
		Timer timeout = new Timer();
		int timeoutLimit = Sleep.Calculate(10000, 2222);
		Walking.walk(walkableTile,()->
		{
			Sleep.sleep(10,333);
			if(timeout.elapsed() > timeoutLimit) return false;
			return area.contains(Players.localPlayer());
		});
		return area.contains(Players.localPlayer());
	}
	public static boolean walkToTileInRadius(Tile walkableTile,int radius)
	{
		Area area = walkableTile.getSurroundingArea(radius);
		
		if(area.contains(Players.localPlayer())) return true;
		
		Walking.walk(walkableTile,()->
		{
			Sleep.sleep(10,333);
			return area.contains(Players.localPlayer());
		});
		return area.contains(Players.localPlayer());
	}
	public static boolean walk(Tile toWalk) {
		if(toWalk.getWalkableTile() == null) {
			//lostclientapi failed
			MethodProvider.log("lostclientAPI failed: No walkable tile generated in generic walk tile function: " + 
					toWalk.getX()+", "+toWalk.getY());
			return false;
		}
		if(toWalk.distance() < destLimit)
		{
			destLimit = Sleep.Calculate(4, 2);
			return true;
		}
		else
		{
			Timer fuckingWalkingTimeout = new Timer();
			Walking.walk(toWalk,()->{
				Sleep.sleep(10,333);
				if(fuckingWalkingTimeout.elapsed() >= 20000)
				{
					return true;
				}
				return toWalk.distance() < destLimit;
			});
		}
		
		if(toWalk.distance() < destLimit)
		{
			destLimit = Sleep.Calculate(4, 2);
			return true;
		}
		else
		{
			//Dax pathing failed
			destLimit = Sleep.Calculate(4, 2);
			return false;
		}
	}
}

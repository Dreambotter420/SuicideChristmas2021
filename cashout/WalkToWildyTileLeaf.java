package org.lostclient.cashout;

import org.lostclient.api.Client;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Worlds;
import org.lostclient.api.events.worldhopper.WorldHopperEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.math.Calculations;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;

import net.runelite.api.GameState;

public class WalkToWildyTileLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return Players.localPlayer().getY() < 3523 ||   //not above / north of wildy ditch
        		Worlds.getCurrentWorld() != worldToHopAfterWildyTile; 
    }

    /**
	 * configure variables
	 */
	
	private int worldToHopAfterWildyTile = 574;
	private final Tile wildyTile = new Tile(3276, 3526, 0);
	@Override
	public int onLoop() 
	{
		//walk to wildy tile if distance to it greater than 3
		if(wildyTile.distance() >= 3)
		{
			Walking.walk(wildyTile);
		}
		//hop to specified world after reaching wildy tile
		else
		{
			MethodProvider.sleep(Calculations.nextGaussianRandom(10000, 5000));
			if(Worlds.getCurrentWorld() != worldToHopAfterWildyTile)
			{
				new WorldHopperEvent(worldToHopAfterWildyTile)
				.setEventTimeout(20000)
				.setEventCompleteCondition(() -> Client.getClient().getGameState() == GameState.LOGGED_IN &&
				Worlds.getCurrentWorld() == worldToHopAfterWildyTile, 18000)
				.execute();
				MethodProvider.tickSleep(5);
			}
		}
		return (int) Calculations.nextGaussianRandom(100, 50);
	}
	
}

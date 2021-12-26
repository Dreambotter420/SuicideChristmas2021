package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.events.InventoryEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.Timer;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;

public class KillRatRanged extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Now you have a bow and some arrows. Before you can use them you") &&
        		Players.localPlayer().getTarget() == null &&
        		!Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	/**
    	 * Open inventory
    	 * equip bow and arrows
    	 * stand at one of a few tiles
    	 * click one of the closest rats
    	 */
    	MethodProvider.log("KillRatRanged is valid");
    	if(Inventory.contains("Bronze arrow", "Shortbow")) { //random order of equipping
    		if(API.rand2.nextInt(200) < 77) {
    			Sleep.sleep(10, 111);
        		if(Inventory.contains("Shortbow")) new InventoryEvent("Shortbow","Wield").execute();
        		Sleep.sleep(10, 111);
        		if(Inventory.contains("Bronze arrow"))  new InventoryEvent("Bronze arrow", "Wield").execute();
        		Sleep.sleep(333, 777);
        	} else {
        		Sleep.sleep(10, 111);
        		if(Inventory.contains("Bronze arrow"))  new InventoryEvent("Bronze arrow", "Wield").execute();
        		Sleep.sleep(10, 111);
        		if(Inventory.contains("Shortbow")) new InventoryEvent("Shortbow","Wield").execute();
        		Sleep.sleep(333, 777);
        	}
    	} else {
    		if(Locations.RAT_TILE1.distance() > 1 &&
    				Locations.RAT_TILE2.distance() > 1 &&
    				Locations.RAT_TILE3.distance() > 1 &&
    				Locations.RAT_TILE4.distance() > 1 &&
    				Locations.RAT_TILE5.distance() > 1) {
    			int rand = API.rand2.nextInt(100);
    			Tile randTile = null;
    			if(rand < 5) randTile = Locations.RAT_TILE1;
    			else if(rand < 20) randTile = Locations.RAT_TILE2;
    			else if(rand < 35) randTile = Locations.RAT_TILE3;
    			else if(rand < 75) randTile = Locations.RAT_TILE4;
    			else if(rand <= 100) randTile = Locations.RAT_TILE5;
    			if(randTile == null) {
    				MethodProvider.log("Cannot find RAT_TILE scripting error");
    			}
    			else
    			{
    				Timer timer = new Timer();
        			while(randTile.distance() > 1.5 && timer.elapsed() < 15000) {
        				Sleep.sleep(10, 444);
        				if(Players.localPlayer().isMoving()) {
        					Sleep.sleep(10, 444);
        					continue;
        				}
        				Walking.setWalkFlag(randTile);
        				MethodProvider.tickSleep(1);
        				Sleep.sleep(10, 444);
        			}
    			}
    			
    		} else { //in range of rats and have arrows equipped
    			ClickRat.clickRat();
    		}
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

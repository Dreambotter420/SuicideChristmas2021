package org.lostclient.behaviour.TutorialIsland;

import java.util.Collections;
import java.util.List;

import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.events.InventoryEvent;
import org.lostclient.api.events.magic.CastSpellEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.Timer;
import org.lostclient.api.wrappers.interactives.NPC;
import org.lostclient.api.wrappers.magic.spellbooks.Standard;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;

public class CastOnAChicken extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Look for the Wind Strike spell in your magic interface. Click on this spell to select it and then click on a chicken to cast it. Talk to the instructor if you need more runes.") &&
        		Players.localPlayer().getTarget() == null &&
        		!Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	/**
    	 * stand at a random spot in castable area
    	 * click one of the chickens
    	 */
    	MethodProvider.log("CastOnAChicken is valid");
    	if(!Locations.CHICKENS_CASTABLE.contains(Players.localPlayer()))
    	{
    		Tile randTile = Locations.CHICKENS_CASTABLE.getRandomTile();
    		if(randTile == null) {
    			MethodProvider.log("Cannot find CHICKEN_COOP scripting error");
    		}
    		else
    		{
    			Timer timer = new Timer();
    			while(randTile.distance() > 1.5 && timer.elapsed() < 15000) {
    				Sleep.sleep(10, 444);
    				if(Players.localPlayer().isMoving()) {
        				MethodProvider.tickSleep(1);
    					Sleep.sleep(10, 444);
    					continue;
    				}
    				Walking.setWalkFlag(randTile);
    				MethodProvider.tickSleep(1);
    				Sleep.sleep(10, 444);
    			}
    		}
		}
    	else 
		{ //in range of chikken
			List<NPC> rats = NPCs.all(rat -> rat.getName().equals("Chicken") && Locations.CHICKEN_COOP.contains(rat));
	    	Collections.shuffle(rats);
	    	if(new CastSpellEvent(Standard.WIND_STRIKE).on(rats.get(0)).executed())
	    	{
	    		MethodProvider.tickSleep(1);
	    		Sleep.sleep(555,999);
	    	}
		}
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

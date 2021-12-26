package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;

public class ClickLadder_Underground extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("To move on, click on the indicated ladder. If you need to go over any of what you learnt here, just talk to the combat instructor and he");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickLadder_Underground is valid");
    	if(!Players.localPlayer().isMoving()) {
    		if(Locations.RAT_CAGE.contains(Players.localPlayer()))
    		{
    			ClickGate3_RatCage.OpenRatCageGate();
    			return 5;
    		}
    		if(API.rand2.nextInt(100) < 77) Sleep.sleep(222,555);
    		if(!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating()) 
    		{
    			new EntityInteractEvent(GameObjects.closest(door -> 
    			door.getName().equals("Ladder") && 
    			door.getX() == 3111 && 
    			door.getY() == 9526),"Climb-up").execute();
        		MethodProvider.tickSleep(1);
        	}
    	}
    	Sleep.sleep(222, 1111);
    	return 5;
    }
}

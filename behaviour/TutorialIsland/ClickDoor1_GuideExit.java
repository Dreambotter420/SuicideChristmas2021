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

public class ClickDoor1_GuideExit extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("s time to meet your first instructor. To continue, all you need to do is click on the door. It") &&
        		Locations.GUIDE_AREA.contains(Players.localPlayer());
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickDoor1 is valid");
    	if(!Players.localPlayer().isMoving()) {
    		if(API.rand2.nextInt(100) < 77) Sleep.sleep(222,555);
    		if(!Players.localPlayer().isMoving()) 
    		{
        		new EntityInteractEvent(GameObjects.closest(door -> 
    			door.getName().equals("Door") && 
    			door.getX() == 3098 && 
    			door.getY() == 3107),"Open").execute();
        		MethodProvider.tickSleep(1);
        	}
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(222, 1111);
    	return 5;
    }
}

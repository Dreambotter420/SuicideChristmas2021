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

public class ClickDoor5_AccountGuideExit extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Continue through the next door.") &&
        		Locations.ACCOUNTGUIDE.contains(Players.localPlayer()) &&
        		!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickDoor5_AccountGuideExit is valid");
    	clickDoor5();
    	return Sleep.Calculate(555,444);
    }
    public static void clickDoor5()
    {
    	if(new EntityInteractEvent(GameObjects.closest(g -> 
    	g.getName().equals("Door") &&
    	g.getX() == 3130 &&
    	g.getY() == 3124),"Open").executed()) {
    		MethodProvider.tickSleep(1);
    	}
    }
}

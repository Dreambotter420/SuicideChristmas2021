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
import org.lostclient.utilities.Walkz;

public class ClickDoor2_ChefEnter extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Follow the path until you get to the door with the yellow arrow above it. Click on the door to open it. Remember that you can also move around by clicking on the minimap in the top right.") &&
        		Locations.GATE_AREA1.contains(Players.localPlayer()) && !Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickDoor2 is valid");
    	if(!Players.localPlayer().isMoving()) {
    		if(API.rand2.nextInt(100) < 77) Sleep.sleep(222,555);
    		if(!Players.localPlayer().isMoving()) {
        		if(new EntityInteractEvent(GameObjects.closest(door ->  door.getName().equals("Door") && door.getX() == 3079 && door.getY() == 3084),"Open").executed()) 
        		{
        			MethodProvider.tickSleep(1);
        		}
        		else 
        		{
        			Walkz.walk(GameObjects.closest(door ->  door.getName().equals("Door") && door.getX() == 3079 && door.getY() == 3084).getTile());
        		}
        	}
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(11,1111);
    	return 5;
    }
}

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


public class ClickDoor3_ChefExit extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("ve baked your first loaf of bread. As you gain experience in Cooking, you will be able to make other things like pies and cakes. You can now use the next door to move on. If you need a recap on anything, talk to the master chef.") &&
        		Locations.COOKING_AREA.contains(Players.localPlayer());
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickDoor3 is valid");
    	if(!Players.localPlayer().isMoving()) {
    		if(API.rand2.nextInt(100) < 77) Sleep.sleep(222,555);
    		if(!Players.localPlayer().isMoving()) {
        		if(new EntityInteractEvent(GameObjects.closest(door ->  door.getName().equals("Door") && door.getX() == 3072 && door.getY() == 3090),"Open").executed())
        		{
        			MethodProvider.tickSleep(1);
        		}
        	}
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(222, 555);
    	return 5;
    }
}

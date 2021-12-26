package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;

public class ClickChef extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Talk to the chef indicated. He will teach you the more advanced aspects of Cooking such as combining ingredients") &&
        		Locations.COOKING_AREA.contains(Players.localPlayer()) && !Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickChef is valid");
    	if(new EntityInteractEvent(NPCs.closest("Master Chef"),"Talk-to").executed()) {
    		Sleep.sleep(111,444);
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(222, 555);
    	return 5;
    }
}

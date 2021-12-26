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

public class ClickCaveEntrance extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("s time to enter some caves. Click on the ladder to go down to the next area.") &&
        		Locations.QUEST_AREA.contains(Players.localPlayer()) && 
        		!Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickCaveEntrance is valid");
    	clickCaveEntrance();
    	return 5;
    }
    public static void clickCaveEntrance()
    {
    	if(new EntityInteractEvent(GameObjects.closest(ladder -> ladder.getName().equals("Ladder") && ladder.getX() == 3088 && ladder.getY() == 3119),"Climb-down").executed()) {
    		MethodProvider.tickSleep(1);
    		MethodProvider.sleepUntil(() -> !Locations.QUEST_AREA.contains(Players.localPlayer()), 5000,111);
    	}
    	Sleep.sleep(10, 444);
    }
}

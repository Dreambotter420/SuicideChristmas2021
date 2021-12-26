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

public class ClickQuestDoor extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("When navigating the world, you can either run or walk. Running is faster but you can't run for long as you'll soon run out of energy. You can use the flashing orb next to the minimap to toggle running. Why not try it as you head to the next section?") &&
        		(Locations.nav3.contains(Players.localPlayer()) || 
        				Locations.nav2.contains(Players.localPlayer()) ||
        				Locations.nav1.contains(Players.localPlayer())) &&
        		!Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickQuestDoor is valid");
    	if(new EntityInteractEvent(GameObjects.closest(stepStone -> stepStone.getName().equals("Door") && stepStone.getX() == 3086 && stepStone.getY() == 3126),"Open").executed()) {
    		MethodProvider.tickSleep(1);
    		MethodProvider.sleepUntil(() -> Locations.QUEST_AREA.contains(Players.localPlayer()), 5555,111);
    	}
    	Sleep.sleep(222, 555);
    	return 5;
    }
}

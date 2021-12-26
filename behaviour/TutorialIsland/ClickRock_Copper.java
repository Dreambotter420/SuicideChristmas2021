package org.lostclient.behaviour.TutorialIsland;

import java.util.Collections;
import java.util.List;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.GameObject;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;

public class ClickRock_Copper extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Now that you have some tin ore, you just need some copper. To mine a rock, all you need to do is click on it. If you") &&
        		!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
		MethodProvider.log("ClickRock_Copper is valid");
    	API.randomAFK();
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(111,1111);
    	if(!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating())
    	{
        	List<GameObject> rocks = GameObjects.all(rock -> rock.getID() == 10079 && Locations.ACCEPTABLE_COPPERS.contains(rock));
        	Collections.shuffle(rocks);
        	if(new EntityInteractEvent(rocks.get(0),"Mine").executed()) {
        		MethodProvider.tickSleep(1);
        	}
        	Sleep.sleep(10, 444);
    	}
    	return 5;
    }
}

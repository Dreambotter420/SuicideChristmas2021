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

public class ClickRock_Tin extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("s quite simple really. To mine a rock, all you need to do is click on it. First up, try mining some tin.") &&
        		!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickRock_Tin is valid");
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(111,1111);
    	if(!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating())
    	{
    		List<GameObject> rocks = GameObjects.all(rock -> rock.getID() == 10080 && Locations.ACCEPTABLE_TINS.contains(rock));
        	Collections.shuffle(rocks);
        	if(new EntityInteractEvent(rocks.get(0),"Mine").executed()) {
        		MethodProvider.tickSleep(1);
        	}
        	Sleep.sleep(10, 444);
    	}
    	return 5;
    }
}

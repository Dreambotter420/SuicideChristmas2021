package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickGate2_Underground extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("s time to move on. Go through the gates shown by the arrow. Remember, you may need to move the camera to see your surroundings. Speak to the mining instructor for a recap at any time.") &&
        		!Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickGate2_Underground is valid");
    	if(new EntityInteractEvent(GameObjects.closest(gate -> gate.getName().equals("Gate") && gate.getX() == 3094 && gate.getY() == 9502),"Open").executed()) {
    		MethodProvider.tickSleep(1);
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

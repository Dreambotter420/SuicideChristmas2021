package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickGate3_RatCage extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("This is your combat interface. From here, you can select the attack style that you") &&
        		!Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickGate3_Underground is valid");
    	OpenRatCageGate();
    	
    	Sleep.sleep(10, 444);
    	return 5;
    }
    public static void OpenRatCageGate() {
    	if(new EntityInteractEvent(GameObjects.closest(gate -> gate.getName().equals("Gate") && gate.getX() == 3111 && gate.getY() == 9518),"Open").executed()) {
    		MethodProvider.tickSleep(1);
    	}
    }
}

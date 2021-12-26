package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;
import org.lostclient.utilities.Walkz;

public class ClickGate1 extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("ve just cooked your first meal! Speak to the survival expert if you want a recap, otherwise you can move on. Click on the gate shown and follow the path.") &&
        		Locations.FISHING_AREA.contains(Players.localPlayer()) && !Players.localPlayer().isMoving();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickGate1 is valid");
    	if(GameObjects.closest(gate -> gate.getName().equals("Gate") && gate.getX() == 3089 && gate.getY() == 3091).interact("Open")) {
    		Sleep.sleep(666,999);
    	}
    	else {
    		Walkz.walk(GameObjects.closest(gate -> gate.getName().equals("Gate") && gate.getX() == 3089 && gate.getY() == 3091).getTile());
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

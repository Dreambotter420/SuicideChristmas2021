package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickBankBooth extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("This is the Bank of Gielinor, where you can store all your most valued items. To open your bank, just click on the indicated booth.") &&
        		!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickBankBooth is valid");
    	if(new EntityInteractEvent(GameObjects.closest(g -> 
    	g.getName().equals("Bank booth") &&
    	g.getX() == 3122 && 
    	g.getY() == 3124),"Use").executed()) {
    		Sleep.sleep(555,444);
    	}
    	MethodProvider.tickSleep(1);
    	return 5;
    }
}

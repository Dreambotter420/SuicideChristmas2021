package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.events.WidgetEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickPollBooth extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("To deposit something from your inventory, just click on it. You can withdraw things in the same way. To continue, close the bank and click on the indicated poll booth.") &&
        		!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickPollBooth is valid");
    	if(Widgets.getWidgetChild(12, 2, 11) != null &&
    			Widgets.getWidgetChild(12, 2, 11).getActions()[0].contains("Close"))
    	{
    		new WidgetEvent(Widgets.getWidgetChild(12, 2, 11),"Close").execute();
    		MethodProvider.tickSleep(1);
    		return Sleep.Calculate(111,1111);
    	}
    	
    	if(new EntityInteractEvent(GameObjects.closest(g -> 
    	g.getName().equals("Poll booth") &&
    	g.getX() == 3119 && 
    	g.getY() == 3121),"Use").executed()) {
    		MethodProvider.tickSleep(1);
    		
    	}
    	return Sleep.Calculate(555,444);
    }
}

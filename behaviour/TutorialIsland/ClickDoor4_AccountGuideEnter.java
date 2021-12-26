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

public class ClickDoor4_AccountGuideEnter extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Polls are run periodically to let the Old School RuneScape community vote on how the game should - or shouldn\'t - change. When you\'re ready, move on through the door indicated.") &&
        		!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickDoor4_AccountGuideEnter is valid");
    	if(Widgets.getWidgetChild(310, 2, 11) != null &&
    			Widgets.getWidgetChild(310, 2, 11).getActions()[0].contains("Close"))
    	{
    		new WidgetEvent(Widgets.getWidgetChild(310, 2, 11),"Close").execute();
    		MethodProvider.tickSleep(1);
    		return Sleep.Calculate(111,1111);
    	}
    	
    	if(new EntityInteractEvent(GameObjects.closest(g -> 
    	g.getName().equals("Door") &&
    	g.getX() == 3125 && 
    	g.getY() == 3124),"Open").executed()) {
    		MethodProvider.tickSleep(1);
    	}
    	MethodProvider.tickSleep(1);
    	return Sleep.Calculate(555,444);
    }
}

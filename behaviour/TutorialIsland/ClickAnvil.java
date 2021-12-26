package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickAnvil extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		(Widgets.getWidgetChild(263,1,0).getText().contains("ll need a hammer and enough metal bars to make the desired item, as well as a handy anvil.")) &&
        		!Players.localPlayer().isMoving();
    }
    
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickAnvil is valid");
    	MethodProvider.tickSleep(2);
    	if(Widgets.getWidgetChild(312,9) != null && 
        		Widgets.getWidgetChild(312,9).getActions()[0].contains("Smith")) 
    	{
    		return Sleep.Calculate(111,111);
    	}
    	
    	if(new EntityInteractEvent(GameObjects.closest("Anvil"),"Smith").executed())
    	{
    		MethodProvider.tickSleep(1);
    	}
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

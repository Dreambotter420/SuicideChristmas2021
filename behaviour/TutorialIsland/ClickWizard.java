package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;
import org.lostclient.utilities.Walkz;

public class ClickWizard extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		(Widgets.getWidgetChild(263,1,0).getText().contains("Follow the path to the wizard") || 
        				Widgets.getWidgetChild(263,1,0).getText().contains("re nearly finish with the tutorial. All you need to do now") || 
        				Widgets.getWidgetChild(263,1,0).getText().contains("re almost finished on tutorial island. Pass through the door")) &&
        		!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickWizard is valid");
    	if(Locations.CHURCH_INSIDE.contains(Players.localPlayer()))
    	{
    		if(new EntityInteractEvent(GameObjects.closest(g -> 
        	g.getName().equals("Door") &&
        	g.getX() == 3122 &&
        	g.getY() == 3102),"Open").executed()) {
        		MethodProvider.tickSleep(1);
        	}
    	}
    	else if(Locations.WIZARD_TILE.distance() > 9)
    	{
    		Walkz.walk(Locations.WIZARD_TILE);
    	}
    	else
    	{
    		new EntityInteractEvent(NPCs.closest("Magic Instructor"),"Talk-to").executed();
    		MethodProvider.tickSleep(1);
    	}
    	MethodProvider.tickSleep(1);
    	return Sleep.Calculate(555,444);
    }
}

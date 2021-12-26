package org.lostclient.behaviour.TutorialIsland;

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

public class ClickBrotherBrace extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		(Widgets.getWidgetChild(263,1,0).getText().contains("Talk with Brother Brace and he\'ll tell you about prayers.") ||
        				Widgets.getWidgetChild(263,1,0).getText().contains("These two lists can be very helpful for keeping track of your friends or for blocking people you don") ||
        				Widgets.getWidgetChild(263,1,0).getText().contains("Once inside talk to the monk. He\'ll tell you all about the Prayer skill.")) &&
        		!Players.localPlayer().isMoving();
    }
    
    
    
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickBrotherBrace is valid");
    	if(Locations.ACCOUNTGUIDE.contains(Players.localPlayer()))
    	{
    		ClickDoor5_AccountGuideExit.clickDoor5();
    		return Sleep.Calculate(111,1111);
    	}
    	if(Locations.CHURCH_TILE.distance() > 7) {
    		if(Walkz.walk(Locations.CHURCH_TILE)) Sleep.sleep(50,1111);
    		return 5;
    	}
    	if(new EntityInteractEvent(NPCs.closest("Brother Brace"), "Talk-to").executed())
		{
			MethodProvider.tickSleep(2);
			Sleep.sleep(444,666);
		}
    
    	Sleep.sleep(10, 222);
    	return 5;
    }
}

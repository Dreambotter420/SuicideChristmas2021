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

public class ClickMiner extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		(Widgets.getWidgetChild(263,1,0).getText().contains("get you a weapon, or more to the point, you can make your first weapon yourself. Don") || Widgets.getWidgetChild(263,1,0).getText().contains("ve made a bronze bar! Speak to the mining instructor and he") ) &&
        		!Players.localPlayer().isMoving();
    }
    
    
    
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickMiner is valid");
    	if(Locations.QUEST_AREA.contains(Players.localPlayer()))
    	{
    		ClickCaveEntrance.clickCaveEntrance();
    	}
    	if(Locations.MinerTile.distance() > 7) {
    		if(Walkz.walk(Locations.MinerTile)) Sleep.sleep(50,1111);
    		return 5;
    	}
    	if(new EntityInteractEvent(NPCs.closest("Mining Instructor"), "Talk-to").executed())
		{
			MethodProvider.tickSleep(1);
			Sleep.sleep(444,666);
		}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 222);
    	return 5;
    }
}

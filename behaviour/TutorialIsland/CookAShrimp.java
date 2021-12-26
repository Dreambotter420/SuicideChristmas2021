package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.events.InventoryEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;

public class CookAShrimp extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("s time to get cooking. To do so, click on the shrimp in your inventory. Then, with the shrimp highlighted, click on a fire to cook them. If you look at the top left of the screen, you") &&
        		!Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("CookAShrimp is valid");
    	if(GameObjects.closest(fire -> fire.getName().equals("Fire") &&
    			Locations.FISHING_AREA.contains(fire)) == null) 
    	{
    		if(!Inventory.contains("Logs")) {
    			ClickTree.ClickATree();
    		} else {
    			MakeAFire.makeAFire();
    		}
    	} else 
    	{ //There is a fire to cook here
    		if(new InventoryEvent("Raw shrimps").on(GameObjects.closest("Fire")).executed()) {
				MethodProvider.tickSleep(1);
			} 
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	if(API.rand2.nextInt(100) < 1 && !Tabs.isOpen(Tab.INVENTORY)) Tabs.open(Tab.INVENTORY);
    	Sleep.sleep(1300, 888);
    	return 5;
    }
}

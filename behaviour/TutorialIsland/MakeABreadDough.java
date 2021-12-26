package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.events.InventoryEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class MakeABreadDough extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("This is the base for many meals. To make dough you must mix flour with water. To do so, click on the flour in your inventory. Then, with the flour highlighted, click on the water to combine them into dough.");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("MakeABreadDough is valid");
    	if(API.rand2.nextInt(100) < 59) {
			if(new InventoryEvent("Bucket of water").on(Inventory.get("Pot of flour")).executed()) {
				MethodProvider.tickSleep(1);
			} 
    	} else if(new InventoryEvent("Pot of flour").on(Inventory.get("Bucket of water")).executed()) {
			MethodProvider.tickSleep(1);
		}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(111, 555);
    	return 5;
    }
}

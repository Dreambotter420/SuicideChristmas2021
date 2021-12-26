package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.events.InventoryEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class EquipSwordShield extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("To unequip an item, go to your worn inventory and click on the item. Alternatively, equipping a new item into the same slot will unequip the old one. Try this out now by swapping your dagger for the sword and shield");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("EquipSwordShield is valid");
    	if(API.rand2.nextInt(200) < 173) {
    		if(Inventory.contains("Bronze sword") &&
    				new InventoryEvent("Bronze sword","Wield").executed()) Sleep.sleep(50,666);
    		if(Inventory.contains("Wooden shield") && 
    				new InventoryEvent("Wooden shield","Wield").executed()) Sleep.sleep(50,666);
    	} else { 
    		if(Inventory.contains("Wooden shield") && 
    				new InventoryEvent("Wooden shield","Wield").executed()) Sleep.sleep(50,666);
    		if(Inventory.contains("Bronze sword") &&
    				new InventoryEvent("Bronze sword","Wield").executed()) Sleep.sleep(50,666);
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

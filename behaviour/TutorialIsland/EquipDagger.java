package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.InventoryEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class EquipDagger extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("You can see what items you are wearing in the worn inventory to the left of the screen, with their combined statistics on");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("EquipDagger is valid");
    	String action = "Wield";
    	if(Widgets.getWidgetChild(84,3,1) != null && 
    			Widgets.getWidgetChild(84,3,1).containsText("Equip Your Character..."))
    	{
    		action = "Equip";
    	}
    	if(new InventoryEvent("Bronze dagger",action).executed()) Sleep.sleep(50,333);
    	if(API.rand2.nextInt(200) < 11) {
    		if(Widgets.getWidgetChild(83,3,11) != null && 
    				Widgets.getWidgetChild(83,3,11).isVisible() && 
    				Widgets.getWidgetChild(83,3,11).interact()) {
    			Sleep.sleep(555,444);
    		}
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

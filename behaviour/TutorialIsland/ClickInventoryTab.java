package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickInventoryTab extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("ll need to open your inventory. To do so, click on the flashing backpack icon to the right hand side of your screen.");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickInventoryTab is valid");
    	Tabs.open(Tab.INVENTORY);
    	MethodProvider.tickSleep(1);
    	if(API.rand2.nextInt(100) < 2) 
    		{
    		new EntityInteractEvent(NPCs.closest("Fishing spot"),"Net").execute();
    		MethodProvider.tickSleep(1);
    		}
    	Sleep.sleep(222, 555);
    	return 5;
    }
}

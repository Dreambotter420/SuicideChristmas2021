package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickEquipmentTab extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("You now have access to a new interface. Click on the flashing icon of a man, the one to the right of your backpack icon.");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickEquipmentTab is valid");
    	if(Tabs.open(Tab.EQUIPMENT)) Sleep.sleep(10,1111);
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

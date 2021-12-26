package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickPrayerTab extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Click on the flashing icon to open the Prayer menu.");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickPrayerTab is valid");
    	Tabs.open(Tab.PRAYER);
		MethodProvider.tickSleep(1);
    	Sleep.sleep(50,111);
    	return 5;
    }
}

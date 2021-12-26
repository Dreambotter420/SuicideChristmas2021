package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickMagicTab extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Open up the magic interface by clicking on the flashing icon");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickMagicTab is valid");
    	Tabs.open(Tab.MAGIC);
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(222, 555);
    	return 5;
    }
}

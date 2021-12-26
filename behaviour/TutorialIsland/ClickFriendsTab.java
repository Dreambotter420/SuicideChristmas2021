package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickFriendsTab extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("You should now see another new icon. Click on the flashing face icon to open your friends and ignore lists.");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickFriendsTab is valid");
    	Sleep.sleep(50,555);
		Tabs.open(Tab.FRIEND);
		MethodProvider.tickSleep(1);
    	Sleep.sleep(50,111);
    	return 5;
    }
}

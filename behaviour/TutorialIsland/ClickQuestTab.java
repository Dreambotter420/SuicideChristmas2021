package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickQuestTab extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Click on the flashing icon to the left of your inventory.");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickQuestTab is valid");
    	Tabs.open(Tab.QUEST);
		MethodProvider.tickSleep(1);
    	Sleep.sleep(50,111);
    	return 5;
    }
}

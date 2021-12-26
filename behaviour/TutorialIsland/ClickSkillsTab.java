package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickSkillsTab extends Leaf{

    @Override
    public boolean isValid() {
        return (Widgets.getWidgetChild(193,2) != null && Widgets.getWidgetChild(193,2).isVisible() &&
        		Widgets.getWidgetChild(193,2).getText().contains("You manage to catch some shrimp.") ||
        		Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Click on the flashing bar graph icon near the inventory button to see your skills menu."));
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickSkillsTab is valid");
    	if(Widgets.getWidgetChild(193,2) != null && 
    			Widgets.getWidgetChild(193,2).isVisible() &&
        		Widgets.getWidgetChild(193,2).getText().contains("You manage to catch some shrimp.")) {
    		if(API.rand2.nextInt(200) < 33) {
    			Tabs.open(Tab.SKILL);
    			MethodProvider.tickSleep(1);
    		} else {
    			Widgets.getWidgetChild(193,0,2).interact();
    		}
    	} else if(Widgets.getWidgetChild(263,1,0) != null && 
    			Widgets.getWidgetChild(263,1,0).isVisible() && 
    			Widgets.getWidgetChild(263,1,0).getText().contains("Click on the flashing bar graph icon near the inventory button to see your skills menu.")) {
    		Tabs.open(Tab.SKILL);
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(222, 555);
    	return 5;
    }
}

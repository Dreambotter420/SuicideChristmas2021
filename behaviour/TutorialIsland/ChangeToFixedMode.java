package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.WidgetEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ChangeToFixedMode extends Leaf {

    @Override
    public boolean isValid() {
        return (Widgets.getWidgetChild(161, 46) != null && Widgets.getWidgetChild(161, 46).isVisible()) || 
        		(Widgets.getWidgetChild(164, 40) != null && Widgets.getWidgetChild(164, 40).isVisible());
    }
    @Override
    public int onLoop() {
    	API.randomAFK();

    	MethodProvider.log("ChangeToFixedMode is valid");
    	if(Widgets.getWidgetChild(116,2).containsText("Controls Settings"))
    	{
    		new WidgetEvent(Widgets.getWidgetChild(116, 112),"Display").execute();
    		MethodProvider.tickSleep(1);
    		return Sleep.Calculate(666,333);
    	}
    	else if(Widgets.getWidgetChild(116,2).containsText("Display Settings"))
    	{//on correct mini-tab to change to fixed mode
    		if(Widgets.getWidgetChild(116, 84, 1) != null)
    		{
    			new WidgetEvent(Widgets.getWidgetChild(116, 84, 1),"Select").execute();
        		MethodProvider.tickSleep(1);
        		return Sleep.Calculate(666,333);
    		}
    		else
    		{
    			//need to open Game Client Layout selection box from within Display minitab 
    			new WidgetEvent(Widgets.getWidgetChild(116, 27, 4)).execute();
    			MethodProvider.tickSleep(2);
        		return Sleep.Calculate(666,1111);
    		}
    	}
    	Sleep.sleep(50,555);
		Tabs.open(Tab.ACCOUNT);
		MethodProvider.tickSleep(1);
    	return Sleep.Calculate(111,1111);
    }
}

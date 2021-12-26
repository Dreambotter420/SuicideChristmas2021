package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.WidgetEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickWornEquipment extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("This is your worn inventory. Here you can see what items you have equipped. In the bottom left corner, you will notice a flashing button with a shield and helmet");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickWornEquipment is valid");
    	if(new WidgetEvent(Widgets.getWidgetChild(387,1)).executed()) 
    		{
    		MethodProvider.tickSleep(1);
    		Sleep.sleep(600,333);
    		}
    	Sleep.sleep(10, 444);
    	return 5;
    }
}

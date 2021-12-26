package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class CookABread extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("Now you have made the dough, you can bake it into some bread. To do so, just click on the indicated range.");
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("CookABread is valid");
    	if(new EntityInteractEvent(GameObjects.closest("Range"),"Cook").executed()) {
    		Sleep.sleep(666,999);
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(111, 555);
    	return 5;
    }
}

package org.lostclient.behaviour.misc;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickWidgetsOpen extends Leaf {

    @Override
    public boolean isValid() {
        return (Widgets.getWidgetChild(219,1,2) != null && 
        		Widgets.getWidgetChild(219,1,2).getText().contains("How do I pay a gravestone fee") && 				
        		!Widgets.getWidgetChild(219,1,2).getText().contains("str")) || 
        		(Widgets.getWidgetChild(219,1,3) != null && 
        		Widgets.getWidgetChild(219,1,3).getText().contains("How long do I have to return to my gravestone") && 				
        		!Widgets.getWidgetChild(219,1,3).getText().contains("str")) || 
        		(Widgets.getWidgetChild(219,1,4) != null && 
        		Widgets.getWidgetChild(219,1,4).getText().contains("How do I know what will happen to my items when I die") && 				
        		!Widgets.getWidgetChild(219,1,4).getText().contains("str")) ||
        		(Widgets.getWidgetChild(219,1,5) != null && 
        		Widgets.getWidgetChild(219,1,5).getText().contains("I think I\'m done here") && 				
        		!Widgets.getWidgetChild(219,1,5).getText().contains("str")) || 
        		(Widgets.getWidgetChild(231,5) != null && 
        		Widgets.getWidgetChild(231,5).getText().contains("Would you like me to remind you about gravestones?"));
    }

    @Override
    public int onLoop() {
    	if(Widgets.getWidgetChild(219,1,2) != null && 
        		Widgets.getWidgetChild(219,1,2).getText().contains("How do I pay a gravestone fee") && 				!Widgets.getWidgetChild(219,1,2).getText().contains("str"))
    	{
    		Widgets.getWidgetChild(219,1,2).interact();
    		MethodProvider.tickSleep(1);
    		return Sleep.Calculate(111,1111);
    	}
    	if(Widgets.getWidgetChild(219,1,3) != null && 
        		Widgets.getWidgetChild(219,1,3).getText().contains("How long do I have to return to my gravestone") && 				
        		!Widgets.getWidgetChild(219,1,3).getText().contains("str"))
    	{
    		Widgets.getWidgetChild(219,1,3).interact();
    		MethodProvider.tickSleep(1);
    		return Sleep.Calculate(111,1111);
    	}
    	if(Widgets.getWidgetChild(219,1,4) != null && 
        		Widgets.getWidgetChild(219,1,4).getText().contains("How do I know what will happen to my items when I die") && 				
        		!Widgets.getWidgetChild(219,1,4).getText().contains("str"))
    	{
    		Widgets.getWidgetChild(219,1,4).interact();
    		MethodProvider.tickSleep(1);
    		return Sleep.Calculate(111,1111);
    	}
    	if(Widgets.getWidgetChild(219,1,5) != null && 
        		Widgets.getWidgetChild(219,1,5).getText().contains("I think I\'m done here") && 				
        		!Widgets.getWidgetChild(219,1,5).getText().contains("str"))
    	{
    		Widgets.getWidgetChild(219,1,5).interact();
    		MethodProvider.tickSleep(1);
    		return Sleep.Calculate(111,1111);
    	}
    	if(Widgets.getWidgetChild(231,5) != null && 
        		Widgets.getWidgetChild(231,5).getText().contains("Would you like me to remind you about gravestones?"))
    	{
    		new EntityInteractEvent(GameObjects.closest(p -> p.getName().contains("Portal") && p.containsAction("Use")),"Use")
    		.setEventTimeout(Sleep.Calculate(5555,1111))
    		.execute();
    		MethodProvider.tickSleep(1);
    	}
    	return(int) ((double) 1400 + API.rand2.nextInt(2000) * API.sleepMod);
    }

}
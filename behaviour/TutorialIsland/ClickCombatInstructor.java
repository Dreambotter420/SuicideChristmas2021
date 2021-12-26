package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;

public class ClickCombatInstructor extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		(Widgets.getWidgetChild(263,1,0).getText().contains("In this area you will find out about melee and ranged combat. Speak to the guide and he will tell you all about it.") || Widgets.getWidgetChild(263,1,0).getText().contains("re now holding your dagger. Clothes, armour, weapons and more are equipped like this. You can unequip")  || 
        				Widgets.getWidgetChild(263,1,0).getText().contains("Pass through the gate and talk to the combat instructor. He will give you your next task.")) &&
        		!Players.localPlayer().isMoving();
    }
    
    
    
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickCombatInstructor is valid");
    	
    	//Equipment menu open - click the close button
    	if(Widgets.getWidgetChild(83,3,11) != null && 
				Widgets.getWidgetChild(83,3,11).isVisible() && 
				Widgets.getWidgetChild(83,3,11).interact()) {
			Sleep.sleep(50,444);
		}
    	
    	if(Locations.RAT_CAGE.contains(Players.localPlayer())) {
    		ClickGate3_RatCage.OpenRatCageGate();
    		return Sleep.Calculate(111, 1111);
    	}
    	if(new EntityInteractEvent(NPCs.closest("Combat Instructor"),"Talk-to").executed())  {
    		MethodProvider.tickSleep(1);
    		MethodProvider.sleepUntil(Dialogues::inDialogue, Sleep.Calculate(5555,1111));
    	}
    	MethodProvider.tickSleep(1);
    	return Sleep.Calculate(1111,1111);
    }
}

package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.hintarrows.HintArrow;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;
import org.lostclient.utilities.Walkz;

public class ClickHintArrow extends Leaf {

    @Override
    public boolean isValid() {
        return HintArrow.getPointed() != null &&
        		Players.localPlayer().getTarget() == null &&
        		!Players.localPlayer().isMoving() && 
        		!Dialogues.inDialogue();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickHintArrow is valid");
    	MethodProvider.tickSleep(3);
    	if(HintArrow.getPointed() != null &&
        		Players.localPlayer().getTarget() == null &&
        		!Players.localPlayer().isMoving() && 
        		!Dialogues.inDialogue())
    	{
    		if(!HintArrow.getPointed().canReach()) {
        		Walkz.walk(HintArrow.getPointed().getTile());
        	} else {
        		if(new EntityInteractEvent(HintArrow.getPointed(), "Talk-to").executed()) {
        			Sleep.sleep(555,666);
        			MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving(), (int) ((double) 5555 + Sleep.initSleepMod * 1111));
        		}
        	}
    	}
    	
    	Sleep.sleep(111, 1111);
    	return 5;
    }
}

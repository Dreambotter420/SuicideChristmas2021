package org.lostclient.behaviour.TutorialIsland;

import java.util.Collections;
import java.util.List;

import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.NPC;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ClickRat extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		(Widgets.getWidgetChild(263,1,0).getText().contains("s time to slay some rats! To attack a rat, all you have to do is click on it. This will cause you to walk over and start hitting it.") || 
        				Widgets.getWidgetChild(263,1,0).getText().contains("While you are fighting you will see a bar over your head")) &&
        		!Players.localPlayer().isMoving() &&
        		Players.localPlayer().getTarget() == null &&
        		Players.localPlayer().getTargetedByNPC() == null;
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickRat is valid");
    	clickRat();
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	return 5;
    }
    public static void clickRat() {
    	List<NPC> rats = NPCs.all(rat -> rat.getName().equals("Giant rat") && rat.distance() <= 5);
    	Collections.shuffle(rats);
    	if(new EntityInteractEvent(rats.get(0),"Attack").executed())
    	{
    		Sleep.sleep(555,999);
    	}
    	MethodProvider.tickSleep(2);
    }
}

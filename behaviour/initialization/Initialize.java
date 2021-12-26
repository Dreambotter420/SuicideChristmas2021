package org.lostclient.behaviour.initialization;

import java.time.LocalTime;

import org.lostclient.api.Client;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.containers.bank.Bank;
import org.lostclient.api.utilities.Timer;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

import net.runelite.api.GameState;

public class Initialize extends Leaf {

	 @Override
	 public boolean isValid() 
	 {
	    return !API.initialized &&
	    		Client.getClient().getGameState() == GameState.LOGGED_IN;
	 }
   

    @Override
    public int onLoop() {
    	if(!Bank.isOpen() && (Widgets.getWidgetChild(192,4) == null || !Widgets.getWidgetChild(192,4).isVisible()))
    	{
    		Widgets.closeAll(); // only close everything if bank / deposit box are NOT open
    	}
    	
    	API.rand2.setSeed(LocalTime.now().getNano());
    	Sleep.initSleepMod = 1.2 + (API.rand2.nextDouble()/1.25);
    	Sleep.initSleepMod = Sleep.initSleepMod * Sleep.initSleepMod;
    	//all initial randomizations that depend on new random seed go here
    	API.runTimer = new Timer();
    	API.initialized = true;
        return 5;
    }
}

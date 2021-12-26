package org.lostclient.behaviour.persistentCommands;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.magic.Magic;
import org.lostclient.api.wrappers.magic.spellbooks.Standard;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;
import org.lostclient.utilities.Walkz;

public class GoToLumbridge extends Leaf {

    @Override
    public boolean isValid() {
    	return API.mode == API.modes.GOHOME;
    }

    @Override
    public int onLoop() {
    	if(API.lumby.contains(Players.localPlayer())){
    		API.exitstop();
    		return (int) ((double) 50 + API.rand2.nextInt(33) * API.sleepMod);
    	}
    	if(API.callerCmd.contains("noAnnounce")) API.lumbyTeleFailed = true;
    	if(!API.lumbyTeleFailed)
    	{
    		if(Players.localPlayer().isAnimating())
    			return (int) ((double) 100 + API.rand2.nextInt(500) * API.sleepMod);
    		if(Magic.castSpell(Standard.HOME_TELEPORT))//successfully clicked cast
    		{
    			MethodProvider.tickSleep(2);
    			return Sleep.Calculate(111,1111);
    		}
    	}
    	if(API.lumbyTeleFailed)
    	{
    		Walkz.walkToArea(API.lumby,API.randomizedLumbyTile);
    	}
    	
        return (int) ((double) 100 + API.rand2.nextInt(200) * API.sleepMod);
    }

}
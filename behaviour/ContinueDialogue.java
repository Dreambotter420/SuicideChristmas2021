package org.lostclient.behaviour;

import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class ContinueDialogue extends Leaf {

    @Override
    public boolean isValid() {
    	return Dialogues.canContinue();
    }

    @Override
    public int onLoop() {
    	if(Dialogues.continueDialogue())
    	{

        	MethodProvider.tickSleep(2);
    	}
		return Sleep.Calculate(111,111);
    }

}
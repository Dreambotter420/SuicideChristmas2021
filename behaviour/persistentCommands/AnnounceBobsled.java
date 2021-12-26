package org.lostclient.behaviour.persistentCommands;

import org.lostclient.api.wrappers.input.Keyboard;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class AnnounceBobsled extends Leaf {

    @Override
    public boolean isValid() {
    	return API.callerCmd.startsWith("announceBobsled");
    }

    @Override
    public int onLoop() {
    	Keyboard.type("I know.",true);
		API.exitstop();
		return Sleep.Calculate(111,1111);
    }

}
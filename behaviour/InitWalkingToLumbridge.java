package org.lostclient.behaviour;

import org.lostclient.api.Client;
import org.lostclient.framework.Root;
import org.lostclient.utilities.API;

public class InitWalkingToLumbridge extends Root{
    @Override
    public boolean isValid() {
        return Client.isLoggedIn() && API.mode == API.modes.GOHOME &&
        		API.reachedLumbridge == false;
    }
    
}
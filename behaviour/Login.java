package org.lostclient.behaviour;

import org.lostclient.api.Client;
import org.lostclient.api.events.random.events.LoginEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

import net.runelite.api.GameState;

/**
 * @author LostVirt
 * @created 09/11/2021 - 11:50
 * @project LCE
 */
public class Login extends Leaf
{

    @Override
    public boolean isValid() {
    	return Client.getClient().getGameState() != GameState.LOGGED_IN;
    }

    @Override
    public int onLoop() {
    	Sleep.sleep(5000,1111);
    	MethodProvider.sleepUntil(() -> Client.isLoggedIn(), Sleep.Calculate(10000, 5000)); 
    	//10-30s wait to see if its a worldhop or actively logging in
    	if(Client.getClient().getGameState() == GameState.LOGGED_IN) return 0;
    	API.randomizeWorld();
        if(new LoginEvent(API.accUsername,API.accPassword)
        .setWorld(API.randomWorld)
        .setEventCompleteCondition(() -> Client.getClient().getGameState() == GameState.LOGGED_IN, 10000)
        .executed()) 
        {
        	MethodProvider.log("Successfully executed LoginEvent to f2p world!");
        }
        else 
        {
        	MethodProvider.log("Not executed LoginEvent to f2p world");
        }
        return 0;
    }
}

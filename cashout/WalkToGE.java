package org.lostclient.cashout;

import org.lostclient.api.Client;
import org.lostclient.api.containers.bank.Bank;
import org.lostclient.api.utilities.container.OwnedItems;
import org.lostclient.framework.Root;

import net.runelite.api.GameState;

public class WalkToGE extends Root {
    @Override
    public boolean isValid() {
        return Client.getClient().getGameState() == GameState.LOGGED_IN &&
        		(OwnedItems.contains("Cabbage") || 
        		OwnedItems.contains("Onion") || 
        		OwnedItems.contains("Potato") || 
        		OwnedItems.contains("Grain") || 
        		OwnedItems.contains("Logs") || 
        		Bank.contains("Blue partyhat") || 
        		Bank.contains("Coins"));
    }

}

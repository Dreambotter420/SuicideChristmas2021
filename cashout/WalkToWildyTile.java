package org.lostclient.cashout;

import org.lostclient.api.Client;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.containers.bank.Bank;
import org.lostclient.api.utilities.container.OwnedItems;
import org.lostclient.framework.Root;
import org.lostclient.utilities.API;

import net.runelite.api.GameState;

public class WalkToWildyTile extends Root {
    @Override
    public boolean isValid() {
        return Client.getClient().getGameState() == GameState.LOGGED_IN &&
        		API.GEhasBanked &&
        		!(OwnedItems.contains("Cabbage") ||
        				OwnedItems.contains("Onion") || 
        				OwnedItems.contains("Potato") || 
        				OwnedItems.contains("Logs") ||
        				OwnedItems.contains("Grain")) &&
        		(!OwnedItems.contains("Blue partyhat") || (OwnedItems.contains("Blue partyhat") && 
        				Inventory.contains("Blue partyhat") && 
        				!Bank.contains("Blue partyhat"))) &&
        		Inventory.contains("Coins") &&
        		!Bank.contains("Coins");
    }

}

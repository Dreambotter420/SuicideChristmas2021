package org.lostclient.behaviour;

import org.lostclient.api.Client;
import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.containers.equipment.Equipment;
import org.lostclient.api.utilities.container.OwnedItems;
import org.lostclient.framework.Root;
import org.lostclient.utilities.API;

public class HasSingleCommand extends Root {
    @Override
    public boolean isValid() {
        return Client.isLoggedIn() && API.initialized && 
        		(API.mode == API.modes.TRADE_ONCE
        		|| API.saySomething == API.modes.SAY
        		|| API.equipMode != null
        		|| GameObjects.closest("Snow") != null
        		|| Inventory.contains("Snowball")
        		|| Equipment.contains("Snowball") ||
        		(!OwnedItems.contains("Snowball") && Inventory.contains("Giant boulder")));
    }

}

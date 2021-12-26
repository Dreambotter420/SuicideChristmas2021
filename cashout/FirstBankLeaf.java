package org.lostclient.cashout;

import java.util.ArrayList;
import java.util.List;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.containers.bank.Bank;
import org.lostclient.api.containers.ge.GrandExchange;
import org.lostclient.api.events.InventoryEvent;
import org.lostclient.api.events.WidgetEvent;
import org.lostclient.api.events.ge.GrandExchangeEvent;
import org.lostclient.api.events.ge.items.SellItem;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.container.OwnedItems;
import org.lostclient.api.utilities.math.Calculations;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;

public class FirstBankLeaf extends Leaf {

    @Override
    public boolean isValid() {
        return true;
    }
    @Override
    public int onLoop() {
    	if(Widgets.getWidgetChild(584, 1) != null && 
    			Widgets.getWidgetChild(584, 1).isVisible())
    	{
    		if(new WidgetEvent(Widgets.getWidgetChild(584, 1),"Yes").executed())
    		{
    			MethodProvider.tickSleep(2);
				return (int) Calculations.nextGaussianRandom(1000, 500);
    		}
    	}
    	if(!Bank.isOpen()) 
    	{
    		if(Inventory.contains("Notes"))
    		{
    			if(new InventoryEvent(Inventory.get("Notes"),"Destroy").executed())
    			{
    				MethodProvider.tickSleep(2);
    				return (int) Calculations.nextGaussianRandom(1000, 500);
    			}
    		}
    		Bank.openClosest();
    	}
    	else
    	{
    		if(Inventory.contains("Notes"))
    		{
    			Bank.close();
    			MethodProvider.tickSleep(2);
    			return (int) Calculations.nextGaussianRandom(1000, 500);
    		}
    		if(!Inventory.isEmpty()) 
    		{
    			Bank.depositInventory();
    			MethodProvider.tickSleep(1);
    			MethodProvider.sleep((int) Calculations.nextGaussianRandom(1000, 500));
    		}
    		if(Bank.contains("Coins"))
    		{
    			Bank.withdrawAll("Coins");
    			MethodProvider.tickSleep(1);
    			MethodProvider.sleep((int) Calculations.nextGaussianRandom(1000, 500));
    		}
    			
    		API.GEhasBanked = true;
    	}
    	
        return (int) Calculations.nextGaussianRandom(1000, 500);
    }
    
}

package org.lostclient.cashout;

import java.util.ArrayList;
import java.util.List;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.containers.bank.Bank;
import org.lostclient.api.containers.ge.GrandExchange;
import org.lostclient.api.events.ge.GrandExchangeEvent;
import org.lostclient.api.events.ge.items.SellItem;
import org.lostclient.api.events.loadout.InventoryLoadout;
import org.lostclient.api.events.loadout.WithdrawLoadoutEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.container.OwnedItems;
import org.lostclient.api.utilities.math.Calculations;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;

import net.runelite.api.ItemID;

public class AtGE extends Leaf {

    @Override
    public boolean isValid() {
        return Locations.GEArea.contains(Players.localPlayer());
    }
    private final int cab = 1965;
    private final int logs = ItemID.LOGS;
    private final int onion = 1957;
    private final int grain = 1947;
    private final int potato = 1942;
    private final int cabNOTED = 1966;
    private final int onionNOTED = 1958;
    private final int potatoNOTED = 1943;
    private final int grainNOTED = 1966;
    private final int coins = 995;
    @Override
    public int onLoop() {
    	/*Creating a Grand Exchange Event:

    		new GrandExchangeEvent()

    		#addBuyItem(BuyItem... buyItems) - Add individual BuyItems
    		#addBuyItemList(List<BuyItem> buyItemList) - Add a List of BuyItems

    		#addSellItem(SellItem... sellItems) - Add individual SellItems
    		#addSellItemList(List<SellItem> sellItemList) - Add a List of SellItems

    		#setWaitTime(long milliseconds) - Sets the amount of time it will wait for offers in milliseconds

    		Example use case:
*/
    	if(!API.openedGEOnce)
    	{
    		if(!GrandExchange.isOpen())
    		{
    			GrandExchange.open();
    			MethodProvider.tickSleep(1);
    		}
    		if(GrandExchange.isOpen())
    		{
    			API.openedGEOnce = true;
    		}
    	}
    	else
    	{
    		if(GrandExchange.isReadyToCollect())
        	{
        		GrandExchange.collect(true);
        	}
        	List<SellItem> sellItemList = new ArrayList<>();
        	if(OwnedItems.contains(cab)) sellItemList.add(new SellItem(cab,OwnedItems.count(cab)).setItemPrice(1).setItemWaitTimeMillis(180000));
        	if(OwnedItems.contains(logs)) sellItemList.add(new SellItem(logs,OwnedItems.count(logs)).setItemPrice(1).setItemWaitTimeMillis(180000));
        	if(OwnedItems.contains(onion)) sellItemList.add(new SellItem(onion,OwnedItems.count(onion)).setItemPrice(1).setItemWaitTimeMillis(180000));
        	if(OwnedItems.contains(grain)) sellItemList.add(new SellItem(grain,OwnedItems.count(grain)).setItemPrice(1).setItemWaitTimeMillis(180000));
        	if(OwnedItems.contains(potato)) sellItemList.add(new SellItem(potato,OwnedItems.count(potato)).setItemPrice(1).setItemWaitTimeMillis(180000));
        	new GrandExchangeEvent()
        		    .addSellItems(sellItemList)
        		    .execute();
        	MethodProvider.log("Done executing first SellEvent(withdraw noted and open GE)");
        	
    	}
    	if(Bank.contains("Coins") || Bank.contains("Blue partyhat"))
    	{
    		if(Bank.isOpen())
    		{
    			if(!Inventory.isEmpty())
    			{
    				Bank.depositInventory();
    				MethodProvider.tickSleep(1);
    			}
    			else
    			{
    				int coinsQty = Bank.count("Coins");
        			InventoryLoadout loadout = new InventoryLoadout()
        				.addReq(995,coinsQty)
        				.addReq(ItemID.BLUE_PARTYHAT,2).setNoted()
        				.addReq(ItemID.YELLOW_PARTYHAT,2).setNoted()
        				.addReq(ItemID.RED_PARTYHAT,2).setNoted()
        				.addReq(ItemID.WHITE_PARTYHAT,2).setNoted()
        				.addReq(ItemID.PURPLE_PARTYHAT,2).setNoted()
        				.addReq(ItemID.GREEN_PARTYHAT,2).setNoted()
        				.addReq(ItemID.SANTA_HAT,2).setNoted()
        				.addReq(ItemID.CHRISTMAS_CRACKER,2).setNoted();
        			new WithdrawLoadoutEvent(loadout).execute();
        			MethodProvider.tickSleep(1);
    			}
    		}
    		else
    		{
    			Bank.openClosest();
    			MethodProvider.tickSleep(1);
    		}
    	}
        return (int) Calculations.nextGaussianRandom(2000, 1000);
    }
    
}

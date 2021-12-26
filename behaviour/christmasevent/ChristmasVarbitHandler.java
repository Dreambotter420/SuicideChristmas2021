package org.lostclient.behaviour.christmasevent;


import org.lostclient.api.Client;
import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.accessors.Worlds;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.containers.bank.Bank;
import org.lostclient.api.containers.bank.BankLocation;
import org.lostclient.api.events.DropAllEvent;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.events.InventoryEvent;
import org.lostclient.api.events.WidgetEvent;
import org.lostclient.api.events.worldhopper.WorldHopperEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.Timer;
import org.lostclient.api.utilities.math.Calculations;
import org.lostclient.api.wrappers.hintarrows.HintArrow;
import org.lostclient.api.wrappers.interactives.GameObject;
import org.lostclient.api.wrappers.interactives.NPC;
import org.lostclient.api.wrappers.map.Area;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.MissingAPI;
import org.lostclient.utilities.Sleep;
import org.lostclient.utilities.Walkz;

public class ChristmasVarbitHandler extends Leaf {

    @Override
    public boolean isValid() {
        return !API.celebratedChristmas;
    }
    
    public static boolean artificialQuestFinishedVar = false; //used at last step when talking to squire to indicate last step is finished
    
    private final static Tile squireTile = new Tile(2976,3343,0);
    private final static Area herquinArea = new Area(2946,3337,2944,3332,0);
    private final static Tile herquinTile = new Tile(2945,3335,0);
    private final static Tile ceciliaTile = new Tile(2991,3383,0);
    private final static Tile ceciliaTile_finished = new Tile(3002,3361,0);
    private final static Area ceciliaArea = new Area(2990,3381,2991,3386,0);
    private final static Tile hairdresserTile = new Tile(2949,3379,0);
    private final static Tile wayneTile = new Tile(2972,3313,0);
    private final static Area hairdressingRoom = new Area(2948,3377,2942,3381,0);

    private final static Tile vyvinTile = new Tile(2983,3341,2);
    private final static Tile wysonTile = new Tile(3027,3379,0);
    private final static Tile larryTile = new Tile(3037,3363,0);
    private final static Tile gurlbuoyTile = new Tile(3022,3364,0);
    private final static Tile snowTile_prepare = new Tile(3032,3363,0);
    private final static Tile snowTile_deploy = new Tile(3031,3363,0);
    private final static Area rollingZone = new Area(3032,3364,3021,3362,0);
    
    
    private final static int snowID = 26296; //deployable snow to roll
    
    private final static int littleSnowball = 26326;
    private final static int smallSnowball = 26328;
    private final static int normalSnowball = 26330;
    private final static int bigSnowball = 26332;
    private final static int largeSnowball = 26334;
    private final static int hugeSnowball = 26336;
    private final static int humongousSnowball = 26338;
    
    /**
     * notes
     * 13164  13165 13166 13163 13168 -> 0->1 upon bushes search
     * 13167 0 -> 3 upon finding brown chocolates
     * 
     * 13159 -> 0->1 upon talking to hairdresser to confirm his gift: scissors
     */
    @Override
    public int onLoop() {
    	API.randomAFK();
    	if(Client.getClient().getVarbitValue(12976) == 0)
    	{
    		if(!Inventory.isEmpty())
    		{
    			if(Bank.isOpen())
    			{
    				Bank.depositInventory();
	    		}
    			else
    			{
    				Bank.traverseOpen(BankLocation.FALADOR_EAST);
    			}
				MethodProvider.tickSleep(2);
    			return Sleep.Calculate(111, 1111);
    		}
    	}
    	if(Client.getClient().getVarbitValue(13152) == 0 || 
        		Client.getClient().getVarbitValue(13156) == 0 || 
        		Client.getClient().getVarbitValue(13162) == 0)
    	{
    		//need to talk to Squire
    		talkToSquire();
    	}
    	
    	//first task:find herquin's chocolate to trade for his cinnamon
    	switch(Client.getClient().getVarbitValue(13162))
    	{
    	case(1):
    	{
    		//not talked first time
    		talkToHerquin();
    		return Sleep.Calculate(111,111);
    	}
    	case(2):
    	{
    		//time to search bushes and give back chocolate found
    		if(Client.getClient().getVarbitValue(13168) == 1 && 
    				Client.getClient().getVarbitValue(13167) == 2)
    		{
    			talkToHerquin();
    		}
    		
    		//need to get chocolate from the bushes
    		pickABush();
    		return Sleep.Calculate(111,111);
    	}
    	case(3):
    	{
    		//given herquin the chocolates but not received the cinnamon
    		talkToHerquin();
    		return Sleep.Calculate(111,111);
    	}
    	case(4):
    	{
    		//got tha cinnamon, continue next task below
    	}
    	}
    	
    	//second task:
    	switch(Client.getClient().getVarbitValue(13156))
    	{
    	case(1):
    	{
    		//need to talk to Cecilia first time
    		talkToCecilia();
    		return Sleep.Calculate(111,111);
    	}
    	case(2):
    	{
    		//need to get presents in inventory I think
    		talkToCecilia();
    		return Sleep.Calculate(111,111);
    	}
    	case(3):
    	{
    		if(Client.getClient().getVarbitValue(13159) == 1 && 
            		Client.getClient().getVarbitValue(13157) == 1 && 
            		Client.getClient().getVarbitValue(13158) == 1)
        	{
    			//all gifts given, talk to cecilia again
    			talkToCecilia();
    			return Sleep.Calculate(111,111);
        	}
    		//only talk to hairdresser if he hasn't received his scissors, 0 == no scissors, 1 == scissors
    		if(Client.getClient().getVarbitValue(13159) == 0)
    		{
    			if(!Dialogues.inDialogue())
    			{
    				MethodProvider.tickSleep(2);
    				if(!Dialogues.inDialogue())
    				{
    					talkToHairdresser();
    				}
    			}
        		return Sleep.Calculate(111,111);
    		}
    		//only talk to wayne if he hasn't received his goblin mail, 0 == no goblin mail, 1 == goblin mail
    		if(Client.getClient().getVarbitValue(13157) == 0)
    		{
    			if(!Dialogues.inDialogue())
    			{
    				MethodProvider.tickSleep(2);
    				if(!Dialogues.inDialogue())
    				{
    					talkToWayne();
    				}
    			}
        		return Sleep.Calculate(111,111);
    		}
    		//only talk to wyson if he hasn't received his woadleaves, 0 == no woadleaves, 1 == woadleaves
    		if(Client.getClient().getVarbitValue(13158) == 0)
    		{
    			if(!Dialogues.inDialogue())
    			{
    				MethodProvider.tickSleep(2);
    				if(!Dialogues.inDialogue())
    				{
    					talkToWyson();
    				}
    			}
        		return Sleep.Calculate(111,111);
    		}
    		
    	}
    	case(4):
    	{
    		//confirmed all gifts given but no festive holly from cecilia yet
    		talkToCecilia();
    		return Sleep.Calculate(111,111);
    	}
    	case(5):
    	{
    		//have festive holly now, continue to next task
    	}
    	
    	//third task:
    	switch(Client.getClient().getVarbitValue(13152))
    	{
    	case(1):
    	{
    		//need to talk to larry first time
    		talkToLarry();
    		return Sleep.Calculate(111, 111);
    	}
    	case(2):
    	{
    		//need to talk to gurlBuoy
    		talkToGurlBuoy();
    		return Sleep.Calculate(111, 111);
    	}
    	case(3):
    	{
    		//need to talk to Larry again
    		talkToLarry();
    		return Sleep.Calculate(111, 111);
    	}
    	case(4):
    	{
    		//need to roll snowballs n shit
    		//but first grab spade
    		if(Client.getClient().getVarbitValue(13155) == 0)
    		{
    			GameObject spade = GameObjects.closest(g -> 
    					g.getName().contains("Spade") && 
    					g.containsAction("Take"));
    			if(spade != null)
    			{
    				if(spade.canReach())
    				{
    					if(new EntityInteractEvent(spade,"Take").executed())
    	    			{
    	    				MethodProvider.tickSleep(1);
    	    				MethodProvider.sleepUntil(() -> Dialogues.inDialogue(),10000);
    	    				return Sleep.Calculate(111, 111);
    	    			}
    				}
    				else
    				{
    					Walking.walk(spade.getTile());
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		
    		//first snowman
    		switch(Client.getClient().getVarbitValue(13153))
    		{
    		case(0):
    		{
    			//need small snowball for west snowman
    			if(Inventory.contains(smallSnowball))
    			{
    				placeSnowballWest();
    			}
    			else 
    			{
    				if(getSnow())
    				{
    					rollSmallSnowball();
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		case(1):
    		{
    			//need big snowball for west snowman
    			if(Inventory.contains(bigSnowball))
    			{
    				placeSnowballWest();
    			}
    			else 
    			{
    				if(getSnow())
    				{
    					rollBigSnowball();
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		case(2):
    		{
    			//need big snowball for west snowman
    			if(Inventory.contains(hugeSnowball))
    			{
    				placeSnowballWest();
    			}
    			else 
    			{
    				if(getSnow())
    				{
    					rollHugeSnowball();
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		case(3):
    		{
    			//all done with this one
    		}
    		case(4):
    		{
    			//can now view the decorated snowmens GZ!!
    		}
    		}
    		
    		//2nd snowman
    		switch(Client.getClient().getVarbitValue(13154))
    		{
    		case(0):
    		{
    			//need humongousSnowball for north snowman
    			if(Inventory.contains(humongousSnowball))
    			{
    				placeSnowballNorth();
    			}
    			else 
    			{
    				if(getSnow())
    				{
    					rollHumongousSnowball();
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		case(1):
    		{
    			//need largeSnowball for north snowman
    			if(Inventory.contains(largeSnowball))
    			{
    				placeSnowballNorth();
    			}
    			else 
    			{
    				if(getSnow())
    				{
    					rollLargeSnowball();
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		case(2):
    		{
    			//need normalSnowball for north snowman
    			if(Inventory.contains(normalSnowball))
    			{
    				placeSnowballNorth();
    			}
    			else 
    			{
    				if(getSnow())
    				{
    					rollNormalSnowball();
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		case(3):
    		{
    			//need bigSnowball for north snowman
    			if(Inventory.contains(bigSnowball))
    			{
    				placeSnowballNorth();
    			}
    			else 
    			{
    				if(getSnow())
    				{
    					rollBigSnowball();
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		case(4):
    		{
    			//need littleSnowball for north snowman
    			if(Inventory.contains(littleSnowball))
    			{
    				placeSnowballNorth();
    			}
    			else 
    			{
    				if(getSnow())
    				{
    					rollLittleSnowball();
    				}
    			}
    			return Sleep.Calculate(111, 111);
    		}
    		case(5):
    		{
    			//all done here
    			MethodProvider.log("test");
    		}
    		case(6):
    		{
    			//can now view the decorated snowmens GZ!!
    		}
    		}
    		
    		
    		return Sleep.Calculate(111, 111);
    	}
    	case(5):
    	{
    		//talk to gurlbuoy to confirm all snowmans and snowwomans built
    		talkToGurlBuoy();
    		return Sleep.Calculate(111, 111);
    	}
    	case(6):
    	{
    		//need to talk to larry at the end
    		talkToLarry();
    		return Sleep.Calculate(111, 111);
    	}
    	case(7):
    	{
    		//need to talk to larry at the end again
    		talkToLarry();
    		return Sleep.Calculate(111, 111);
    	}
    	case(8):
    	{
    		//snowmen are all decorated now
    		switch(Client.getClient().getVarbitValue(12976))
    		{
    		case(4):
    		{
    			//talk to squire
        		talkToSquire();
        		return Sleep.Calculate(111, 111);
    		}
    		case(10):
    		{
    			//talk to squire
        		talkToSquire();
        		return Sleep.Calculate(111, 111);
    		}
    		case(12):
    		{
    			//need to use festive cinnamon on festive white wine
        		if(new InventoryEvent(Inventory.get("Festive cinnamon")).on(Inventory.get("Festive white wine")).executed())
        		{
        			MethodProvider.tickSleep(1);
        		}
        		else {
        			MethodProvider.log("Failed to mix cinnamon and white wine");
        		}
        		return Sleep.Calculate(111, 111);
    		}
    		case(13):
    		{
    			//talk to squire
        		talkToSquire();
        		return Sleep.Calculate(111, 111);
    		}
    		case(14):
    		{
    			if(Client.getClient().getVarbitValue(13147) == 1 && 
    					Client.getClient().getVarbitValue(13148) == 1 && 
    					Client.getClient().getVarbitValue(13149) == 1)
    			{
    				talkToSquire();
    				return Sleep.Calculate(111, 111);
    			}
    			//need to use magical cleaning potion on stained armour pieces
    			if(Client.getClient().getVarbitValue(13147) == 0)
        		{
        			if(new InventoryEvent(Inventory.get("Magical cleaning potion")).on(Inventory.get("Pink stained platebody")).executed())
            		{
            			MethodProvider.tickSleep(1);
            		}
            		else {
            			MethodProvider.log("Failed to cleaning potion and Pink stained platebody");
            		}
        		}
        		else if(Client.getClient().getVarbitValue(13148) == 0)
        		{
        			if(new InventoryEvent(Inventory.get("Magical cleaning potion")).on(Inventory.get("Pink stained platelegs")).executed())
            		{
            			MethodProvider.tickSleep(1);
            		}
            		else {
            			MethodProvider.log("Failed to cleaning potion and Pink stained platelegs");
            		}
        		}
        		else if(Client.getClient().getVarbitValue(13149) == 0)
        		{
        			if(new InventoryEvent(Inventory.get("Magical cleaning potion")).on(Inventory.get("Pink stained full helm")).executed())
            		{
            			MethodProvider.tickSleep(1);
            		}
            		else {
            			MethodProvider.log("Failed to cleaning potion and Pink stained full helm");
            		}
        		}
    			
        		return Sleep.Calculate(111, 111);
    		}
    		case(15):
    		{
    			//talk to sir vyvin
        		talkToVyvin();
        		return Sleep.Calculate(111, 111);
    		}
    		case(16):
    		{
    			if(Inventory.contains(snowID))
    			{
    				if(new DropAllEvent(snowID).executed())
    				{
    					MethodProvider.tickSleep(1);
    				}
    			}
    			if(artificialQuestFinishedVar || 
    					NPCs.closest(n -> 
    						n.getTile().equals(new Tile(3002,3360,0)) && 
    						n.getName().contains("Cecilia")) != null)
    			{
    				Sleep.sleep(3333,1111);
    				if(Dialogues.inDialogue()) return Sleep.Calculate(11,111);
    				talkToCecilia_finished();
    			}
    			else
    			{
    				//talk to squire
            		talkToSquire();
    			}
        		return Sleep.Calculate(111, 111);
    		}
    		case(20):
    		{
    			if(Client.isInCutscene()) return Sleep.Calculate(2222, 1111);
    			if(Widgets.getWidgetChild(584, 1) != null && 
    	    			Widgets.getWidgetChild(584, 1).isVisible())
    	    	{
    	    		if(new WidgetEvent(Widgets.getWidgetChild(584, 1),"Yes").executed())
    	    		{
    	    			MethodProvider.tickSleep(2);
    	    			MethodProvider.log("Destroyed notes from christmas event");
    	    		}
    	    		return Sleep.Calculate(111, 111);
    	    	}
    	    	if(!Bank.isOpen()) 
    	    	{
    	    		if(Inventory.contains("Notes"))
    	    		{
    	    			if(new InventoryEvent(Inventory.get("Notes"),"Destroy").executed())
    	    			{
    	    				MethodProvider.tickSleep(2);
    	    			}
        	    		return Sleep.Calculate(111, 111);
    	    		}
    	    		
    	    	}
    	    	if(Bank.isOpen())
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
    	    			return Sleep.Calculate(111, 111);
    	    		}
    	    		else
    	    		{
    	    			if(Bank.close())
    	    			{
    	    				MethodProvider.tickSleep(1);
    	    			}
    	    			MethodProvider.tickSleep(1);
    	    		}
    	    	}
    			if(Widgets.getWidgetChild(153, 16) != null && 
    					Widgets.getWidgetChild(153, 16).isVisible())
    			{
    				if(new WidgetEvent(Widgets.getWidgetChild(153, 16),"Close").executed())
    				{
    					MethodProvider.tickSleep(1);
    					MethodProvider.log("~~~~[x] Christmas 2021 Event Completed [x]~~~~~");
    				}
    				return Sleep.Calculate(111, 111);
    			}
    			if(Inventory.contains("A big present"))
    			{
    				if(new InventoryEvent(Inventory.get("A big present"),"Open").executed())
    				{
    					MethodProvider.tickSleep(2);
    				}
    				else
    				{
    					MethodProvider.log("Failed to open a big present :-(");
    				}
    				return Sleep.Calculate(111, 111);
    			}
    			API.celebratedChristmas = true;
    			return Sleep.Calculate(111, 111);
    		}
    		}
    		
    		
    	}
    	
    	}
    	
    	
    	}
    	if(Client.getClient().getVarbitValue(13152) == 1 && 
        		Client.getClient().getVarbitValue(13156) == 1 && 
        		Client.getClient().getVarbitValue(13162) == 1)
    	{
    		//need to talk to read notes
    		if(Widgets.getWidgetChild(625,7) != null && 
    				Widgets.getWidgetChild(625,7).isVisible())
    		{
    			if(Widgets.getWidgetChild(625,7).getText().contains("<col=ffffff>I need to collect three items to help make a magical cleaning potion.<br><br><col=ff981f>Festive cinnamon<col=ffffff><br><col=ffffff>I should speak to Herquin. He can be found in his gem shop just south of the west bank.<br><br><col=ff981f>Festive holly<col=ffffff><br><col=ffffff>I should speak to Cecilia. She can be found on the bridge in the park.<br><br><col=ff981f>Festive white wine<br><col=ffffff>I should speak to Larry. He can be found in his house just south east of the park.<br><br>"))
    			{
    				//this is the original text before any steps in case I need it
    				if(new WidgetEvent(Widgets.getWidgetChild(625,2))
    						.executed())
    				{
    					MethodProvider.tickSleep(1);
    				}
    			}
    		}
    		
    		if(Inventory.contains("Notes"))
    		{
    			
    		}
    	}
    	return Sleep.Calculate(111,111);
    }
    private static final Tile bushSW = new Tile(2945,3314,0);
    private static final Tile bushMid = new Tile(2947, 3318,0);
    private static final Tile bushNW = new Tile(2945, 3320,0);
    private static final Tile bushNE = new Tile(2951, 3319,0);
    
    public static void pickABush()
    {
    	if(Players.localPlayer().isAnimating() || Players.localPlayer().isMoving())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	/*
    	 * 13165 - nw bushvarbit
    	 * 13164 mid
    	 * 13166 sw
    	 * 13163 NE
    	 */
    	if(Client.getClient().getVarbitValue(13165) == 0)
    	{
    		if(new EntityInteractEvent(GameObjects.getTopObjectOnTile(bushNW),"Search").executed())
    		{
    			MethodProvider.tickSleep(2);
    			Sleep.sleep(111, 1111);
    			return;
    		}	
    	}
    	if(Client.getClient().getVarbitValue(13164) == 0)
    	{
    		if(new EntityInteractEvent(GameObjects.getTopObjectOnTile(bushMid),"Search").executed())
    		{
    			MethodProvider.tickSleep(2);
    			Sleep.sleep(111, 1111);
    			return;
    		}	
    	}
    	if(Client.getClient().getVarbitValue(13166) == 0)
    	{
    		if(new EntityInteractEvent(GameObjects.getTopObjectOnTile(bushSW),"Search").executed())
    		{
    			MethodProvider.tickSleep(2);
    			Sleep.sleep(111, 1111);
    			return;
    		}	
    	}
    	if(Client.getClient().getVarbitValue(13163) == 0)
    	{
    		if(new EntityInteractEvent(GameObjects.getTopObjectOnTile(bushNE),"Search").executed())
    		{
    			MethodProvider.tickSleep(2);
    			Sleep.sleep(111, 1111);
    			return;
    		}	
    	}
    	
    }
    public static void talkToVyvin()
    {
    	if(Walkz.walkToTileInRadius(vyvinTile,10))
    	{
			if(MissingAPI.isInteracting()) return;
    		NPC vyvin = NPCs.closest(n -> n.getName().contains("Sir Vyvin"));
			if(vyvin.canReach())
			{
				if(new EntityInteractEvent(vyvin,"Talk-to")
						.setEventTimeout(15000)
						.setEventCompleteCondition(() -> Dialogues.inDialogue())
						.executed())
				{
					MethodProvider.tickSleep(1);
				}
				else
				{
					MethodProvider.log("vyvin talk-to event not executed!");
				}
			}
			else {
				Walking.walk(vyvin.getTile());
			}
		}
    }
    public static void talkToGurlBuoy()
    {
    	if(Walkz.walkToTileInRadius(gurlbuoyTile,6))
    	{
    		if(MissingAPI.isInteracting()) return;
			NPC gurlbuoy = NPCs.closest(n -> n.getName().contains("Gurl") || 
					n.getName().contains("Buoy"));
			if(gurlbuoy.canReach())
			{
				if(new EntityInteractEvent(gurlbuoy,"Talk-to")
						.setEventTimeout(15000)
						.setEventCompleteCondition(() -> Dialogues.inDialogue())
						.executed())
				{
					MethodProvider.tickSleep(1);
				}
				else
				{
					MethodProvider.log("gurlbouy talk-to event not executed!");
				}
			}
			else {
				Walking.walk(gurlbuoyTile);
			}
		}
    }
    public static void talkToLarry()
    {
    	if(Walkz.walkToTileInRadius(larryTile,6))
    	{
    		if(MissingAPI.isInteracting()) return;
    		NPC larry = NPCs.closest("Larry");
			if(larry.canReach())
			{
				if(new EntityInteractEvent(larry,"Talk-to")
						.setEventTimeout(15000)
						.setEventCompleteCondition(() -> Dialogues.inDialogue())
						.executed())
				{
					MethodProvider.tickSleep(1);
				}
				else
				{
					MethodProvider.log("larry talk-to event not executed!");
				}
			}
			else {
				Walking.walk(larryTile);
			}
		}
    }
    public static void getOutOfHairdressingRoom()
    {
    	if(hairdressingRoom.contains(Players.localPlayer()))
		{
			MethodProvider.log("INSIDE");
    		if(GameObjects.closest(p -> p.getName().contains("Door") && 
					p.getTile().equals(new Tile(2949, 3379,0))) != null)
			{
				if(new EntityInteractEvent(GameObjects.closest(p -> p.getName().contains("Door") && 
					p.getTile().equals(new Tile(2949, 3379,0))),"Open")
				.executed())
				{
					MethodProvider.tickSleep(2);
					MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving(), 5000);
				}
			}
			else
			{
			 	Walking.setWalkFlag(new Tile(2951,3379,0));
			 	MethodProvider.tickSleep(2);
			 	MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving(), 5000);
			}
		}
    	else
    	{
    		MethodProvider.log("OUTSIDE");
    	}
    }
    public static void talkToWayne()
    {
    	getOutOfHairdressingRoom();
    	if(Walkz.walkToTileInRadius(wayneTile, 4))
		{
    		if(MissingAPI.isInteracting()) return;
			NPC wayne = NPCs.closest("Wayne");
			
			if(wayne.canReach())
			{
				if(new EntityInteractEvent(wayne,"Talk-to")
						.setEventTimeout(15000)
						.setEventCompleteCondition(() -> Dialogues.inDialogue())
						.executed())
				{
					MethodProvider.tickSleep(1);
				}
				else
				{
					MethodProvider.log("Wayne talk-to event not executed!");
				}
			}
			else {
				Walking.walk(wayneTile);
			}
		}
    }
    public static void talkToWyson()
    {
    	if(Walkz.walkToTileInRadius(wysonTile, 8))
		{
    		if(MissingAPI.isInteracting()) return;
			NPC wyson = NPCs.closest("Wyson the gardener");
			if(wyson.canReach())
			{
				if(new EntityInteractEvent(wyson,"Talk-to")
						.setEventTimeout(15000)
						.setEventCompleteCondition(() -> Dialogues.inDialogue())
						.executed())
				{
					MethodProvider.tickSleep(1);
				}
				else
				{
					MethodProvider.log("Wayne talk-to event not executed!");
				}
			}
			else {
				Walking.walk(wayneTile);
			}
		}
    }
    public static void talkToHairdresser()
    {
    	if(Walkz.walkToTileInRadius(hairdresserTile, 8))
		{
			NPC dresser = NPCs.closest("Hairdresser");
			if(dresser.canReach())
			{
				if(new EntityInteractEvent(dresser,"Talk-to")
						.setEventTimeout(15000)
						.setEventCompleteCondition(() -> Dialogues.inDialogue())
						.executed())
				{
					MethodProvider.tickSleep(1);
				}
				else
				{
					MethodProvider.log("Hairdresser talk-to event not executed!");
				}
			}
			else {
				if(GameObjects.closest(p -> p.getName().contains("Door") && 
						p.getTile().equals(new Tile(2949, 3379,0))) != null)
				{
					if(new EntityInteractEvent(GameObjects.closest(p -> p.getName().contains("Door") && 
						p.getTile().equals(new Tile(2949, 3379,0))),"Open")
					.executed())
					{
						MethodProvider.tickSleep(2);
						MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving(), 5000);
					}
				}
				Walking.walk(hairdresserTile);
			}
		}
    }
    public static void talkToSquire()
    {
    	if(Walkz.walkToTileInRadius(squireTile, 15))
		{
    		if(MissingAPI.isInteracting()) return;
    		NPC squire = NPCs.closest("Squire");
			if(squire.canReach())
			{
				if(new EntityInteractEvent(squire,"Talk-to")
						.setEventTimeout(15000)
						.setEventCompleteCondition(() -> Dialogues.inDialogue())
						.executed())
				{
					MethodProvider.tickSleep(1);
				}
				else
				{
					MethodProvider.log("squire talk-to event not executed!");
				}
			}
			else {
				Walking.walk(squireTile);
			}
    		
		}
    }
    public static void talkToHerquin()
    {
    	if(Walkz.walkToArea(herquinArea, herquinTile))
		{
    		if(MissingAPI.isInteracting()) return;
			if(new EntityInteractEvent(NPCs.closest("Herquin"),"Talk-to")
					.setEventTimeout(15000)
					.setEventCompleteCondition(() -> Dialogues.inDialogue())
					.executed())
			{
				MethodProvider.tickSleep(1);
			}
			else
			{
				MethodProvider.log("Herquin talk-to event not executed!");
			}
		}
    }
    public static void talkToCecilia_finished()
    {
    	if(Walkz.walkToTileInRadius(ceciliaTile_finished,5))
		{
    		if(MissingAPI.isInteracting()) return;
			if(new EntityInteractEvent(NPCs.closest("Cecilia"),"Talk-to")
					.setEventTimeout(15000)
					.setEventCompleteCondition(() -> Dialogues.inDialogue())
					.executed())
			{
				MethodProvider.tickSleep(1);
			}
			else
			{
				MethodProvider.log("Cecilia talk-to event not executed!");
			}
		}
    }
    public static void talkToCecilia()
    {
    	if(Walkz.walkToArea(ceciliaArea, ceciliaTile))
		{
    		if(MissingAPI.isInteracting()) return;
			if(new EntityInteractEvent(NPCs.closest("Cecilia"),"Talk-to")
					.setEventTimeout(15000)
					.setEventCompleteCondition(() -> Dialogues.inDialogue())
					.executed())
			{
				MethodProvider.tickSleep(1);
			}
			else
			{
				MethodProvider.log("Cecilia talk-to event not executed!");
			}
		}
    }
    public static boolean setupOrientation() {
    	if(!rollingZone.contains(Players.localPlayer()))
    	{
    		Walking.walk(snowTile_prepare);
    		MethodProvider.tickSleep(1);
    	}
    	else if(!snowTile_prepare.equals(Players.localPlayer().getTile()))
    	{
    		Walking.setWalkFlag(snowTile_prepare);
    		MethodProvider.tickSleep(2);
    	}
    	
		MethodProvider.sleepUntil(() -> Players.localPlayer().getTile().equals(snowTile_prepare), 8000);
		Sleep.sleep(1111, 111);
		if(Players.localPlayer().getTile().equals(snowTile_prepare))
		{
			Walking.setWalkFlag(snowTile_deploy);
			MethodProvider.tickSleep(2);
			Sleep.sleep(1111, 111);
			if(Players.localPlayer().getTile().equals(snowTile_deploy))
			{
				Sleep.sleep(1111, 111);
				return true;
			}
		}
		return false;
    }
    public static boolean getSnow()
    {
    	if(Inventory.contains(snowID)) return true;
    	if(!Players.localPlayer().isAnimating() && 
    			!Players.localPlayer().isMoving() && 
				API.snowDigTimer.elapsed() >= 3000)
		{
			if(new EntityInteractEvent(GameObjects.closest(g -> 
				g.getName().contains("Pile of Snow") && 
				g.containsAction("Dig")),"Dig")
					.executed())
			{
				API.snowDigTimer = new Timer();
				MethodProvider.tickSleep(1);
				Sleep.sleep(111, 111);
			}	
		}
    	return false;
    }
    public static void rollSmallSnowball()
    {
    	if(Inventory.contains(smallSnowball)) return;
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	NPC rolledSnow = NPCs.closest(g -> 
			g.containsAction("Pick-up") &&

    		g.equals(HintArrow.getNpc()) &&
			g.getName().contains("Small snowball"));
    	if(rolledSnow != null)
    	{
    		if(new EntityInteractEvent(rolledSnow,"Pick-up").executed())
    		{
    			MethodProvider.tickSleep(2);
    			return;
    		}
    	}
    	else
    	{
    		rolledSnow = NPCs.closest(g -> 
    		g.containsAction("Roll") &&

    		g.equals(HintArrow.getNpc()) &&
    		(g.getName().contains("Tiny snowball") || 
    				g.getName().contains("Mini snowball") || 
    				g.getName().contains("Little snowball")));
    		if(rolledSnow != null)
    		{
    			if(new EntityInteractEvent(rolledSnow,"Roll").executed())
        		{
        			MethodProvider.tickSleep(2);
        			return;
        		}
    			else 
    			{
    				MethodProvider.log("Roll-snow interaction failed");
        			return;
    			}
    		}
    		else
    		{
    			if(setupOrientation())
    			{
    				if(new InventoryEvent(Inventory.get(snowID),"Roll-snowball").executed())
    				{
    					MethodProvider.tickSleep(2);
    					Sleep.sleep(1111, 111);
    				}
    				else 
        			{
        				MethodProvider.log("Deploy snow from inventory failed");
            			return;
        			}
    			}
    		}
    	}
    }
    public static void rollLittleSnowball()
    {
    	if(Inventory.contains(littleSnowball)) return;
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	NPC rolledSnow = NPCs.closest(g -> 
			g.containsAction("Pick-up") &&

    		g.equals(HintArrow.getNpc()) &&
			g.getName().contains("Little snowball"));
    	if(rolledSnow != null)
    	{
    		if(new EntityInteractEvent(rolledSnow,"Pick-up").executed())
    		{
    			MethodProvider.tickSleep(2);
    			return;
    		}
    	}
    	else
    	{
    		rolledSnow = NPCs.closest(g -> 
    		g.containsAction("Roll") &&

    		g.equals(HintArrow.getNpc()) &&
    		(g.getName().contains("Mini snowball") || 
    				g.getName().contains("Tiny snowball")));
    		if(rolledSnow != null)
    		{
    			if(new EntityInteractEvent(rolledSnow,"Roll").executed())
        		{
        			MethodProvider.tickSleep(2);
        			return;
        		}
    			else 
    			{
    				MethodProvider.log("Roll-snow interaction failed");
        			return;
    			}
    		}
    		else
    		{
    			if(setupOrientation())
    			{
    				if(new InventoryEvent(Inventory.get(snowID),"Roll-snowball").executed())
    				{
    					MethodProvider.tickSleep(2);
    					Sleep.sleep(1111, 111);
    				}
    				else 
        			{
        				MethodProvider.log("Deploy snow from inventory failed");
            			return;
        			}
    			}
    		}
    	}
    }
    public static void rollBigSnowball()
    {
    	if(Inventory.contains(bigSnowball)) return;
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	NPC rolledSnow = NPCs.closest(g -> 
			g.containsAction("Pick-up") &&

    		g.equals(HintArrow.getNpc()) &&
			g.getName().contains("Big snowball"));
    	if(rolledSnow != null)
    	{
    		if(new EntityInteractEvent(rolledSnow,"Pick-up").executed())
    		{
    			MethodProvider.tickSleep(2);
    			return;
    		}
    	}
    	else
    	{
    		rolledSnow = NPCs.closest(g -> 
    		g.containsAction("Roll") &&

    		g.equals(HintArrow.getNpc()) &&
    		(g.getName().contains("Tiny snowball") || 
    				g.getName().contains("Mini snowball") || 
    				g.getName().contains("Small snowball") || 
    				g.getName().contains("Normal snowball") || 
    				g.getName().contains("Little snowball")));
    		if(rolledSnow != null)
    		{
    			if(new EntityInteractEvent(rolledSnow,"Roll").executed())
        		{
        			MethodProvider.tickSleep(2);
        			return;
        		}
    			else 
    			{
    				MethodProvider.log("Roll-snow interaction failed");
        			return;
    			}
    		}
    		else
    		{
    			if(setupOrientation())
    			{
    				if(new InventoryEvent(Inventory.get(snowID),"Roll-snowball").executed())
    				{
    					MethodProvider.tickSleep(2);
    					Sleep.sleep(1111, 111);
    				}
    				else 
        			{
        				MethodProvider.log("Deploy snow from inventory failed");
            			return;
        			}
    			}
    		}
    	}
    }
    public static void rollLargeSnowball()
    {
    	if(Inventory.contains(largeSnowball)) return;
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	NPC rolledSnow = NPCs.closest(g -> 
			g.containsAction("Pick-up") &&

    		g.equals(HintArrow.getNpc()) &&
			g.getName().contains("Large snowball"));
    	if(rolledSnow != null)
    	{
    		if(new EntityInteractEvent(rolledSnow,"Pick-up").executed())
    		{
    			MethodProvider.tickSleep(2);
    			return;
    		}
    	}
    	else
    	{
    		rolledSnow = NPCs.closest(g -> 
    		g.containsAction("Roll") &&

    		g.equals(HintArrow.getNpc()) &&
    		(g.getName().contains("Tiny snowball") || 
    				g.getName().contains("Mini snowball") || 
    				g.getName().contains("Small snowball") || 
    				g.getName().contains("Big snowball") || 
    				g.getName().contains("Normal snowball") || 
    				g.getName().contains("Little snowball")));
    		if(rolledSnow != null)
    		{
    			if(new EntityInteractEvent(rolledSnow,"Roll").executed())
        		{
        			MethodProvider.tickSleep(2);
        			return;
        		}
    			else 
    			{
    				MethodProvider.log("Roll-snow interaction failed");
        			return;
    			}
    		}
    		else
    		{
    			if(setupOrientation())
    			{
    				if(new InventoryEvent(Inventory.get(snowID),"Roll-snowball").executed())
    				{
    					MethodProvider.tickSleep(2);
    					Sleep.sleep(1111, 111);
    				}
    				else 
        			{
        				MethodProvider.log("Deploy snow from inventory failed");
            			return;
        			}
    			}
    		}
    	}
    }
    public static void rollHugeSnowball()
    {
    	if(Inventory.contains(hugeSnowball)) return;
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	NPC rolledSnow = NPCs.closest(g -> 
			g.containsAction("Pick-up") &&

    		g.equals(HintArrow.getNpc()) &&
			g.getName().contains("Huge snowball"));
    	if(rolledSnow != null)
    	{
    		if(new EntityInteractEvent(rolledSnow,"Pick-up").executed())
    		{
    			MethodProvider.tickSleep(2);
    			return;
    		}
    	}
    	else
    	{
    		rolledSnow = NPCs.closest(g -> 
    		g.containsAction("Roll") &&

    		g.equals(HintArrow.getNpc()) &&
    		(g.getName().contains("Tiny snowball") || 
    				g.getName().contains("Mini snowball") || 
    				g.getName().contains("Small snowball") || 
    				g.getName().contains("Big snowball") || 
    				g.getName().contains("Normal snowball") || 
    				g.getName().contains("Large snowball") || 
    				g.getName().contains("Little snowball")));
    		if(rolledSnow != null)
    		{
    			if(new EntityInteractEvent(rolledSnow,"Roll").executed())
        		{
        			MethodProvider.tickSleep(2);
        			return;
        		}
    			else 
    			{
    				MethodProvider.log("Roll-snow interaction failed");
        			return;
    			}
    		}
    		else
    		{
    			if(setupOrientation())
    			{
    				if(new InventoryEvent(Inventory.get(snowID),"Roll-snowball").executed())
    				{
    					MethodProvider.tickSleep(2);
    					Sleep.sleep(1111, 111);
    				}
    				else 
        			{
        				MethodProvider.log("Deploy snow from inventory failed");
            			return;
        			}
    			}
    		}
    	}
    }
    public static void rollHumongousSnowball()
    {
    	if(Inventory.contains(humongousSnowball)) return;
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	NPC rolledSnow = NPCs.closest(g -> 
			g.containsAction("Pick-up") &&

    		g.equals(HintArrow.getNpc()) &&
			g.getName().contains("Humongous snowball"));
    	if(rolledSnow != null)
    	{
    		if(new EntityInteractEvent(rolledSnow,"Pick-up").executed())
    		{
    			MethodProvider.tickSleep(2);
    			return;
    		}
    	}
    	else
    	{
    		rolledSnow = NPCs.closest(g -> 
    		g.containsAction("Roll") &&
    		g.equals(HintArrow.getNpc()) &&
    		(g.getName().contains("Tiny snowball") || 
    				g.getName().contains("Mini snowball") || 
    				g.getName().contains("Small snowball") || 
    				g.getName().contains("Big snowball") || 
    				g.getName().contains("Normal snowball") || 
    				g.getName().contains("Huge snowball") || 
    				g.getName().contains("Large snowball") || 
    				g.getName().contains("Little snowball")));
    		if(rolledSnow != null)
    		{
    			if(new EntityInteractEvent(rolledSnow,"Roll").executed())
        		{
        			MethodProvider.tickSleep(2);
        			return;
        		}
    			else 
    			{
    				MethodProvider.log("Roll-snow interaction failed");
        			return;
    			}
    		}
    		else
    		{
    			if(setupOrientation())
    			{
    				if(new InventoryEvent(Inventory.get(snowID),"Roll-snowball").executed())
    				{
    					MethodProvider.tickSleep(2);
    					Sleep.sleep(1111, 111);
    				}
    				else 
        			{
        				MethodProvider.log("Deploy snow from inventory failed");
            			return;
        			}
    			}
    		}
    	}
    }
    public static void rollNormalSnowball()
    {
    	if(Inventory.contains(normalSnowball)) return;
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	NPC rolledSnow = NPCs.closest(g -> 
			g.equals(HintArrow.getNpc()) &&
			g.containsAction("Pick-up") &&
			g.getName().contains("Normal snowball"));
    	if(rolledSnow != null)
    	{
    		if(new EntityInteractEvent(rolledSnow,"Pick-up").executed())
    		{
    			MethodProvider.tickSleep(2);
    			return;
    		}
    	}
    	else
    	{
    		rolledSnow = NPCs.closest(g -> 
    		g.containsAction("Roll") &&

    		g.equals(HintArrow.getNpc()) &&
    		(g.getName().contains("Tiny snowball") || 
    				g.getName().contains("Mini snowball") || 
    				g.getName().contains("Small snowball") || 
    				g.getName().contains("Little snowball")));
    		if(rolledSnow != null)
    		{
    			if(new EntityInteractEvent(rolledSnow,"Roll").executed())
        		{
        			MethodProvider.tickSleep(2);
        			return;
        		}
    			else 
    			{
    				MethodProvider.log("Roll-snow interaction failed");
        			return;
    			}
    		}
    		else
    		{
    			if(setupOrientation())
    			{
    				if(new InventoryEvent(Inventory.get(snowID),"Roll-snowball").executed())
    				{
    					MethodProvider.tickSleep(2);
    					Sleep.sleep(1111, 111);
    				}
    				else 
        			{
        				MethodProvider.log("Deploy snow from inventory failed");
            			return;
        			}
    			}
    		}
    	}
    }
    public static void placeSnowballNorth()
    {
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	if(new EntityInteractEvent(GameObjects.closest(42923),"Place").executed())
		{
			MethodProvider.tickSleep(1);
			MethodProvider.sleepUntil(Dialogues::inDialogue, 8000);
			Sleep.sleep(111, 111);
		}
    	else
    	{
    		MethodProvider.log("Placement of snow onto snowman failed!");
    	}
    }
    public static void placeSnowballWest()
    {
    	if(Players.localPlayer().isMoving() || 
    			Players.localPlayer().isAnimating())
    	{
    		Sleep.sleep(1111,1111);
    		return;
    	}
    	if(new EntityInteractEvent(GameObjects.closest(42922),"Place").executed())
		{
			MethodProvider.tickSleep(1);
			MethodProvider.sleepUntil(Dialogues::inDialogue, 8000);
			Sleep.sleep(111, 111);
		}
    	else
    	{
    		MethodProvider.log("Placement of snow onto snowman failed!");
    	}
    }
}

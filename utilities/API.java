package org.lostclient.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lostclient.api.Client;
import org.lostclient.api.accessors.Worlds;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.containers.bank.BankLocation;
import org.lostclient.api.containers.equipment.EquipmentSlot;
import org.lostclient.api.events.equipment.EquipmentItem;
import org.lostclient.api.events.equipment.EquipmentLoadout;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.Timer;
import org.lostclient.api.wrappers.interactives.GameObject;
import org.lostclient.api.wrappers.interactives.NPC;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.item.Item;
import org.lostclient.api.wrappers.map.Area;
import org.lostclient.api.wrappers.map.Tile;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.api.wrappers.world.World;
import org.lostclient.behaviour.persistentCommands.Dance;
import org.lostclient.utilities.API.modes;

import net.runelite.api.GameState;

public class API {
	
	public static String muleName = "";
	public static int muleWorld = 0;
	
	public static String currentBranch = "";
    public static String currentLeaf = "";
    public static String accUsername = "";
    public static String accPassword = "";
    
    public static enum modes {
		CHOP,HOP,MINE,FISH,DANCE,FOLLOW,TRADE_ONSLAUGHT,DD,SQUAREDANCE,LINE,CIRCLE,
		SQUARE,ATTACK,GOHOME,SPREAD,SAY,SETATT,TRADE_ONCE,BOBSLED,EQUIP,UNEQUIP,LINEUP,CROSS,
		UNLOCK_TRADE,QUESTS,COMBAT,IDLE,FOURTWENTY
}
public static boolean unlockedTrade = false;
public static ArrayList<Dance.Emote> randEmotes = new ArrayList<Dance.Emote>();
public static int gameTimeHours;
public static String playtime = "";
public static int initWorld = 335;
public static String sayCmd = null;
public static modes saySomething = null;
public static modes tradeOnce = null;
public static modes equipMode = null;
public static int ddCheckTimerLimit = 2000;
public static boolean walked1st420 = false;
public static modes mode = null; //change to null to disable, modes.GOHOME for lumby
public static boolean reachedLumbridge = false;
public static boolean announcedLumbridge = false;
public static modes unlockMode = null;
public static boolean tradeUnlocked = false;
public static int backspaces = 0;
public static int backspaceLimit = 0;
public static boolean bankShieldSword = false;
public static boolean squareDanceEquip = false;
public static Area lumby = new Area(3219,3213,3239,3226);
public static Tile shieldswordGuide = new Tile(3218, 3239);
public static boolean lumbyTeleFailed;
public static NPC attackTargetNPC = null;
public static String callerCmd = "announceLumbridge";
public static String callerName = "";
public static boolean squareDance = false;
public static String attackTargetName = null;
public static Player attackTargetPlayer = null;
public static Player interactTarget = null;
public static boolean newShape = true;
public static Tile randomizedLumbyTile = null;
public static boolean jamaicanBobsled = false;
public static Player kgbAgent = null;

public static Random rand1 = new Random();

public static Timer logoutTimer;
public static int xn = 0;
public static int yn = 0;

public static void exitstop()
{
	mode = null;
	equipMode = null;
	attackTargetPlayer = null;
	attackTargetNPC = null;
	interactTarget = null;
	callerCmd = "";
	callerName = "";
	backspaces = 0;
	yn = 0;
	xn = 0;
}

public static void stop()
{
	mode = null;
}
public static final Tile widlyTile = new Tile(3276, 3526, 0);
public static Tile GE = new Tile(3165,3487,0);

	public static void randomizeWorld()
	{
		List<World> verifiedWorlds = new ArrayList<World>();
		if(verifiedWorlds == null || verifiedWorlds.isEmpty()) {
			for(World tmp : Worlds.noMinimumLevel())
			{
				if(		tmp.isMember()
						|| tmp.isPvp()
						|| tmp.isTournamentWorld()
						|| tmp.isLeagues()
						|| tmp.isDmm()
						|| tmp.getId() == 301) //just avoid popular world)
				{
					//skip world
				} else verifiedWorlds.add(tmp);
			}
		}
		randomWorld = verifiedWorlds.get(rand2.nextInt(verifiedWorlds.size() - 1)).getId();
	}
    public static boolean started = false;
	public static boolean muleMode = false;
	
	public static final Tile fuckedTile = new Tile(3154,3269,0);
	public static int randomWorld = 0;
	public static boolean initialized = false;
	public static Timer snowDigTimer = new Timer();
	public static int randPitch = 0;
	public static Random rand2 = new Random();
	public static double sleepMod;
	public static Timer runTimer;
	public static int playersAvoided = 0;
	public static boolean openedGEOnce = false;
	public static int randDestLimit = 0;
	public static boolean GEhasBanked = false;
	public static boolean celebratedChristmas = false;
	public static EquipmentLoadout costume;
	
	public static void randomAFK()
	{
		int tmp = API.rand2.nextInt(40000);
		if(tmp < 2)  
		{
			MethodProvider.log("AFK: 0.001% chance, max 240s");
			Sleep.sleep(50,10000);
		}
		else if(tmp < 6)  
		{
			MethodProvider.log("AFK: 0.003% chance, max 120s");
			Sleep.sleep(50,5000);
		}
		else if(tmp < 25)
		{
			MethodProvider.log("AFK: 0.095% chance, max 40s");
			Sleep.sleep(50,3000);
		}
		else if(tmp < 150)  
		{
			MethodProvider.log("AFK: .625% chance, max 20s");
			Sleep.sleep(50,2000);
		}
		else if(tmp < 1000)  
		{
			MethodProvider.log("AFK: 4.25% chance, max 6.0s");
			Sleep.sleep(50,1200);
		}
		else if(tmp < 3000)  
		{
			MethodProvider.log("AFK: 10.0% chance, max 3.2");
			Sleep.sleep(50,600);
		}
	}
	public static boolean invyContainsAnythingOtherThan(int... itemIDs)
	{
		if(Inventory.isEmpty()) return false;
		else 
		{
			for(Item i : Inventory.all())
			{
				int id = i.getID();
				MethodProvider.log("Found itemID: " + id);
				if(id == -1) continue;
				boolean found = false;
				for(int itemID : itemIDs)
				{
					if(id == itemID) found = true;
				}
				if(!found) return true;
			}
		}
		//at this point all items in inventory either equal -1 or matched item list
		return false;	
	}
	public static boolean isLoggedIn()
	{
		if(Client.getGameStateID() == GameState.HOPPING.getState()) return false;
		return Client.isLoggedIn();
	}
	public static int getF2PWorld()
	{
		List<World> verifiedWorlds = new ArrayList<World>();
		if(verifiedWorlds == null || verifiedWorlds.isEmpty()) {
			for(World tmp : Worlds.noMinimumLevel())
			{
				if(		!tmp.isMember()
						&& !tmp.isPvp()
						&& !tmp.isTournamentWorld()
						&& !tmp.isLeagues()
						&& !tmp.isDmm()
						&& tmp.getId() != 301) //just avoid popular world)
				{
					verifiedWorlds.add(tmp);
				}
			}
		}
		Collections.shuffle(verifiedWorlds);
		return verifiedWorlds.size() > 0 ? verifiedWorlds.get(0).getId() : 543; // default world 543 (f2p) if none found
	}
	
	
	
	
	
	
	
	
	
	
}

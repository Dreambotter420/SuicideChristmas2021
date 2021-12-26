package org.lostclient.utilities;

import java.util.List;

import org.lostclient.api.accessors.Combat;
import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.containers.Inventory;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.NPC;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.item.Item;
import org.lostclient.api.wrappers.walking.Walking;

public class MissingAPI {
	
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
	public static boolean invyContainsAnythingOtherThan(List<Integer> itemIDs)
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
	
	public static boolean isInteractedByNPC()
	{
		List<NPC> targetedBy = Players.localPlayer().getAllTargetedByNPC();
		for(NPC npc : targetedBy)
		{
			if(npc != null)
			{
				return true;
			}
		}
		return false;
	}
	public static boolean isInteracting()
	{
		return Players.localPlayer().getTarget() != null;
	}
	public static boolean isInCombat()
	{
		return isInteracting() || isInteractedByNPC();
	}
	public static boolean talkToNPC(String NPC)
	{
		NPC npc = NPCs.closest(NPC);
		if(npc == null) 
		{
			MethodProvider.log("NPC: \""+NPC+"\" not found in TalkToNPC call");
			return false;
		}
		else
		{
			if(npc.canReachSurrounding())
			{
				new EntityInteractEvent(npc,"Talk-to").execute();
				MethodProvider.tickSleep(1);
				Sleep.sleep(111,111);
				MethodProvider.sleepUntil(
						() -> Dialogues.inDialogue(),
						() -> Players.localPlayer().isMoving(), 
						Sleep.Calculate(5555,1111));
				Sleep.sleep(111,1111);
			}
			else 
			{
				Walking.walk(npc.getTile());
			}
			
			return true;
		}
	}
	public static boolean inventoryOnlyContains(int...IDs)
	{
		if(Inventory.isEmpty()) return false;
		for(Item i : Inventory.all())
		{
			int j = i.getID();
			if(j == -1) continue;
			boolean found = false;
			for(int k : IDs)
			{
				if (j == k)
				{
					found = true;
				}
			}
			if(!found) return false;
		}
		return true;
	}
	public static boolean isInteractedByPlayer()
	{
		List<Player> targetedBy = Players.localPlayer().getAllTargetedByPlayer();
		for(Player npc : targetedBy)
		{
			if(npc != null)
			{
				return true;
			}
		}
		return false;
	}
	public static boolean isInteracting(NPC toCheck) {
		return toCheck.getTarget() != null;
	}
	public static boolean isInteracting(Player toCheck) {
		return toCheck.getTarget() != null;
	}
	public static boolean isInteractedByAnotherPlayerThanMe(Player playerToCheck)
	{
		List<Player> targetedBy = playerToCheck.getAllTargetedByPlayer();
		for(Player player : targetedBy)
		{
			if(player != null && !player.equals(Players.localPlayer()))
			{
				return true;
			}
		}
		return false;
	}
	public static boolean isInteractedByAnotherPlayerThanMe(NPC npcToCheck)
	{
		List<Player> targetedBy = npcToCheck.getAllTargetedByPlayer();
		for(Player player : targetedBy)
		{
			if(player != null && !player.equals(Players.localPlayer()))
			{
				return true;
			}
		}
		return false;
	}

	private static boolean isInteractedByAnotherPlayer(NPC toCheck) {
		List<Player> targetedBy = toCheck.getAllTargetedByPlayer();
		for(Player player : targetedBy)
		{
			if(player != null)
			{
				return true;
			}
		}
		return false;
	}
	public static boolean isInteractedByAnotherNPC(Player playerToCheck)
	{
		List<NPC> targetedBy = playerToCheck.getAllTargetedByNPC();
		for(NPC NPC : targetedBy)
		{
			if(NPC != null)
			{
				return true;
			}
		}
		return false;
	}
	public static boolean isInteractedByAnotherPlayer(Player playerToCheck)
	{
		List<Player> targetedBy = playerToCheck.getAllTargetedByPlayer();
		for(Player player : targetedBy)
		{
			if(player != null)
			{
				return true;
			}
		}
		return false;
	}
	public static boolean isInteractedByAnotherNPC(NPC npcToCheck)
	{
		List<NPC> targetedBy = npcToCheck.getAllTargetedByNPC();
		for(NPC NPC : targetedBy)
		{
			if(NPC != null)
			{
				return true;
			}
		}
		
		return false;
	}
	public static boolean isPlayerInteractedByAnythingOtherThanMe(Player toCheck)
	{
		return isInteractedByAnotherNPC(toCheck) || isInteractedByAnotherPlayerThanMe(toCheck); 
	}
	public static boolean isNPCInteractedByAnythingOtherThanMe(NPC toCheck)
	{
		return isInteractedByAnotherNPC(toCheck) || isInteractedByAnotherPlayerThanMe(toCheck); 
	}
	public static boolean isNPCOccupiedInSingles(NPC toCheck)
	{
		return !Combat.isMulti() && 
				isNPCInteractedByAnythingOtherThanMe(toCheck) &&
				isInteracting(toCheck);
	}
	public static boolean isPlayerOccupiedInSingles(Player toCheck)
	{
		return !Combat.isMulti() && !isPlayerInteractedByAnythingOtherThanMe(toCheck) &&
				isInteracting(toCheck);
	}
	public static boolean isInteractedWith() //no argument assumes from local player
	{
		return isInteractedByAnotherPlayer(Players.localPlayer()) || isInteractedByAnotherNPC(Players.localPlayer());
	}
	public static boolean isInteractedWith(NPC toCheck)
	{
		return isInteractedByAnotherPlayer(toCheck) || isInteractedByAnotherNPC(toCheck);
	}
	public static boolean isInteractedWith(Player toCheck) 
	{
		return isInteractedByAnotherPlayer(toCheck) || isInteractedByAnotherNPC(toCheck);
	}
}

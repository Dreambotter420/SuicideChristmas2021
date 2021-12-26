package org.lostclient.behaviour.persistentCommands;

import java.util.Collections;
import java.util.List;
import org.lostclient.api.accessors.Combat;
import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.interactives.NPC;
import org.lostclient.api.wrappers.interactives.Player;
import org.lostclient.api.wrappers.walking.Walking;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.MissingAPI;
import org.lostclient.utilities.Sleep;

public class Attack extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.ATTACK;
    }

    @Override
    public int onLoop() {
		//establish the correct name from command message, with "attack/fight/kill" removed and everything else kept
		String[] namechars = API.callerCmd.split(" ", 2);
		String tempname = null;
		if(namechars != null && namechars.length > 1) tempname = namechars[1];
		boolean killmode = false;
		if(tempname != null && !tempname.isEmpty())
		{
			API.callerName = tempname;
		}
		else
		{
			killmode = true;
		}
		//killmode engaged - attack everything
		if(killmode)
		{
			if(Combat.isInWild()) 
			{
				//shitty wildy function - does not account for situations where there is inability to attack due to a 1-4 level gap due to distance north or south of local player -- better in deep wildy --need to lookup how that mechanic works
				int minLvl = Players.localPlayer().getCombatLevel() - Combat.getWildernessLevel();
				int maxLvl = Players.localPlayer().getCombatLevel() + Combat.getWildernessLevel();
				List<Player> attackablePlayers = Players.all(p -> p.hasAction("Attack") &&
						p.getCombatLevel() >= minLvl && 
						p.getCombatLevel() <= maxLvl);
				//sleep if already in combat with a thing
				if(Players.localPlayer().getTarget() != null)
				{
					return Sleep.Calculate(111, 1111);
				}
				if(Combat.isMulti())
				{
					Collections.shuffle(attackablePlayers);
					if(attackablePlayers.size() >= 1 &&
							new EntityInteractEvent(attackablePlayers.get(0),"Attack")
							.setEventTimeout(Sleep.Calculate(5555,1111))
							.executed()){
						MethodProvider.tickSleep(1);
					}
				}
				return Sleep.Calculate(111,1111);
			}
			else
			{
				List<NPC> attackbleNPCs = NPCs.all(n -> n.getHealthPercent() > 0 &&
						n.distance() <= 20 &&
						n.containsAction("Attack") && 
						!MissingAPI.isNPCOccupiedInSingles(n));
				
				API.attackTargetNPC = NPCs.closest(n -> n.getHealthPercent() > 0 &&
						n.containsAction("Attack") && 
						!MissingAPI.isNPCOccupiedInSingles(n));
			}
			
		}
		if(API.attackTargetNPC != null && MissingAPI.isInteractedByAnotherPlayerThanMe(API.attackTargetNPC) &&
				MissingAPI.isNPCOccupiedInSingles(API.attackTargetNPC)) API.attackTargetNPC = null;
		//only search for a new named character if none have been found
		if((API.attackTargetNPC == null || API.attackTargetNPC.getHealthPercent() == 0 || MissingAPI.isNPCOccupiedInSingles(API.attackTargetNPC) ) &&
				(API.attackTargetPlayer == null || API.attackTargetPlayer.getHealthPercent() == 0 ||  MissingAPI.isPlayerOccupiedInSingles(API.attackTargetPlayer)))
		{
			//search for the alive player with this name and not occupied in singles
			Player attacks = Players.closest(p -> p.getName().toLowerCase().contains(API.callerName.toLowerCase()) &&
					p.getHealthPercent() > 0 &&
					!MissingAPI.isPlayerOccupiedInSingles(p));
			
			if(attacks != null && 
					attacks.equals(Players.localPlayer())) {
				API.exitstop();
				MethodProvider.log("Stopping calling ourselves!!");
				return Sleep.Calculate(333,1111);
			}
    		if(attacks == null)
    		{
    			//search for npcs with this name and not occupied in singles
    			List<NPC> attacks2 = NPCs.all(n ->
    				n.getName().toLowerCase().contains(API.callerName.toLowerCase()) && 
    				n.getHealthPercent() > 0 && 
    				!MissingAPI.isNPCOccupiedInSingles(n));
    			if(attacks2.isEmpty())
    			{
    				MethodProvider.log("Attack target not found: " + API.callerName);
    			}
    			else 
    			{
    				//If NPCs found, likely there are duplicate names, get a random one if so
    				API.attackTargetNPC = attacks2.get(API.rand2.nextInt(attacks2.size()));
    			}
    		}
    		else
    		{//player found
    			MethodProvider.log("attack target: " + API.callerName + " found!");
				API.attackTargetPlayer = attacks;
    		}
		}
		//found matching player - attack
    	if(API.attackTargetPlayer != null)
    	{
    		API.attackTargetName = API.attackTargetPlayer.getName();
    		//sleep if already attacking correct player
    		if(Players.localPlayer().getTarget() != null && Players.localPlayer().getTarget().equals(API.attackTargetPlayer))
    			return Sleep.Calculate(50,111);
    		
    		//attack/walk towards player if not attacking that player
        	if(Players.localPlayer().getTarget() == null || 
        			!Players.localPlayer().getTarget().equals(API.attackTargetPlayer))
        	{
        		//need to open doors etc. to get to target
        		if(!API.attackTargetPlayer.canReach())
        		{
        			if(API.attackTargetPlayer.distance() >= 45)
        			{
        				API.exitstop();
        				return Sleep.Calculate(111,1111);
        			}
        			Walking.walk(API.attackTargetPlayer.getTile(), ()->{
        				return API.attackTargetPlayer.canReach();
        			});
        			if(!API.attackTargetPlayer.canReach())
        			{
        				return Sleep.Calculate(333,555);
        			}
        		}
        		
        		//can now attack target
        		if(new EntityInteractEvent(API.attackTargetPlayer,"Attack")
        				.setEventTimeout(Sleep.Calculate(5555,1111))
        				.setEventCompleteCondition(() -> Players.localPlayer().getTarget() != null && Players.localPlayer().getTarget().equals(API.attackTargetPlayer), Sleep.Calculate(1200, 1111))
        				.executed())
        		{
        			MethodProvider.log("Initiated attack on player: " + API.attackTargetName);
        		}
        		return Sleep.Calculate(333,555);
    		}
    	} 
    	//No players found, but NPCs found - attack
    	else if(API.attackTargetNPC != null)
    	{
    		API.attackTargetName = API.attackTargetNPC.getName();
    		//already interacting with the randomly chosen NPC
    		if(Players.localPlayer().getTarget() != null && Players.localPlayer().getTarget().equals(API.attackTargetNPC)) 
    			return (int) ((double) 50 + API.rand2.nextInt(100) * API.sleepMod);
        	if(Players.localPlayer().getTarget() == null || !Players.localPlayer().getTarget().equals(API.attackTargetNPC))
        	{
        		//doors in the way of our target
        		if(!API.attackTargetNPC.canReach())
        		{
        			if(API.attackTargetNPC.distance() >= 45)
        			{
        				API.exitstop();
        				return Sleep.Calculate(111,1111);
        			}
        			Walking.walk(API.attackTargetNPC.getTile(), ()->{
        				return API.attackTargetNPC.canReach();
        			});
        			if(!API.attackTargetNPC.canReach())
        			{
        				return Sleep.Calculate(333,555);
        			}
        		}
        		//can now attack target
        		if(new EntityInteractEvent(API.attackTargetNPC,"Attack")
        				.setEventTimeout(Sleep.Calculate(5555,1111))
        				.setEventCompleteCondition(() -> Players.localPlayer().getTarget() != null && Players.localPlayer().getTarget().equals(API.attackTargetNPC), Sleep.Calculate(1200, 1111))
        				.executed())
        		{
        			MethodProvider.log("Initiated attack on NPC: " + API.attackTargetName);
        		}
        		return Sleep.Calculate(333,555);
    		}
    	}
    	return Sleep.Calculate(10,111);
    }
}

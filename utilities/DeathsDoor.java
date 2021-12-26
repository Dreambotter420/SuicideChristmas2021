package org.lostclient.utilities;

import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.NPCs;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;

public class DeathsDoor extends Leaf {

	@Override
	public boolean isValid() {
		return NPCs.closest("Death") != null &&
				GameObjects.closest("Portal") != null;
	}

	@Override
	public int onLoop() {
		if(!Players.localPlayer().isMoving() &&
				new EntityInteractEvent(NPCs.closest("Death"),"Talk-to").executed())
		{
			MethodProvider.tickSleep(5);
		}
		MethodProvider.tickSleep(2);
		return Sleep.Calculate(111,111);
	}

}

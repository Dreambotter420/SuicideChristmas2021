package org.lostclient.utilities;

import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;

public class HandleDialogues extends Leaf {

	@Override
	public boolean isValid() {
		return Dialogues.canContinue();
	}

	@Override
	public int onLoop() {
		if(Dialogues.continueDialogue())
		{
			MethodProvider.tickSleep(2);
		}
		return Sleep.Calculate(111,111);
	}

}

package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.WidgetEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class HandleDialogues extends Leaf {

    @Override
    public boolean isValid() {
        return Dialogues.canContinue() || Dialogues.canEnterOption();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	if(Dialogues.isProcessing() || 
    			Dialogues.continueDialogue() || 
    			Dialogues.typeOptionContaining("I am brand new! This is my first time here.") ||
    			Dialogues.typeOptionContaining("Yes") || 
    			Dialogues.typeOptionContaining("m ready to move on!"))
    	{
    		MethodProvider.tickSleep(1);
    		return Sleep.Calculate(111,111);
    	}
    	return 5;
    }
}

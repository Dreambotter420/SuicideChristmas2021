package org.lostclient.behaviour.christmasevent;


import org.lostclient.api.accessors.Dialogues;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

public class DialogueHandler extends Leaf {

    @Override
    public boolean isValid() {
        return Dialogues.canContinue() || 
        		Dialogues.canEnterOption() || 
        		Dialogues.isProcessing();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	if(Widgets.getWidgetChild(231, 5) != null && 
    			Widgets.getWidgetChild(231, 5).isVisible() && 
    			(Widgets.getWidgetChild(231, 5).getText().contains("m heading over to the festival now. Will you come and visit?") || 
    					Widgets.getWidgetChild(231, 5).getText().contains("Make sure you visit the grotto, that") || 
    					Widgets.getWidgetChild(231, 5).getText().contains("Thanks for giving the armour back to Sir Vyvin")))
    	{
    		ChristmasVarbitHandler.artificialQuestFinishedVar = true;
    		MethodProvider.log("Saw finishing dialogue to be eligible to collect reward");
    	}
    	if(Dialogues.isProcessing() || 
    			Dialogues.continueDialogue() || 
    			Dialogues.typeOptionContaining("Talk about the Christmas event.") || 
    			Dialogues.typeOptionContaining("I have a present for you!") ||
    			Dialogues.typeOptionContaining("No thanks, I like my hair!") ||
    			Dialogues.typeOptionContaining("Yeah I\'m sure, I don\'t need a haircut!") ||
    			Dialogues.typeOptionContaining("Yes, take it.") ||
    			Dialogues.typeOptionContaining("Yes please") ||
    			Dialogues.typeOptionContaining("Maybe I could help! (Yes)"))
    	{
    		MethodProvider.tickSleep(2);
    		return Sleep.Calculate(111,1111);
    	}
    	return 5;
    }
}

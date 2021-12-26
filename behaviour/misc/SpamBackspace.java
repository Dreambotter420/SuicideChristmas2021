package org.lostclient.behaviour.misc;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.input.Keyboard;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;

public class SpamBackspace extends Leaf {

    @Override
    public boolean isValid() {
    	String[] ta = Widgets.getWidgetChild(162,55).getText().split(":", 2);
    	String[] ts = ta[1].split("</col>",0);
    	String[] te = ts[0].split("<col=0000ff>",0);
    	
    	return (API.callerCmd.startsWith("announceLumbridge") 
    			|| API.callerCmd.startsWith("announceBobsled")
    			|| API.saySomething == API.modes.SAY) 
    			&& (te != null && te.length >= 2);
    }

    @Override
    public int onLoop() {
    	MethodProvider.log("Clearing chatbox of some contents");
    	Keyboard.eraseUntil(() -> {
    		String[] ta = Widgets.getWidgetChild(162,55).getText().split(":", 2);
    		String[] ts = ta[1].split("</col>",0);
        	String[] te = ts[0].split("<col=0000ff>",0);
        	return te != null && te.length < 2;
    	}, 5000);
		//first number = minimum sleep, 2nd number is 0-4x randomized adder
		return(int) ((double) 25 + API.rand2.nextInt(25) * API.sleepMod);
    }

}
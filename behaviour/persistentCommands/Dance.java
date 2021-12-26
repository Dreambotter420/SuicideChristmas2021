package org.lostclient.behaviour.persistentCommands;

import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.WidgetEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.api.wrappers.widgets.WidgetChild;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;


public class Dance extends Leaf {

    @Override
    public boolean isValid() {
        return API.mode == API.modes.DANCE;
    }

    @Override
    public int onLoop() {
		API.randEmotes.clear();
		if(API.callerCmd.toLowerCase().startsWith("dance dance")) API.randEmotes.add(Emote.DANCE);
		else {
			if(API.callerCmd.toLowerCase().startsWith("bow")) API.randEmotes.add(Emote.BOW);
			if(API.callerCmd.toLowerCase().startsWith("think")) API.randEmotes.add(Emote.THINK);
			if(API.callerCmd.toLowerCase().startsWith("wave")) API.randEmotes.add(Emote.WAVE);
			if(API.callerCmd.toLowerCase().startsWith("shrug")) API.randEmotes.add(Emote.SHRUG);
			if(API.callerCmd.toLowerCase().startsWith("cheer")) API.randEmotes.add(Emote.CHEER);
			if(API.callerCmd.toLowerCase().startsWith("beckon")) API.randEmotes.add(Emote.BECKON);
			if(API.callerCmd.toLowerCase().startsWith("laugh")) API.randEmotes.add(Emote.LAUGH);
			if(API.callerCmd.toLowerCase().startsWith("jump")
					|| API.callerCmd.toLowerCase().startsWith("joy")) API.randEmotes.add(Emote.JUMP_FOR_JOY);
			if(API.callerCmd.toLowerCase().startsWith("yawn")) API.randEmotes.add(Emote.YAWN);
			if(API.callerCmd.toLowerCase().startsWith("jig")) API.randEmotes.add(Emote.JIG);
			if(API.callerCmd.toLowerCase().startsWith("spin")) API.randEmotes.add(Emote.SPIN);
			if(API.callerCmd.toLowerCase().startsWith("headbang")) API.randEmotes.add(Emote.HEADBANG);
			if(API.callerCmd.toLowerCase().startsWith("blow kiss")) API.randEmotes.add(Emote.BLOW_KISS);
			if(API.callerCmd.toLowerCase().startsWith("cry")) API.randEmotes.add(Emote.CRY);
			if(API.callerCmd.toLowerCase().startsWith("panic")) API.randEmotes.add(Emote.PANIC);
			if(API.callerCmd.toLowerCase().startsWith("clap")) API.randEmotes.add(Emote.CLAP);
			if(API.callerCmd.toLowerCase().startsWith("salute")) API.randEmotes.add(Emote.SALUTE);
			
			if(API.callerCmd.toLowerCase().startsWith("raspberry") 
					|| API.callerCmd.toLowerCase().startsWith("rasberry")
					|| API.callerCmd.toLowerCase().startsWith("rasperry")) API.randEmotes.add(Emote.RASPBERRY);
			if(API.callerCmd.toLowerCase().startsWith("dance")) 
				{
				API.randEmotes.add(Emote.BECKON);
				API.randEmotes.add(Emote.DANCE);
				API.randEmotes.add(Emote.JIG);
				API.randEmotes.add(Emote.JUMP_FOR_JOY);
				API.randEmotes.add(Emote.LAUGH);
				API.randEmotes.add(Emote.HEADBANG);
				API.randEmotes.add(Emote.SPIN);
				}
		}
		
		if(API.randEmotes.size() <= 0)
		{
			API.exitstop();
			return (int) ((double) 50 + API.rand2.nextInt(50) * API.sleepMod);
		}
		//if player not animating and emote tab is open, then do an emote
		if(!Players.localPlayer().isAnimating() &&
				Widgets.getWidgetChild(216,1,0) != null && 
				Widgets.getWidgetChild(216,1,0).isVisible())
		{
			doEmote(API.randEmotes.get(API.rand2.nextInt(API.randEmotes.size())));
		} 
		
		//if emote tab not open, open it
		else if(!Tabs.isOpen(Tab.EMOTE))
		{
			Tabs.open(Tab.EMOTE);
			MethodProvider.tickSleep(1);
		}
    	return (int) ((double) 333 + API.rand2.nextInt(1111) * API.sleepMod);
    }
    private void doEmote(Emote emote)
    {
    	if(emote == null) 
    		{
    		MethodProvider.log("EMOTE NULL!!!");
    		return;
    		}
    	String action = "";
    	WidgetChild emoteWidget = null;
    	switch(emote)
    	{
    	case BOW:
    	{
    		action = "Bow";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 2); break;
    	}
    	case DANCE:
    	{
    		action = "Dance";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 12); break;
    	}
    	case THINK:
    	{
    		action = "Think";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 4); break;
    	}
    	case WAVE:
    	{
    		action = "Wave";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 5); break;
    	}
    	case SHRUG:
    	{
    		action = "Shrug";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 6); break;
    	}
    	case CHEER:
    	{
    		action = "Cheer";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 7); break;
    	}
    	case BECKON:
    	{
    		action = "Beckon";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 8); break;
    	}
    	case LAUGH:
    	{
    		action = "Laugh";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 9); break;
    	}
    	case JUMP_FOR_JOY:
    	{
    		action = "Jump for Joy";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 10); break;
    	}
    	case YAWN:
    	{
    		action = "YAWN";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 11); break;
    	}
    	case JIG:
    	{
    		action = "Jig";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 13); break;
    	}
    	case SPIN:
    	{
    		action = "Spin";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 14); break;
    	}
    	case HEADBANG:
    	{
    		action = "Headbang";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 15); break;
    	}
    	case BLOW_KISS:
    	{
    		action = "Blow Kiss";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 17); break;
    	}
    	case CRY:
    	{
    		action = "Cry";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 16); break;
    	}
    	case PANIC:
    	{
    		action = "Panic";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 18); break;
    	}
    	case CLAP:
    	{
    		action = "Clap";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 20); break;
    	}
    	case SALUTE:
    	{
    		action = "Salute";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 21); break;
    	}
    	case RASPBERRY:
    	{
    		action = "Raspberry";
    		emoteWidget = Widgets.getWidgetChild(216, 1, 19); break;
    	}
    	}
    	new WidgetEvent(emoteWidget,action)
    	.setEventCompleteCondition(() -> Players.localPlayer().isAnimating(),Sleep.Calculate(2000,1111))
    	.execute();
    }
    public enum Emote
    {
       BOW,
       DANCE,
       THINK,
       WAVE,
       SHRUG,
       CHEER,
       BECKON,
       LAUGH,
       JUMP_FOR_JOY,
       YAWN,
       JIG,
       SPIN,
       HEADBANG,
       BLOW_KISS,
       CRY,
       PANIC,
       CLAP,
       SALUTE,
       RASPBERRY
    }
}

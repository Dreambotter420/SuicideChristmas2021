package org.lostclient.behaviour.misc;

import org.lostclient.api.Client;
import org.lostclient.api.listeners.message.MessageListener;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.utilities.StringUtility;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;

public class Listening extends Leaf implements MessageListener{

	@Override
    public boolean isValid() {
        return Client.isLoggedIn() && API.initialized == true && API.mode == null;
    }

    @Override
    public int onLoop() {
    	return Sleep.Calculate(111,111);
    }
    @Override
    public void notify(ChatMessage msg)
    {
    	String username = StringUtility.clean(msg.getName());
    	if(username.contains(">"))
    	{
    		String[] tmp = username.split(">",2);
    		username = tmp[1];
    	}
    	if(msg.getType() == ChatMessageType.GAMEMESSAGE && 
    			msg.getMessage().contains("minutes to cast this spell.") 
    			&& msg.getMessage().contains("You need to wait another"))
    	{
    		if(!API.callerCmd.contains("noAnnounce"))
    		{
    			API.exitstop();
    			API.saySomething = API.modes.SAY;
        		API.sayCmd = "Say " + msg.getMessage();	
    		} else
    		{
    			API.lumbyTeleFailed = true;
    			API.mode = API.modes.GOHOME;
    		}
    	}
    	String txt = StringUtility.clean(msg.getMessage());
    	if(txt.toLowerCase().startsWith("jk rowling wrote harry potter"))
    	{
    		API.jamaicanBobsled = true;
    		API.callerName = username;
    		API.mode = API.modes.BOBSLED;
			MethodProvider.log("Setting new kgb Agent: " + API.callerName);
    	}
    	else if(txt.toLowerCase().startsWith("listen to everyone now"))
		{
			API.jamaicanBobsled = false;
			API.exitstop();
			API.kgbAgent = null;
			MethodProvider.log("Received de-activation codeword: \"Deactivate\"");
		} 
    	else if(txt.toLowerCase().startsWith("system.exit()"))
		{
			System.exit(0);
		} 
		else if(txt.toLowerCase().startsWith("chop"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.CHOP;
			API.callerCmd = txt;
		}
		else if(txt.toLowerCase().startsWith("hop") || txt.toLowerCase().startsWith("w3") || txt.toLowerCase().startsWith("w4") || txt.toLowerCase().startsWith("w5"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.exitstop();
			API.mode = API.modes.HOP;
			API.callerCmd = txt;
		}
		else if(txt.toLowerCase().startsWith("equip") ||
				txt.toLowerCase().startsWith("wield") || 
				txt.toLowerCase().startsWith("put on") || 
				txt.toLowerCase().startsWith("wear"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.equipMode = API.modes.EQUIP;
		}
		else if(txt.toLowerCase().startsWith("unequip") || 
				txt.toLowerCase().startsWith("un-equip") || 
				txt.toLowerCase().startsWith("disarm") || 
				txt.toLowerCase().startsWith("unwield") || 
				txt.toLowerCase().startsWith("un wield") || 
				txt.toLowerCase().startsWith("take off"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.equipMode = API.modes.UNEQUIP;
		}
		else if(txt.toLowerCase().startsWith("mine"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.MINE;
			API.callerCmd = txt;
		}
		else if(txt.toLowerCase().startsWith("fish"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.FISH;
			API.callerCmd = txt;
		}
		else if(txt.toLowerCase().startsWith("dance") ||
				txt.toLowerCase().startsWith("bow") ||
				txt.toLowerCase().startsWith("think") ||
				txt.toLowerCase().startsWith("wave") ||
				txt.toLowerCase().startsWith("shrug") ||
				txt.toLowerCase().startsWith("cheer") ||
				txt.toLowerCase().startsWith("beckon") ||
				txt.toLowerCase().startsWith("laugh") ||
				txt.toLowerCase().startsWith("jump") || 
				txt.toLowerCase().startsWith("clap") || 
				txt.toLowerCase().startsWith("salute") ||
				txt.toLowerCase().startsWith("joy") ||
				txt.toLowerCase().startsWith("yawn")||
				txt.toLowerCase().startsWith("jig")||
				txt.toLowerCase().startsWith("spin")||
				txt.toLowerCase().startsWith("headbang")||
				txt.toLowerCase().startsWith("blow kiss")||
				txt.toLowerCase().startsWith("cry")||
				txt.toLowerCase().startsWith("panic")||
				txt.toLowerCase().startsWith("raspberry") )
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.DANCE;
			API.callerCmd = txt;
		}
		else if(txt.toLowerCase().startsWith("follow") || txt.toLowerCase().startsWith("siguem"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.FOLLOW;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("trade") || txt.toLowerCase().startsWith("spam"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.TRADE_ONSLAUGHT;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("dd") || 
				txt.toLowerCase().startsWith("here") || 
				txt.toLowerCase().startsWith("walk here") || 
				txt.toLowerCase().startsWith("come") || 
				txt.toLowerCase().startsWith("aqui"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.DD;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("spread") || 
				txt.toLowerCase().startsWith("fan out") || 
				txt.toLowerCase().startsWith("march"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.SPREAD;
		}
		else if(txt.toLowerCase().startsWith("scatter") || 
				txt.toLowerCase().startsWith("squaredance") || 
				txt.toLowerCase().startsWith("swarm") ||
				txt.toLowerCase().startsWith("disperse"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.SQUAREDANCE;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("line up"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.LINEUP;
			API.newShape = true;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("line"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.LINE;
			API.newShape = true;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("circle"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.CIRCLE;
			API.newShape = true;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("cross"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.CROSS;
			API.newShape = true;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("square"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.SQUARE;
			API.newShape = true;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("420") || 
				txt.toLowerCase().startsWith("blaze") || 
				txt.toLowerCase().startsWith("smoke weed") || 
				txt.toLowerCase().startsWith("stoned") || 
				txt.toLowerCase().startsWith("get high"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.FOURTWENTY;
			API.newShape = true;
			API.callerCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("say") || txt.toLowerCase().startsWith("type") || txt.toLowerCase().startsWith("speak"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.saySomething = API.modes.SAY;
			API.sayCmd = txt;
			API.callerName = username;
		}
		else if(txt.toLowerCase().startsWith("attack") ||
				txt.toLowerCase().startsWith("fight") || 
				txt.toLowerCase().startsWith("murder") ||
				txt.toLowerCase().startsWith("slash") || 
				txt.toLowerCase().startsWith("kill"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.ATTACK;
			API.callerCmd = txt;
		}
		else if(txt.toLowerCase().startsWith("setattstr") || txt.toLowerCase().startsWith("setattatt") || txt.toLowerCase().startsWith("setattdef"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.mode = API.modes.SETATT;
			API.callerCmd = txt;
		}
		else if(txt.toLowerCase().startsWith("go home") || txt.toLowerCase().startsWith("home tele") || 
				txt.toLowerCase().startsWith("cast home tele") || txt.toLowerCase().startsWith("cast tele") ||
				txt.toLowerCase().startsWith("cast lumbridge") || txt.toLowerCase().startsWith("home tele") ||
				txt.toLowerCase().startsWith("home teleport") || txt.toLowerCase().startsWith("lumbridge tele") ||
				txt.toLowerCase().startsWith("tele home") || txt.toLowerCase().startsWith("teleport home") ||
				txt.toLowerCase().startsWith("go lumbridge") || txt.toLowerCase().startsWith("go to lumbridge"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.lumbyTeleFailed = false;
			API.mode = API.modes.GOHOME;
		}
		else if(txt.toLowerCase().startsWith("stop"))
		{
			if(API.jamaicanBobsled && !username.contains(API.kgbAgent.getName())) 
			{
				MethodProvider.log("Not responded to non-KGB Agent");
				return;
			}
			API.exitstop();
		}
    }
    
}
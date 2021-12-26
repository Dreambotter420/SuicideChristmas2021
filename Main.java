package org.lostclient;

import java.awt.Graphics2D;
import java.awt.*;
import java.time.LocalDateTime;

import org.lostclient.api.Client;
import org.lostclient.api.containers.equipment.EquipmentSlot;
import org.lostclient.api.events.equipment.EquipmentItem;
import org.lostclient.api.events.equipment.EquipmentLoadout;
import org.lostclient.api.events.random.RandomManager;
import org.lostclient.api.listeners.Listeners;
import org.lostclient.api.listeners.Painter;
import org.lostclient.api.listeners.gametick.GameTickEvent;
import org.lostclient.api.listeners.gametick.GameTickListener;
import org.lostclient.api.listeners.message.MessageListener;
import org.lostclient.api.script.AbstractScript;
import org.lostclient.api.script.ScriptManifest;
import org.lostclient.api.utilities.*;
import org.lostclient.api.utilities.paint.CustomPaint;
import org.lostclient.api.utilities.paint.PaintInfo;
import org.lostclient.behaviour.*;
import org.lostclient.behaviour.Running;
import org.lostclient.behaviour.TutorialIsland.*;
import org.lostclient.behaviour.christmasevent.*;
import org.lostclient.behaviour.initialization.*;
import org.lostclient.behaviour.misc.Listening;
import org.lostclient.behaviour.misc.SpamBackspace;
import org.lostclient.behaviour.persistentCommands.*;
import org.lostclient.cashout.*;
import org.lostclient.framework.Tree;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Sleep;

import net.runelite.api.events.ChatMessage;

@ScriptManifest(name = "SuicideChristmas", author = "Dreambotter420", version = 0.1)
public class Main extends AbstractScript implements Painter, PaintInfo, GameTickListener, MessageListener
{
    public static Timer timer;
    
    @Override
    public void onStart(String[] args)
    {
    	MethodProvider.log("OnStart Script!");
    	
    	RandomManager.setSoundDisableEnabled(false);
    	RandomManager.setToggleAcceptAid(false);
    	if(RandomManager.isSoundDisableEnabled() || 
    			RandomManager.isToggleAcceptAid())
    	{
    		MethodProvider.log("Sound disabler or AIDs disabler enabled! BAD!!!");
    	} 
    	else
    	{
    		MethodProvider.log("Sound disabler && AIDs disabler disabled! GOOD!!!");
    	}
    	CUSTOM_PAINT.setInfoSupplier(() ->
        new String[] {
                AbstractScript.getScriptName() + " V" + AbstractScript.getScriptVersion(),
                "Current Branch: " + API.currentBranch,
                "Current Leaf: " + API.currentLeaf,
        });
    	API.accUsername = Client.getLoginUsername();
    	API.accPassword = Client.getLoginPassword();
    	API.muleName = ""; //lowercase pls
    	API.muleWorld = 542;
    	API.costume = new EquipmentLoadout()
			.addReq(EquipmentSlot.HAT, new EquipmentItem(21847))
			.addReq(EquipmentSlot.CHEST, new EquipmentItem(21849))
			.addReq(EquipmentSlot.CAPE, new EquipmentItem(21853))
			.addReq(EquipmentSlot.FEET, new EquipmentItem(21857))
			.addReq(EquipmentSlot.HANDS, new EquipmentItem(21855))
			.addReq(EquipmentSlot.LEGS, new EquipmentItem(21851))
			.addReq(EquipmentSlot.WEAPON, new EquipmentItem(25314)); // boulder
    	Sleep.dt = LocalDateTime.now();
        API.runTimer = new Timer();
        API.started = true;
        instantiateTree();
    }

    private final Tree tree = new Tree();
    
    private void instantiateTree() {
    	Listening listen = new Listening();
    	Listeners.register(listen);
        tree.addBranches(new Running().addLeafs(
        		new Login(),
        		
        		new Initialize(),

        		new OnTutorialIsland().addLeafs(
            			new TypeName(),
        				new CharacterPainter(),
            			new HandleDialogues(),
            			new ClickWizard(),
            			new CastOnAChicken(),
            			new ClickBrotherBrace(),
            			new ClickAnvil(),
            			new ClickAccountTab(),
            			new ClickBankBooth(),
            			new ClickCaveEntrance(),
            			new ClickChef(),
            			new ClickMagicTab(),
            			new ClickCombatInstructor(),
            			new ClickCombatTab(),
            			new ClickDoor1_GuideExit(),
            			new ClickDoor2_ChefEnter(),
            			new ClickDoor3_ChefExit(),
            			new ClickDoor4_AccountGuideEnter(),
            			new ClickDoor5_AccountGuideExit(),
            			new ClickEquipmentTab(),
            			new ClickFishingSpot(),
            			new ClickForge(),
            			new ClickGate1(),
            			new ClickPrayerTab(),
            			new ClickFriendsTab(),
            			new ClickGate2_Underground(),
            			new ClickGate3_RatCage(),
            			new ClickInventoryTab(),
            			new ClickMiner(),
            			new ClickQuestTab(),
            			new ClickRat(),
            			new ClickRock_Copper(),
            			new ClickRock_Tin(),
            			new ClickSettingsTab(),
            			new ClickSkillsTab(),
            			new ClickPollBooth(),
            			new ClickTree(),
            			new ClickLadder_Underground(),
            			new ClickQuestDoor(),
            			new ClickWornEquipment(),
            			new CookABread(),
            			new CookAShrimp(),
            			new EquipDagger(),
            			new EquipSwordShield(),
            			new KillRatRanged(),
            			new MakeABreadDough(),
            			new MakeAFire(),
            			new SmithDagger(),
            			new ChangeToFixedMode(),
            			new ClickHintArrow()),
        		
        		new OffTutorialIsland().addLeafs(
        				new DialogueHandler(),
                    	new ChristmasVarbitHandler(),
                		new FinishedChristmasEvent().addLeafs(
                        		new FirstBankLeaf(),
                        		new QuitAfterCashout(),
                            	new WalkToGE().addLeafs(new AtGE(), new NotAtGE()),
                                new WalkToWildyTile().addLeafs(
                                		new WalkToWildyTileLeaf(),
                                		listen,
                                		new HasPersistentCommand().addLeafs(
                                        		new SpamBackspace(), 
                                        		new AnnounceBobsled(), 
                                        		new JamaicanBobsled(), 
                                        		new HopToCalledWorld(), 
                                        		new GoToLumbridge(), 
                                        		new Squaredance(), 
                                        		new Circle(), 
                                        		new FourTwenty(),
                                        		new Square(), 
                                        		new Spread(),
                                        		new Line(), 
                                        		new LineUp(), 
                                        		new Follow(), 
                                        		new SpamTrade(), 
                                        		new DD(), 
                                        		new Attack(), 
                                        		new Dance()))))));
    	
    }

    @Override
    public int onLoop()
    {
        if (!API.started)
        {
            return 50;
        }

        if (timer == null)
        {
            timer = new Timer();
        }

        return tree.onLoop();
    }

    @Override
    public void onPause()
    {
        timer.resume();
    }

    @Override
    public void onResume()
    {
        timer.pause();
    }

    @Override
   	public void notify(GameTickEvent arg0) {
   		if (Client.getClient().getKeyboardIdleTicks() > 14900)
   		{
   			Client.getClient().setKeyboardIdleTicks(0);
   		}
   		if (Client.getClient().getMouseIdleTicks() > 14900)
   		{
   			Client.getClient().setMouseIdleTicks(0);
   		}
   	}
    
    // Our paint info
    // Add new lines to the paint here
    @Override
    public String[] getPaintInfo()
    {
    	return new String[] {
                AbstractScript.getScriptName() + " V" + AbstractScript.getScriptVersion(),
                "Current Branch: " + API.currentBranch,
                "Current Leaf: " + API.currentLeaf
        };
    }

    // Instantiate the paint object. This can be customized to your liking.
    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.BOTTOM_LEFT_PLAY_SCREEN,
            new Color[] {new Color(255, 251, 255)},
            "Trebuchet MS",
            new Color[] {new Color(50, 50, 50, 175)},
            new Color[] {new Color(28, 28, 29)},
            1, false, 5, 3, 0);
    private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


    @Override
    public void onPaint(Graphics2D graphics2D)
    {
        // Set the rendering hints
        graphics2D.setRenderingHints(aa);
        // Draw the custom paint
        CUSTOM_PAINT.paint(graphics2D);
    }
    
	@Override
	public void notify(ChatMessage arg0) {
		String mesg = StringUtility.clean(arg0.getMessage());
		if(mesg.contains("You dig up some snow from the snowpile"))
		{
			API.snowDigTimer = new Timer();
		}
	}

}

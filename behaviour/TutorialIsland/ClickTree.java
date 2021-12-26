package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.GameObjects;
import org.lostclient.api.accessors.Players;
import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.EntityInteractEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.api.wrappers.tabs.Tab;
import org.lostclient.api.wrappers.tabs.Tabs;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Locations;
import org.lostclient.utilities.Sleep;

public class ClickTree extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(263,1,0) != null && Widgets.getWidgetChild(263,1,0).isVisible() &&
        		Widgets.getWidgetChild(263,1,0).getText().contains("s time to cook your shrimp. However, you require a fire to do that which means you need some logs. You can cut down trees using your Woodcutting skill, all you need is an axe. Give it a go by clicking on one of the trees in the area") &&
        		!Players.localPlayer().isMoving() && !Players.localPlayer().isAnimating();
    }
    @Override
    public int onLoop() {
    	API.randomAFK();
    	MethodProvider.log("ClickTree is valid");
    	ClickATree();
    	return 5;
    }
    public static void ClickATree() {
    	if(Players.localPlayer().isMoving()|| Players.localPlayer().isAnimating()) return;
    	new EntityInteractEvent(GameObjects.closest(tree -> tree.getName().equals("Tree") && Locations.ACCEPTABLE_TREES.contains(tree)),"Chop down").execute();
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(10, 444);
    	if(API.rand2.nextInt(100) < 1 && !Tabs.isOpen(Tab.INVENTORY)) Tabs.open(Tab.INVENTORY);
    	Sleep.sleep(1300, 888);
    }
}

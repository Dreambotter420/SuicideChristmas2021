package org.lostclient.behaviour.TutorialIsland;

import org.lostclient.api.accessors.Widgets;
import org.lostclient.api.events.WidgetEvent;
import org.lostclient.api.utilities.MethodProvider;
import org.lostclient.framework.Leaf;
import org.lostclient.utilities.API;
import org.lostclient.utilities.Creator;
import org.lostclient.utilities.Sleep;


public class CharacterPainter extends Leaf {

    @Override
    public boolean isValid() {
        return Widgets.getWidgetChild(679,3,1) != null
        		&& Widgets.getWidgetChild(679,3,1).isVisible();
    }
    @Override
    public int onLoop() {
    	/**character creator menu
    	 * following method creates the exact appearance I want my characters
    	 */
    	Creator.randomizeCreationOrder();
    	API.randomAFK();
    	if(Creator.headRights < 7 ||
    			Creator.jawRights < 1 ||
    			Creator.armsRights < 3 ||
    			Creator.legsRights < 5 ||
    			Creator.handsRights < 1 ||
    			Creator.headColourRights < Creator.headColourMaxRights ||
    			Creator.torsoColourRights < Creator.torsoColourMaxRights ||
    			Creator.legsColourRights < Creator.legsColourMaxRights ||
    			Creator.feetColourRights < Creator.feetColourMaxRights ||
    			Creator.skinColourLefts < 2) 
    	{
    		if(Creator.attributeList.get(0).equals(Creator.attributes.HEAD)) {
        		if(Creator.headRights >= 7) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,13), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.headRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.JAW)) {
        		if(Creator.jawRights >= 1) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,17), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.jawRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.ARMS)) {
        		if(Creator.armsRights >= 3) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,25), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.armsRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.LEGS)) {
        		if(Creator.legsRights >= 5) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,33), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.legsRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.HANDS)) {
        		if(Creator.handsRights >= 1) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,28), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.handsRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.HAIRCOLOUR)) {
        		if(Creator.headColourRights >= Creator.headColourMaxRights) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,44), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.headColourRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.TORSOCOLOUR)) {
        		if(Creator.torsoColourRights >= Creator.torsoColourMaxRights) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,48), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.torsoColourRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.LEGSCOLOUR)) {
        		if(Creator.legsColourRights >= Creator.legsColourMaxRights) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,52), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.legsColourRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.FEETCOLOUR)) {
        		if(Creator.feetColourRights >= Creator.feetColourMaxRights) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,56), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.feetColourRights++;
        			}
        		}
        	} else if(Creator.attributeList.get(0).equals(Creator.attributes.SKINCOLOUR)) {
        		if(Creator.skinColourLefts >= 2) Creator.attributeList.remove(0);
        		else {
        			if(new WidgetEvent(Widgets.getWidgetChild(679,59), "Select")
        					.setEventTimeout(3000)
        					.executed()) {
        				Creator.skinColourLefts++;
        			}
        		}
        	}
    	} else {
    		Sleep.sleep(666, 1200);
    		MethodProvider.log("ALL DONE PAINTING CHARACTER!");
    		if(Widgets.getWidgetChild(679,68).interact()) {
    			Sleep.sleep(666, 1200);
    		}
    	}
    	MethodProvider.tickSleep(1);
    	Sleep.sleep(33, 555);
    	return 5;
    }
}

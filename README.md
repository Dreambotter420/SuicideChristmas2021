This is an almost-all-in-one script that is made for LostClientEnhanced to complete Tutorial Island and the Christmas 2021 event for l00t. 


# How it works

Script will first check if your account is on tutorial island, if so then completes remaining Tutorial Island steps. Works on a completely fresh account by naming then coloring your character. The naming function requires a file "usernames.txt" to exist in your file directory <%USERNAME%\LostClientEnhanced\Scripts\usernames.txt>, with one RSN per line. Example usernames.txt file is included in source folder. Script will read the first line then delete it, and repeats if username is taken.

Script then checks varbit values for christmas 2021 event, and completes it if not done already. 

Once christmas event is done, script checks bank and GE to withdraw any coins on the account (also has some selling functions for certain things like potatoes, cabbages in case the account was used for other F2P suicide farming) as well as withdraws the tradeable christmas rewards in noted form. Then travels character to a specified spot in wilderness, hops to a specified world, then waits to be commanded to skull on other accounts and drop the noted rewards. 

After christmas event is complete and the script detects no more coins or christmas rewards, closes the client.

# Configuration / Commands

To configure the area to walk to in wildy and the world to hop to after reaching it, edit the variables <int worldToHopAfterWildyTile> and <Tile wildyTile> in org.lostclient.cashout.WalkToWildyTileLeaf.java.

The bots will start responding to commands in public chat after they reach the wildy spot + world. The commands are as follows:
 
  "jk rowling wrote harry potter": Assigns whoever said this as the exclusive caller, stop listening to other people's commands
  
  "listen to everyone now": Disengages the previous mode
  
  "attack"/"kill"/"murder"/"slash"/"fight": Gets a random valid & attackable target nearby and attacks them; checks for single combat zone + occupied target and player combat level is compatible in wildy; continues to search for new targets after current one dies
  
  "attack <partial name>": Searches for nearest attackable target containing name and attacks them
  
  "hop to w301": Hop to w301 or any other valid F2P world
  
  "dance dance": Constantly picks a random dancing emote to emote
 
  "<Emote name>": Continuously emotes the specified emote (all default unlocked emotes supported)
 
  "follow": Follows the person who said this
  
  "follow <partial name>": Searches for the nearest player containing this name and follows them
  
  "trade" / "Spam": Spamtrades the person who said this
  
  "trade/Spam <partial name>": Searches for the nearest player containing this name and spamtrades them
  
  "spread": Does a formation, I think it's a circle actually
  
  "circle": Walks to a random tile around whoever said this, generally making a circle with enough bots.
  
  "square": Same as above
  
  "line up": Same as above - vertical line N-S
  
  "line": Same as above - horizontal line E-W
  
  "swarm"/"squaredance": Constantly paths to a random tile in a 5x5 square around whoever said this
  
  "go home": Goes to lumbridge
  
  "stop":Forgets and disengages all current commands (but continues to listen to exclusive caller if any)


  # Final note
  
  The script will listen to commands in public chat while doing the christmas event and store them, but won't actually execute any of them until they reach the wildy spot+tile. You may find your bots dancing or saying random things to you at this point.

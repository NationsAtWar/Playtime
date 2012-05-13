package org.nationsatwar.playtime;

import java.util.*;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Playtime extends JavaPlugin
{
	Logger log;
	HashMap<String, PlaytimeEvent> map;
	PlaytimeEvent temp;
	
	public void onEnable()
	{
		// read external file for current event stuff
		log = this.getLogger();
		map = new HashMap<String,PlaytimeEvent>();
		log.info("Playtime has been enabled!");
	}
	
	public void onDisable()
	{
		log.info("Playtime has been disabled!");
	}
	
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	Player player = null;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    	}
    	
    	if(cmd.getName().equalsIgnoreCase("event"))
    	{ 
    		if(args[0].equalsIgnoreCase("create"))
    		{
				if(args[1] != null)
				{
	    			if(player != null)
	    			{
		    			if(player.hasPermission("playtime.admins"))
					    {
	    					if(map.get(args[1]) != null) // makes sure event with name does not already exist
	    					{
		    					if(args[2] == null)
		    					{
		    						temp = new PlaytimeEvent(args[1]);
		    						map.put(args[1],temp);
		    						// also put event in file
		    						// message to player
		    					}
	    					}
	    					else
	    					{
	    						// error; event already exists.
	    					}
					    }
	    			}
	    			else // user is server
	    			{
						if(map.get(args[1]) != null) // makes sure event with name does not already exist
						{
	    					if(args[2] == null)
	    					{
	    						temp = new PlaytimeEvent(args[1]);
	    						map.put(args[1],temp);
	    						// also put event in file
	    						log.info("Event '"+args[0]+"' successfully created.");
	    					}
						}
						else
						{
							// error; event already exists.
							log.info("Error: event '"+args[1]+"' already exists.");
						}
	    			}
				}
				else
				{
					// instructions on use of command
				}
    		}
    		else if(args[0].equalsIgnoreCase("end"))
    		{
				if(args[1] != null) // event name
				{
	    			if(player != null)
	    			{
		    			if(player.hasPermission("playtime.admins"))
					    {
	    					if(map.get(args[1]) != null) // if event with name is found
	    					{
	    						map.remove(args[1]);
		    					// remove event from file
		    					// remove subscriptions, too
	    						// message to player using command
	    						// general message to players (subscribed to event?) that event has ended
	    					}
	    					else
	    					{
	    						// error; event does not exist
	    					}
					    }
	    			}
	    			else // user is server
	    			{
	    				if(args[1] != null)
	    				{
	    					if(map.get(args[1]) != null)
	    					{
	    						map.remove(args[1]);
	        					// remove event from file
	        					// remove subscriptions, too
	    						// general message to players (subscribed to event?) that event has ended
	    						log.info("Event '"+args[0]+"' successfully ended.");
	    					}
	    					else
	    					{
	    						log.info("Error: event '"+args[0]+"' does not exist.");
	    					}
	    				}
	    			}
				}
				else
				{
					// instructions on use of command
				}
    		}
    		else if(args[0].equalsIgnoreCase("setspawn"))
    		{
				if(args[1] != null) // event name
				{
	    			if(player != null) // user is player
	    			{
		    			if(player.hasPermission("playtime.admins"))
					    {
	    					if(map.get(args[1]) != null) // if event with name is found
	    					{
	    	    				if(args[2] != null) // player name or nothing
	    	    				{
	    	    					// player name was provided
	    	    					Player p = getServer().getPlayer(args[2]); // get player from server
	    	    					// set player as event spawn
	    	    					temp = map.get(args[1]);
	    	    					temp.setSpawn(p);
	    	    					map.put(args[1],temp);
	    	    				}
	    	    				else
	    	    				{
	    	    					// no player provided, so take user's current location as spawn
	    	    					temp = map.get(args[1]);
	    	    					temp.setSpawn(player.getLocation());
	    	    					map.put(args[1],temp);
	    	    				}
	    					}
	    					else
	    					{
	    						//log.info("Error: event '"+args[1]+"' does not exist.");
	    					}
					    }
	    			}
	    			else // user is server
	    			{
    					if(map.get(args[1]) != null) // if event with name is found
    					{
    	    				if(args[2] != null) // player name or nothing
    	    				{
    	    					// player name was provided
    	    					Player p = getServer().getPlayer(args[2]); // get player from server
    	    					// set player as event spawn
    	    					temp = map.get(args[1]);
    	    					temp.setSpawn(p);
    	    					map.put(args[1],temp);
    	    				}
    	    				else
    	    				{
    	    					// no player provided, so this can't be used
    	    					log.info("Error: must specify player");
    	    				}
    					}
    					else
    					{
    						log.info("Error: event '"+args[1]+"' does not exist.");
    					}
	    			}
				}
				else
				{
					// help text
				}
    		}
    		else if(args[0].equalsIgnoreCase("subscribe"))
    		{
				if(args[1] != null) // if event name has been provided
				{
	    			if(player != null)
	    			{
		    			if(player.hasPermission("playtime.admins")) // check if player has permission to subscribe other players
					    {
	    					if(args[2] != null) // subscribing named player to event
	    					{
	    						// get player from name
	    						Player p = getServer().getPlayer(args[2]); // get player from server
	    						boolean s = false;
	    						for (PlaytimeEvent t : map.values()) // check whether player is already subscribed to an event
	    						{
	    							if(t.isSubscribed(p.getName()))
	    								s = true;
	    						}

	    						if(!s) // subscribe player to event
	    						{
	    							temp.subscribe(p); 
	    							map.put(args[1],temp);
	    							player.sendMessage(p.getName() + " subscribed to event " + args[1] + ".");
	    							p.sendMessage("You have been subscribed to event " + args[1] + " by " + player.getName() + ".");
		    						// do not notify player if event is hidden?
	    							// store in file
	    						}
	    						else
	    						{
	    							player.sendMessage(p.getName() + " is already subscribed to an event.");
	    							// feedback on which event?
	    						}
	    						
	    					}
	    					else // subscribing self to event
	    					{
	    						temp = map.get(args[1]);
	    						boolean s = false;
	    						for (PlaytimeEvent t : map.values()) // check whether player is already subscribed to an event
	    						{
	    							if(t.isSubscribed(player.getName()))
	    								s = true;
	    						}

	    						if(!s) // if player is not subscribed
	    						{
	    							temp.subscribe(player);
	    							map.put(args[1],temp);
	    							player.sendMessage("You have subscribed to event " + args[1] + ".");
	    							// store in file
	    						}
	    						else
	    						{
	    							player.sendMessage("You are already subscribed to an event.");
	    						}
	    					}
					    }
		    			else // player does not have permission; can only subscribe self to event
		    			{
	    					if(map.get(args[1]) != null) // check event exists
	    					{
	    						temp = map.get(args[1]);
	    						boolean s = false;
	    						for (PlaytimeEvent t : map.values()) // check whether player is already subscribed to an event
	    						{
	    							if(t.isSubscribed(player.getName()))
	    								s = true;
	    						}

	    						if(!s) // if player is not subscribed
	    						{
	    							temp.subscribe(player);
	    							map.put(args[1],temp);
	    							player.sendMessage("You have subscribed to event " + args[1] + ".");
	    							// store in file
	    						}
	    						else
	    						{
	    							player.sendMessage("You are already subscribed to an event.");
	    						}
	    					}
		    			}
	    			}
	    			else
	    			{
	    				
	    			}
				}
				else
				{
					// help text
				}
    		}
    		else if(args[0].equalsIgnoreCase("unsubscribe"))
    		{
    			if(args[1] != null)
    			{
	    			if(player != null)
	    			{
		    			if(player.hasPermission("playtime.admins"))
					    {
		    				
					    }
		    			else
		    			{
		    				
		    			}
	    			}
    			}
    			else
    			{
    				// help text
    			}
    		}
    		else if(args[0].equalsIgnoreCase("list"))
    		{
    			player.sendMessage("Events in progress:"); // show something else if none are in progress
    			for (PlaytimeEvent value : map.values()) 
    			{
    				player.sendMessage(value.getInfo());
    			}
    		}
    	}
    return false; // remember to add return trues where necessary
    }
}

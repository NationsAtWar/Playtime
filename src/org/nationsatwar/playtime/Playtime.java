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
    			if(player != null)
    			{
	    			if(player.hasPermission("playtime.admins"))
				    {
	    				if(args[1] != null)
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
	    				else
	    				{
	    					// instructions on use of command
	    				}
				    }
    			}
    			else // user is server
    			{
    				if(args[1] != null)
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
    				else
    				{
    					// instructions on use of command
    				}
    			}
    		}
    		else if(args[0].equalsIgnoreCase("end"))
    		{
    			if(player != null)
    			{
	    			if(player.hasPermission("playtime.admins"))
				    {
	    				if(args[1] != null) // event name
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
	    				else
	    				{
	    					// instructions on use of command
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
    				else
    				{
    					// instructions on use of command
    				}
    			}
    		}
    		else if(args[0].equalsIgnoreCase("setspawn"))
    		{
    			if(player != null) // user is player
    			{
	    			if(player.hasPermission("playtime.admins"))
				    {
	    				if(args[1] != null) // event name
	    				{
	    					if(map.get(args[1]) != null) // if event with name is found
	    					{
	    	    				if(args[2] != null) // player name or nothing
	    	    				{
	    	    					// player name was provided
	    	    					// check player is online?
	    	    					// set player as event spawn
	    	    				}
	    	    				else
	    	    				{
	    	    					// no player provided, so take user's current location as spawn
	    	    					PlaytimeEvent temp = map.get(args[1]);
	    	    					temp.setSpawn(player.getLocation());
	    	    					map.put(args[1],temp);
	    	    				}
	    					}
	    					else
	    					{
	    						log.info("Error: event '"+args[1]+"' does not exist.");
	    					}
	    				}
						else
						{
							// help text
						}
				    }
    			}
    			else // user is server
    			{
    				
    			}
    		}
    		else if(args[0].equalsIgnoreCase("forcesubscribe"))
    		{
    			if(player.hasPermission("playtime.admins"))
			    {
    				
			    }
    		}
    		else if(args[0].equalsIgnoreCase("forceunsubscribe"))
    		{
    			if(player.hasPermission("playtime.admins"))
			    {
    				
			    }
    		}
    		else if(args[0].equalsIgnoreCase("list"))
    		{
    			
    		}
    		else if(args[0].equalsIgnoreCase("subscribe"))
    		{
    			
    		}
    		else if(args[0].equalsIgnoreCase("unsubscribe"))
    		{
    			
    		}
    		else
    		{
    			// event help text
    		}
    		return true;
    	} 
    	return false; 
    }


}

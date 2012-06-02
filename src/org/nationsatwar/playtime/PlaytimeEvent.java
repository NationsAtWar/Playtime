package org.nationsatwar.playtime;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlaytimeEvent 
{
	String name;
	Location location;
	String player;
	HashMap<String, Location> subscribed;
	
	boolean hidden;
	//boolean teleportOnJoin;
	//int secondsToTeleport;
	
	public PlaytimeEvent(String n)
	{
		name = n;
		hidden = false;
		subscribed = new HashMap<String, Location>();
	}
	
	public PlaytimeEvent(String n, boolean h)
	{
		name = n;
		hidden = h;
		subscribed = new HashMap<String, Location>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isHidden()
	{
		return hidden;
	}
	
	public void setSpawn(Location l)
	{
		location = l;
		player = null;
	}
	
	public void setSpawn(Player p)
	{
		player = p.getName();
		location = null;
	}
	
	public void setSpawn(String p)
	{
		player = p;
		location = null;
	}
	
	public void subscribe(Player p) // return true for success, false for failure? This could be used by the server or a player, ultimately
	{
		if(subscribed.get(p.getName()) == null)
		{
			subscribed.put(p.getName(),p.getLocation());
			// teleport player to spawn
		}
		else
		{
			// error: player already subscribed to event
		}
	}
	
	public void subscribe(String n, Location l)
	{
		if(subscribed.get(n) == null)
		{
			subscribed.put(n,l);
		}
		else
		{
			// error: player already subscribed to event
		}
	}
	
	public void unsubscribe(Player p) // return boolean
	{
		if(subscribed.get(p.getName()) != null)
		{
			subscribed.remove(p.getName());
		}
		else
		{
			// error: player is not subscribed to event
		}
	}
	
	public boolean isSubscribed(String n)
	{
		if(subscribed.get(n) != null)
			return true;
		return false;
	}
	
	public String getInfo()
	{
		String s = getName() + ": ";
		int n = 0;
		for (@SuppressWarnings("unused") String key : subscribed.keySet()) 
		    n++;
		s += n + " subscribed players";
		if(isHidden())
			s += " (hidden)";
		return s;
	}
	
	public void teleportToSpawn(Player p)
	{
		if(location != null)
			p.teleport(location);
		else if(player != null)
		{
			Player spawn = (Bukkit.getServer().getPlayer(player));
			if(spawn != null)
			{
				p.teleport(spawn.getLocation());
			}
			else
			{
				// there was a problem; spawn location player was offline.
			}
		}
	}
	
	// use this to teleport players?
}

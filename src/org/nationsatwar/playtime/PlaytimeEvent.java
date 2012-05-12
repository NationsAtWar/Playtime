package org.nationsatwar.playtime;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlaytimeEvent 
{
	String name;
	boolean hidden;
	Location location;
	Player player;
	HashMap<String, Player> subscribed;
	
	public PlaytimeEvent(String n)
	{
		name = n;
		hidden = false;
	}
	
	public PlaytimeEvent(String n, boolean h)
	{
		name = n;
		hidden = h;
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
		player = p;
		location = null;
	}
	
	public void subscribe(Player p)
	{
		if(subscribed.get(p.getName()) == null)
		{
			subscribed.put(p.getName(),p);
			// teleport player to spawn
		}
		else
		{
			// error: player already subscribed to event
		}
	}
	
	public void unsubscribe(Player p)
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
	
	// use this to teleport players?
}

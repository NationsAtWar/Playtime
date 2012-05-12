package org.nationsatwar.playtime;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlaytimeEvent 
{
	String name;
	boolean hidden;
	Location location;
	Player player;
	
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
	
	// use this to teleport players?
}

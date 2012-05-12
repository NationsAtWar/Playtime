package org.nationsatwar.playtime;

public class PlaytimeEvent 
{
	String name;
	boolean hidden;
	
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
}

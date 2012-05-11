package org.nationsatwar.playtime;

import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Playtime extends JavaPlugin
{
	Logger log;
	
	public void onEnable()
	{
		// read external file for current event stuff
		log = this.getLogger();
		log.info("Your plugin has been enabled!");
	}
	
	public void onDisable()
	{
		log.info("Your plugin has been disabled!");
	}
	
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    	if(cmd.getName().equalsIgnoreCase("basic"))
    	{ 
    		
    		return true;
    	} 
    	return false; 
    }


}

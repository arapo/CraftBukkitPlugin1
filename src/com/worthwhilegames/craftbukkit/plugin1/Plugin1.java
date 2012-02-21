package com.worthwhilegames.craftbukkit.plugin1;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import com.worthwhilegames.craftbukkit.plugin1.config.Plugin1Config;
import com.worthwhilegames.craftbukkit.plugin1.config.container.Plan;

public class Plugin1 extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	private Plugin1Config plugin1Config;
	
    public void onEnable(){
    	
    	plugin1Config = new Plugin1Config(this);
    	
    	log.info("Plugin1 has been enabled and configured!");
    }
     
    public void onDisable(){ 
    	log.info("Plugin1 has been disabled.");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
    	if(cmd.getName().equalsIgnoreCase("bling")){ // If the player typed /bling then do the following...
    		
            Player player = (Player)sender;

	        PlayerInventory inventory = player.getInventory(); // The player's inventory
	        ItemStack diamondstack = new ItemStack(Material.DIAMOND, 64); // A stack of diamonds
	     
	        //if (inventory.contains(diamondstack)) {
	            inventory.addItem(diamondstack); // Adds a stack of diamonds to the player's inventory
	            player.sendMessage(ChatColor.GOLD + "Bling!!!");
	        //}
	            return true;
    	}
    	else if(cmd.getName().equalsIgnoreCase("solid")){ // If the player typed /solid then do the following...
    		
            Player player = (Player)sender;

	        PlayerInventory inventory = player.getInventory(); // The player's inventory
	        ItemStack diamondstack = new ItemStack(Material.OBSIDIAN, 64); // A stack of diamonds
	     
	        //if (inventory.contains(diamondstack)) {
	            inventory.addItem(diamondstack); // Adds a stack of diamonds to the player's inventory
	            player.sendMessage(ChatColor.GOLD + "Solid!!!");
	        //}
	            return true;
    	}
    	return false;
    }
}

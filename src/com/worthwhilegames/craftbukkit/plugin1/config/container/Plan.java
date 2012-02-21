package com.worthwhilegames.craftbukkit.plugin1.config.container;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * Plan Class that will hold the information about a plan.
 * Each plan object will map to a plan in the configuration.
 * 
 * @author Andrew
 *
 */
public class Plan {

	// plan name
	private String planName;
	// plan short name (for use in multiple plans)
	private String planShortName;
	// plan chat colour
	private ChatColor planColour;

	/**
	 * Constructor
	 * @param param - description
	 */
	public Plan(
			String planName,
			String planShortName,
			ChatColor planColour
			){

		this.planName = planName;
		this.planShortName = planShortName;
		this.planColour = planColour;
	}

	/**
	 * Get the plan name
	 * @return the plan name
	 */
	public String getName(){
		return planName;
	}
	
	/**
	 * Get the shortened version of the planName
	 * @return the shortened version of the planName
	 */
	public String getShortName(){
		return planShortName;
	}
	
	/**
	 * Get the Color of the plan for chat
	 * @return the Color of the plan for chat
	 */
	public ChatColor getChatColour(){
		return planColour;
	}

}

package com.worthwhilegames.craftbukkit.plugin1.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.material.MaterialData;
//import org.mbertoli.jfep.Parser;

import com.worthwhilegames.craftbukkit.plugin1.Plugin1;
import com.worthwhilegames.craftbukkit.plugin1.config.container.Plan;

//import com.zford.jobs.Jobs;
//import com.zford.jobs.config.container.Job;
//import com.zford.jobs.config.container.JobsMaterialInfo;
//import com.zford.jobs.config.container.JobsLivingEntityInfo;
//import com.zford.jobs.util.DisplayMethod;

/**
 * Configuration class.
 * 
 * Holds all the configuration information for Plugin1
 * @author Andrew
 *
 */
public class Plugin1Config {
	
	Logger log = Logger.getLogger("Minecraft");
	
	// all of the possible plans
	private HashMap<String, Plan> plans;
	
	private Plugin1 plugin;
	public Plugin1Config(Plugin1 plugin) {
	    this.plugin = plugin;
	}
	
	public void reload() {
        // plan settings
        loadPlanSettings();
	}
	
	/**
	 * Method to load the plans configuration
	 * 
	 * loads from Plans/planConfig.yml
	 */
	private void loadPlanSettings(){
	    File f = new File("plugins/Plans/planConfig.yml");
        YamlConfiguration conf;
        this.plans = new HashMap<String, Plan>();
        if(!f.exists()) {
            // disable plugin
            System.err.println("[Plans] - configuration file planConfig.yml does not exist.  Disabling jobs !");
            //plugin.disablePlugin();
            return;
        }
        conf = new YamlConfiguration();
        try {
            conf.load(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        ConfigurationSection planSection = conf.getConfigurationSection("Plans");
        if(planSection == null) {
            // no plans
            System.err.println("[Plans] - No plans detected. Disabling Plans!");
            //plugin.disablePlugin();
            return;
        }
        

        for(String planKey : planSection.getKeys(false)) {
            String planName = conf.getString("Plans."+planKey+".fullname");
            if(planName == null) {
                System.err.println("[Plans] - Plan " + planKey + " has an invalid fullname property. Disabling plans !");
                return;
            }

            String planShortName = conf.getString("Plans."+planKey+".shortname");
            if(planShortName == null) {
                System.err.println("[Plans] - Plan " + planKey + " is missing the shortname property. Disabling plans !");
                return;
            }

            ChatColor planColour = ChatColor.valueOf(conf.getString("Plans."+planKey+".ChatColour", "").toUpperCase());
            if(planColour == null) {
                System.err.println("[Plans] - Plan " + planKey + " is missing the ChatColour property. Disabling plans !");
                return;
            }
            
            log.info("Plugin1:Plan added: " + planName);
            
/*          
            // place
            ConfigurationSection placeSection = conf.getConfigurationSection("Jobs."+jobKey+".Place");
            HashMap<String, JobsMaterialInfo> jobPlaceInfo = new HashMap<String, JobsMaterialInfo>();
            if(placeSection != null) {
                for(String placeKey : placeSection.getKeys(false)) {
                    String materialType = placeKey.toUpperCase();
                    String subType = "";
                    Material material;
                    if(materialType.contains("-")) {
                        // uses subType
                        subType = ":"+materialType.split("-")[1];
                        materialType = materialType.split("-")[0];
                    }
                    try {
                        material = Material.matchMaterial(materialType);
                    }
                    catch(IllegalArgumentException e) {
                        material = null;
                    }
                    if(material == null) {
                        System.err.println("[Jobs] - Job " + jobKey + " has an invalid " + placeKey + " Place material type property. Skipping!");
                        continue;
                    }
                    MaterialData materialData = new MaterialData(material);
                    
                    Double income = conf.getDouble("Jobs."+jobKey+".Place."+placeKey+".income", 0.0);
                    Double experience = conf.getDouble("Jobs."+jobKey+".Place."+placeKey+".experience", 0.0);
                    
                    jobPlaceInfo.put(material.toString()+subType, new JobsMaterialInfo(materialData, experience, income));
                }
            }
            
*/            
            this.plans.put(planName.toLowerCase(), new Plan(planName, planShortName, planColour));

        }

	}
	
	/**
	 * Function to return the plan information that matches the planName given
	 * @param planName - the name of the plan given
	 * @return the plan that matches the name
	 */
	public Plan getPlan(String planName){
		return plans.get(planName.toLowerCase());
	}
	
	/**
	 * Get all the plans loaded in the plugin
	 * @return a collection of the plans
	 */
	public Collection<Plan> getPlans(){
		return plans.values();
	}

}

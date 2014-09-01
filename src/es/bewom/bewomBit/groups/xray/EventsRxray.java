package es.bewom.bewomBit.groups.xray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventsRxray {

	public static void OnBreak(BlockBreakEvent eventBroke) throws FileNotFoundException, IOException, InvalidConfigurationException{

		Player craftPlayer = eventBroke.getPlayer();
		String playerName = craftPlayer.getName();

		File rayData = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
		File f = new File(rayData, File.separator + "rxray.yml");
		FileConfiguration xrayData = YamlConfiguration.loadConfiguration(f);

		if(eventBroke.getBlock().getType().equals(Material.STONE)){			
			actualizarOres (xrayData, f, playerName, ".Stone", true);			
		}		

		if(eventBroke.getBlock().getType().equals(Material.COAL_ORE)){
			actualizarOres (xrayData, f, playerName, ".Coal_Ore", true);		
		}	

		if(eventBroke.getBlock().getType().equals(Material.IRON_ORE)){
			actualizarOres (xrayData, f, playerName, ".Iron_Ore", true);		
		}

		if(eventBroke.getBlock().getType().equals(Material.GOLD_ORE)){
			actualizarOres (xrayData, f, playerName, ".Gold_Ore", true);		
		}

		if(eventBroke.getBlock().getType().equals(Material.REDSTONE_ORE)){
			actualizarOres (xrayData, f, playerName, ".Redstone_Ore", true);		
		}

		if(eventBroke.getBlock().getType().equals(Material.LAPIS_ORE)){
			actualizarOres (xrayData, f, playerName, ".Lapis_Ore", true);		
		}

		if(eventBroke.getBlock().getType().equals(Material.DIAMOND_ORE)){
			actualizarOres (xrayData, f, playerName, "Diamond_Ore", true);		
		}	

		if(eventBroke.getBlock().getType().equals(Material.EMERALD_ORE)){
			actualizarOres (xrayData, f, playerName, ".Emerald_Ore", true);		
		}
	}

	public static void OnPlace(BlockPlaceEvent eventPlace) throws FileNotFoundException, IOException, InvalidConfigurationException {

		Player craftPlayer = eventPlace.getPlayer();
		String playerName = craftPlayer.getName();

		File rayData = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
		File f = new File(rayData, File.separator + "rxray.yml");
		FileConfiguration xrayData = YamlConfiguration.loadConfiguration(f);

		if(eventPlace.getBlock().getType().equals(Material.COAL_ORE)){
			actualizarOres (xrayData, f, playerName, ".Coal_Ore", false);	
		}	

		if(eventPlace.getBlock().getType().equals(Material.IRON_ORE)){
			actualizarOres (xrayData, f, playerName, ".Iron_Ore", false);	
		}

		if(eventPlace.getBlock().getType().equals(Material.GOLD_ORE)){
			actualizarOres (xrayData, f, playerName, ".Gold_Ore", false);	
		}

		if(eventPlace.getBlock().getType().equals(Material.REDSTONE_ORE)){
			actualizarOres (xrayData, f, playerName, ".Redstone_Ore", false);	
		}

		if(eventPlace.getBlock().getType().equals(Material.LAPIS_ORE)){
			actualizarOres (xrayData, f, playerName, ".Lapis_Ore", false);	
		}

		if(eventPlace.getBlock().getType().equals(Material.DIAMOND_ORE)){
			actualizarOres (xrayData, f, playerName, ".Diamond_Ore", false);	
		}	

		if(eventPlace.getBlock().getType().equals(Material.EMERALD_ORE)){
			actualizarOres (xrayData, f, playerName, ".Emerald_Ore", false);	
		}
	}

	public static void actualizarOres (FileConfiguration xrayData, File f, String playerName, String ore, boolean suma) throws FileNotFoundException, IOException, InvalidConfigurationException{

		xrayData.load(f);

		int i = xrayData.getInt(playerName + ore);
		if (suma){
			xrayData.set(playerName + ore, i + 1);
		}
		else{
			xrayData.set(playerName + ore, i - 1);
		}
		xrayData.save(f);
	}
}
package es.bewom.bewomBit.events;

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
		
		File rayData = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File f = new File(rayData, File.separator + "rxray.yml");
		FileConfiguration xrayData = YamlConfiguration.loadConfiguration(f);
		
		if(eventBroke.getBlock().getType().equals(Material.STONE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Stone");
			xrayData.set(playerName + ".Stone", i + 1);
			
			xrayData.save(f);
			
		}		
		
		if(eventBroke.getBlock().getType().equals(Material.COAL_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Coal_Ore");
			xrayData.set(playerName + ".Coal_Ore", i + 1);
			
			xrayData.save(f);
			
		}	
		
		if(eventBroke.getBlock().getType().equals(Material.IRON_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Iron_Ore");
			xrayData.set(playerName + ".Iron_Ore", i + 1);
			
			xrayData.save(f);
			
		}
		
		if(eventBroke.getBlock().getType().equals(Material.GOLD_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Gold_Ore");
			xrayData.set(playerName + ".Gold_Ore", i + 1);
			
			xrayData.save(f);
			
		}
		
		if(eventBroke.getBlock().getType().equals(Material.REDSTONE_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Redstone_Ore");
			xrayData.set(playerName + ".Redstone_Ore", i + 1);
			
			xrayData.save(f);
			
		}
		
		if(eventBroke.getBlock().getType().equals(Material.LAPIS_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Lapis_Ore");
			xrayData.set(playerName + ".Lapis_Ore", i + 1);
			
			xrayData.save(f);
			
		}
		
		if(eventBroke.getBlock().getType().equals(Material.DIAMOND_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Diamond_Ore");
			xrayData.set(playerName + ".Diamond_Ore", i + 1);
			
			xrayData.save(f);
			
		}	
		
		if(eventBroke.getBlock().getType().equals(Material.EMERALD_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Emerald_Ore");
			xrayData.set(playerName + ".Emerald_Ore", i + 1);
			
			xrayData.save(f);
			
		}
		
	}
	
	public static void OnPlace(BlockPlaceEvent eventPlace) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		Player craftPlayer = eventPlace.getPlayer();
		String playerName = craftPlayer.getName();
		
		File rayData = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File f = new File(rayData, File.separator + "rxray.yml");
		FileConfiguration xrayData = YamlConfiguration.loadConfiguration(f);
		
		if(eventPlace.getBlock().getType().equals(Material.COAL_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Coal_Ore");
			xrayData.set(playerName + ".Coal_Ore", i - 1);
			
			xrayData.save(f);
			
		}	
		
		if(eventPlace.getBlock().getType().equals(Material.IRON_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Iron_Ore");
			xrayData.set(playerName + ".Iron_Ore", i - 1);
			
			xrayData.save(f);
			
		}
		
		if(eventPlace.getBlock().getType().equals(Material.GOLD_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Gold_Ore");
			xrayData.set(playerName + ".Gold_Ore", i - 1);
			
			xrayData.save(f);
			
		}
		
		if(eventPlace.getBlock().getType().equals(Material.REDSTONE_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Redstone_Ore");
			xrayData.set(playerName + ".Redstone_Ore", i - 1);
			
			xrayData.save(f);
			
		}
		
		if(eventPlace.getBlock().getType().equals(Material.LAPIS_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Lapis_Ore");
			xrayData.set(playerName + ".Lapis_Ore", i - 1);
			
			xrayData.save(f);
			
		}
		
		if(eventPlace.getBlock().getType().equals(Material.DIAMOND_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Diamond_Ore");
			xrayData.set(playerName + ".Diamond_Ore", i - 1);
			
			xrayData.save(f);
			
		}	
		
		if(eventPlace.getBlock().getType().equals(Material.EMERALD_ORE)){
			
			xrayData.load(f);
			
			int i = xrayData.getInt(playerName + ".Emerald_Ore");
			xrayData.set(playerName + ".Emerald_Ore", i - 1);
			
			xrayData.save(f);
			
		}
		
	}
	
}

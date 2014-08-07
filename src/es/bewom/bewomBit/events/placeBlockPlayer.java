package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class placeBlockPlayer implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@EventHandler
	public void OnPlace(BlockPlaceEvent eventPlace) throws SQLException, IOException {
		
		String playerUUID = eventPlace.getPlayer().getUniqueId().toString();
		String playerName = eventPlace.getPlayer().getName();
		
		Block placeBlock = eventPlace.getBlock();
		
		int locationBlockX = placeBlock.getLocation().getBlockX();
		int locationBlockY = placeBlock.getLocation().getBlockY();
		int locationBlockZ = placeBlock.getLocation().getBlockZ();
		
		if(placeBlock.getType() == Material.CHEST){
			
			log.info("CHEST");
			
			File cofredata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Cofres.yml");
			FileConfiguration cofreData = YamlConfiguration.loadConfiguration(cofredata);
			
			
			try {
				try {
					try {
						cofreData.load(cofredata);
						
						int hash = locationBlockX * 3 + locationBlockY * 2 + locationBlockZ *5;
						
						cofreData.set("Chests."+ hash + ".playerName", playerName);
						cofreData.set("Chests."+ hash + ".playerUUID", playerUUID);
						cofreData.set("Chests."+ hash + ".X", locationBlockX);
						cofreData.set("Chests."+ hash + ".Y", locationBlockY);
						cofreData.set("Chests."+ hash + ".Z", locationBlockZ);
						cofreData.set("Chests."+ hash + ".estado", "privado");
						
						cofreData.save(cofredata);
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
				} catch (IOException e) {
						e.printStackTrace();
				}
				
			} catch (InvalidConfigurationException e) {
					e.printStackTrace();
			}
		}

		
	}

}

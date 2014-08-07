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
import org.bukkit.event.block.BlockBreakEvent;

public class brokeBlockPlayer implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@EventHandler
	public void OnBreak(BlockBreakEvent eventPlace) throws SQLException, IOException {
		
		Block brokeBlock = eventPlace.getBlock();
		
		int locationBlockX = brokeBlock.getLocation().getBlockX();
		int locationBlockY = brokeBlock.getLocation().getBlockY();
		int locationBlockZ = brokeBlock.getLocation().getBlockZ();
		
		if(brokeBlock.getType() == Material.CHEST){
			
			File cofredata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Cofres.yml");
			FileConfiguration cofreData = YamlConfiguration.loadConfiguration(cofredata);
			
			
			try {
				try {
					try {
						cofreData.load(cofredata);
						
						int hash = locationBlockX * 3 + locationBlockY * 2 + locationBlockZ *5;
						
						cofreData.set("Chests."+ hash, null);
						
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
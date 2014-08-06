package es.bewom.bewomBit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class interactPlayer implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent eventInteract){
		
		String playerUUID = eventInteract.getPlayer().getUniqueId().toString();
		if (eventInteract.getClickedBlock().getType().equals(Material.CHEST)){
			
			File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
			File f = new File(userdata, File.separator + playerUUID + ".yml");
			FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
			
			int locationBlockX = 0;
			int locationBlockY = 0;
			int locationBlockZ = 0;
			
			int getlocationBlockX = 0;
			int getlocationBlockY = 0;
			int getlocationBlockZ = 0;
			
			locationBlockX = eventInteract.getClickedBlock().getLocation().getBlockX();
			locationBlockY = eventInteract.getClickedBlock().getLocation().getBlockY();
			locationBlockZ = eventInteract.getClickedBlock().getLocation().getBlockZ();
			
			try {
				try {
					try {
						playerData.load(f);
			
						getlocationBlockX = playerData.getInt("Chest.X", locationBlockX);
						getlocationBlockY = playerData.getInt("Chest.Y", locationBlockY);
						getlocationBlockZ = playerData.getInt("Chest.Z", locationBlockZ);
						
						playerData.save(f);
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
				} catch (IOException e) {
						e.printStackTrace();
				}
				
			} catch (InvalidConfigurationException e) {
					e.printStackTrace();
			}
			
			if (locationBlockX == getlocationBlockX && locationBlockY == getlocationBlockY && locationBlockZ == getlocationBlockZ){
				log.info("TU CHEST");
				
				
				
			} else {
				
				log.info("NO TU CHEST");
				
				eventInteract.setCancelled(true);
			}
		}
        
        
    }

}

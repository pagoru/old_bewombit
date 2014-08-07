package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class interactPlayer implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@SuppressWarnings({ "static-access", "unused" })
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent eventInteract){
		
		String playerUUID = eventInteract.getPlayer().getUniqueId().toString();
		String playerName = eventInteract.getPlayer().getName();
		Player craftPlayer = (Player) eventInteract.getPlayer();
			

		if ((eventInteract.getAction() == eventInteract.getAction().RIGHT_CLICK_BLOCK || eventInteract.getAction() == eventInteract.getAction().LEFT_CLICK_BLOCK) && eventInteract.getClickedBlock().getType() == Material.CHEST){
			
			File cofredata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Cofres.yml");
			FileConfiguration cofreData = YamlConfiguration.loadConfiguration(cofredata);
			
			int locationBlockX = 0;
			int locationBlockY = 0;
			int locationBlockZ = 0;
			
			int getlocationBlockHash = 0;
			
			String getlocationBlockPlayerName = null;
			String getlocationBlockPlayerUUID = null;
			int getlocationBlockX = 0;
			int getlocationBlockY = 0;
			int getlocationBlockZ = 0;
			String getlocationBlockEstado = null;
			
			
			locationBlockX = eventInteract.getClickedBlock().getLocation().getBlockX();
			locationBlockY = eventInteract.getClickedBlock().getLocation().getBlockY();
			locationBlockZ = eventInteract.getClickedBlock().getLocation().getBlockZ();
			
			int hash = locationBlockX * 3 + locationBlockY * 2 + locationBlockZ *5;
			
			int gethash = 0;
			
			try {
				try {
					try {
						cofreData.load(cofredata);
						
						getlocationBlockHash = cofreData.getInt("Chests." + hash);
						getlocationBlockPlayerName = cofreData.getString("Chests." + hash + ".playerName");
						getlocationBlockPlayerUUID = cofreData.getString("Chests." + hash + ".playerUUID");
						getlocationBlockX = cofreData.getInt("Chests." + hash + ".X");
						getlocationBlockY = cofreData.getInt("Chests." + hash + ".Y");
						getlocationBlockZ = cofreData.getInt("Chests." + hash + ".Z");
						getlocationBlockEstado = cofreData.getString("Chests."+ hash + ".estado");
						
						gethash = getlocationBlockX * 3 + getlocationBlockY * 2 + getlocationBlockZ *5;
						
						craftPlayer.sendMessage(gethash + "/" + hash + "   " + getlocationBlockPlayerName + " - " + playerName);
						
						if (gethash == hash){
							
							if (getlocationBlockPlayerName.equals(playerName) || getlocationBlockPlayerName.equals("Steve")){
								
								craftPlayer.sendMessage(gethash + "/" + hash + "   " + getlocationBlockPlayerName + " - " + playerName);
								
							} else if (getlocationBlockPlayerName.equals(null)) {
								
								craftPlayer.sendMessage(gethash + "/" + hash + "   " + getlocationBlockPlayerName + " - " + playerName);
								
							} else {
								
								eventInteract.setCancelled(true);
								
								craftPlayer.sendMessage(gethash + "/" + hash + "   " + getlocationBlockPlayerName + " - " + playerName);
								
							}
							
						} else {
							
							if (getlocationBlockPlayerName == null) {
								

								cofreData.set("Chests."+ hash + ".playerName", "Steve");
								cofreData.set("Chests."+ hash + ".playerUUID", null);
								cofreData.set("Chests."+ hash + ".X", locationBlockX);
								cofreData.set("Chests."+ hash + ".Y", locationBlockY);
								cofreData.set("Chests."+ hash + ".Z", locationBlockZ);
								cofreData.set("Chests."+ hash + ".estado", "publico");
								
								craftPlayer.sendMessage(gethash + "/" + hash + "   " + getlocationBlockPlayerName + " - " + playerName);
								
							} else {
								
								eventInteract.setCancelled(true);
								
							}
							
							
						}
						
						
						
						
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

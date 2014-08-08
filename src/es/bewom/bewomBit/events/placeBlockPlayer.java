package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class placeBlockPlayer implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@SuppressWarnings("unused")
	@EventHandler
	public void OnPlace(BlockPlaceEvent eventPlace) throws SQLException, IOException {
		
		String playerUUID = eventPlace.getPlayer().getUniqueId().toString();
		String playerName = eventPlace.getPlayer().getName();
		Player craftPlayer = (Player) eventPlace.getPlayer();
		
		boolean playerIsCongelado = false;
		
		boolean isCongelado = false;
		
		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
		
		Block placeBlock = eventPlace.getBlock();
		
		int locationBlockX = placeBlock.getLocation().getBlockX();
		int locationBlockY = placeBlock.getLocation().getBlockY();
		int locationBlockZ = placeBlock.getLocation().getBlockZ();
		Location locationBlock = placeBlock.getLocation();
		
		//---> Proteccion
		
		File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
		FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);
		
		String material = null;
		
		try {
			try {
				try {
					proteccionData.load(protecciondata);
					
					// Protección 
					
					if(placeBlock.getType() == Material.CHEST || placeBlock.getType() == Material.HOPPER || placeBlock.getType() == Material.TRAPPED_CHEST || placeBlock.getType() == Material.FURNACE || placeBlock.getType() == Material.ANVIL){
						
						if(placeBlock.getType() == Material.CHEST){
							material = "Chest";
						} else if (placeBlock.getType() == Material.HOPPER){
							material = "Hopper";
						} else if (placeBlock.getType() == Material.TRAPPED_CHEST){
							material = "TrappedChest";
						} else if (placeBlock.getType() == Material.FURNACE){
							material = "Furnace";
						} else if (placeBlock.getType() == Material.ANVIL){
							material = "Anvil";
						}
			
						int hash = locationBlockX * 3 + locationBlockY * 2 + locationBlockZ *5;
							
						proteccionData.set(material + "." + hash + ".playerName", playerName);
						proteccionData.set(material + "." + hash + ".playerUUID", playerUUID);
						proteccionData.set(material + "." + hash + ".X", locationBlockX);
						proteccionData.set(material + "." + hash + ".Y", locationBlockY);
						proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
						proteccionData.set(material + "." + hash + ".estado", "privado");

					} else if(placeBlock.getType() == Material.DROPPER || placeBlock.getType() == Material.JUKEBOX || placeBlock.getType() == Material.ENCHANTMENT_TABLE || placeBlock.getType() == Material.ENDER_CHEST) {
						
						if(placeBlock.getType() == Material.DROPPER){
							material = "Dropper";
						} else if (placeBlock.getType() == Material.JUKEBOX){
							material = "Jukebox";
						} else if (placeBlock.getType() == Material.ENCHANTMENT_TABLE){
							material = "EnchantmentTable";
						} else if (placeBlock.getType() == Material.ENDER_CHEST){
							material = "EnderChest";
						} 
			
						int hash = locationBlockX * 3 + locationBlockY * 2 + locationBlockZ *5;
							
						proteccionData.set(material + "." + hash + ".playerName", playerName);
						proteccionData.set(material + "." + hash + ".playerUUID", playerUUID);
						proteccionData.set(material + "." + hash + ".X", locationBlockX);
						proteccionData.set(material + "." + hash + ".Y", locationBlockY);
						proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
						proteccionData.set(material + "." + hash + ".estado", "publico");
						
					}
					
					if(placeBlock.getType().equals(Material.CHEST) || placeBlock.getType().equals(Material.TRAPPED_CHEST)){
						
						int getlocationBlockHash = 0;
						
						int hashlocationBlockXmas1 = locationBlockX + 1;
						int hashlocationBlockZmas1 = locationBlockZ + 1;
						int hashlocationBlockXmenos1 = locationBlockX - 1;
						int hashlocationBlockZmenos1 = locationBlockZ - 1;
						
						int hashpos1 = hashlocationBlockXmas1 * 3 + locationBlockY * 2 + locationBlockZ *5;
						int hashpos2 = locationBlockX * 3 + locationBlockY * 2 + hashlocationBlockZmas1 *5;
						int hashpos3 = hashlocationBlockXmenos1  * 3 + locationBlockY * 2 + locationBlockZ *5;
						int hashpos4 = locationBlockX * 3 + locationBlockY * 2 + hashlocationBlockZmenos1 *5;
						
						int hash = locationBlockX * 3 + locationBlockY * 2 + locationBlockZ *5;
						
						String getlocationBlockPlayerNamepos1 = proteccionData.getString(material + "." + hashpos1 + ".playerName");
						String getlocationBlockPlayerNamepos2 = proteccionData.getString(material + "." + hashpos2 + ".playerName");
						String getlocationBlockPlayerNamepos3 = proteccionData.getString(material + "." + hashpos3 + ".playerName");
						String getlocationBlockPlayerNamepos4 = proteccionData.getString(material + "." + hashpos4 + ".playerName");
						
						if(getlocationBlockPlayerNamepos1 != null){
							
							craftPlayer.sendMessage("asd");
							
							if (!getlocationBlockPlayerNamepos1.equals(playerName)){
								eventPlace.setCancelled(true);
							} 
						} 
						if(getlocationBlockPlayerNamepos2  != null){
							if (!getlocationBlockPlayerNamepos2.equals(playerName)){
								eventPlace.setCancelled(true);
							} 
						} 
						if(getlocationBlockPlayerNamepos3 != null){
							if (!getlocationBlockPlayerNamepos3.equals(playerName)){
								eventPlace.setCancelled(true);
							}
						} 
						if(getlocationBlockPlayerNamepos4 != null){
							if (!getlocationBlockPlayerNamepos4.equals(playerName)){
								eventPlace.setCancelled(true);
							} 
						}
						
					}
					
					proteccionData.save(protecciondata);
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
					e.printStackTrace();
			}
			
		} catch (InvalidConfigurationException e) {
				e.printStackTrace();
		}
		
		//---> Protección Hopper contra cofre y cofre trampa
		
		if(placeBlock.getType().equals(Material.HOPPER)){
			
			locationBlock = placeBlock.getLocation().add(0, 1, 0);
			
			if(locationBlock.getBlock().getType() == Material.CHEST || locationBlock.getBlock().getType() == Material.TRAPPED_CHEST){
				
				eventPlace.setCancelled(true);
				
			}
			
		}
		
		try {
			try {
				try {
					
					playerData.load(f);
					
					playerIsCongelado = playerData.getBoolean("Congelado");
					
					playerData.save(f);
					
					
					Data.load(data);
					
					isCongelado = Data.getBoolean("Congelado");
					
					Data.save(data);
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
					e.printStackTrace();
			}
			
		} catch (InvalidConfigurationException e) {
				e.printStackTrace();
		}

		if (!craftPlayer.hasPermission("bewom.admin") || !craftPlayer.hasPermission("bewom.mod")){
			if (playerIsCongelado || isCongelado){
	
				eventPlace.setCancelled(true);
				craftPlayer.sendMessage(ChatColor.RED + "Has sido congelado temporalmente.");
			}
		}
	}

}

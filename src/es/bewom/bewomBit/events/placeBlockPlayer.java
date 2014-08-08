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
		
		//---> Protección Hopper contra cofre
		
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

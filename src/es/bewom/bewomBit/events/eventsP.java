package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class eventsP {
	
	public static void blockPlacePlayerEventsP(BlockPlaceEvent eventPlace) throws SQLException, IOException {
		
		String playerUUID = eventPlace.getPlayer().getUniqueId().toString();
		String playerName = eventPlace.getPlayer().getName();
				
		Block placeBlock = eventPlace.getBlock();
		
		int locationBlockX = placeBlock.getLocation().getBlockX();
		int locationBlockY = placeBlock.getLocation().getBlockY();
		int locationBlockZ = placeBlock.getLocation().getBlockZ();
		Location locationBlock = placeBlock.getLocation();
		
		int locationBlockXmas1 = locationBlockX + 1;
		int locationBlockZmas1 = locationBlockZ + 1;
		int locationBlockXmenos1 = locationBlockX - 1;
		int locationBlockZmenos1 = locationBlockZ - 1;
		
		String hashpos1 = Integer.toString(locationBlockXmas1) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
		String hashpos2 = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZmas1);
		String hashpos3 = Integer.toString(locationBlockXmenos1) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
		String hashpos4 = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZmenos1);
		
		String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
		
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
					
					if(placeBlock.getType() == Material.FURNACE || placeBlock.getType() == Material.ANVIL){
						
						if (placeBlock.getType() == Material.FURNACE){
							material = "Furnace";
						} else if (placeBlock.getType() == Material.ANVIL){
							material = "Anvil";
						}
						
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
						
						proteccionData.set(material + "." + hash + ".playerName", playerName);
						proteccionData.set(material + "." + hash + ".playerUUID", playerUUID);
						proteccionData.set(material + "." + hash + ".X", locationBlockX);
						proteccionData.set(material + "." + hash + ".Y", locationBlockY);
						proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
						proteccionData.set(material + "." + hash + ".estado", "publico");
						
					}
					
					if(placeBlock.getType().equals(Material.CHEST) || placeBlock.getType().equals(Material.TRAPPED_CHEST)){
						
						if(placeBlock.getType() == Material.CHEST){
							material = "Chest";
						} else if (placeBlock.getType() == Material.TRAPPED_CHEST){
							material = "TrappedChest";
						}
						
						String getlocationBlockPlayerNamepos1 = proteccionData.getString(material + "." + hashpos1 + ".playerName");
						String getlocationBlockPlayerNamepos2 = proteccionData.getString(material + "." + hashpos2 + ".playerName");
						String getlocationBlockPlayerNamepos3 = proteccionData.getString(material + "." + hashpos3 + ".playerName");
						String getlocationBlockPlayerNamepos4 = proteccionData.getString(material + "." + hashpos4 + ".playerName");
						
						if (getlocationBlockPlayerNamepos1 == null && getlocationBlockPlayerNamepos2 == null && getlocationBlockPlayerNamepos3 == null && getlocationBlockPlayerNamepos4 == null){
							
							proteccionData.set(material + "." + hash + ".playerName", playerName);
							proteccionData.set(material + "." + hash + ".X", locationBlockX);
							proteccionData.set(material + "." + hash + ".Y", locationBlockY);
							proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
							proteccionData.set(material + "." + hash + ".estado", "privado");
							proteccionData.set(material + "." + hash + ".doble", false);
							
						} else {
							
							if (getlocationBlockPlayerNamepos1 != null){
								
								if (getlocationBlockPlayerNamepos1.equals(playerName)){
									
									String estadohaspos1 = proteccionData.getString(material + "." + hashpos1 + ".estado");
									
									proteccionData.set(material + "." + hash + ".playerName", playerName);
									proteccionData.set(material + "." + hash + ".X", locationBlockX);
									proteccionData.set(material + "." + hash + ".Y", locationBlockY);
									proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
									proteccionData.set(material + "." + hash + ".estado", estadohaspos1);
									proteccionData.set(material + "." + hash + ".doble", true);
									proteccionData.set(material + "." + hash + ".dobleHash", hashpos1);
									
									proteccionData.set(material + "." + hashpos1 + ".doble", true);
									proteccionData.set(material + "." + hashpos1 + ".dobleHash", hash);
									
								} else {
									
									eventPlace.setCancelled(true);
									
								}
									
							} 
							
							if (getlocationBlockPlayerNamepos2 != null){
								
								if (getlocationBlockPlayerNamepos2.equals(playerName)){
									
									String estadohaspos2 = proteccionData.getString(material + "." + hashpos2 + ".estado");
									
									proteccionData.set(material + "." + hash + ".playerName", playerName);
									proteccionData.set(material + "." + hash + ".X", locationBlockX);
									proteccionData.set(material + "." + hash + ".Y", locationBlockY);
									proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
									proteccionData.set(material + "." + hash + ".estado", estadohaspos2);
									proteccionData.set(material + "." + hash + ".doble", true);
									proteccionData.set(material + "." + hash + ".dobleHash", hashpos2);
									
									proteccionData.set(material + "." + hashpos2 + ".doble", true);
									proteccionData.set(material + "." + hashpos2 + ".dobleHash", hash);
									
								} else {
									
									eventPlace.setCancelled(true);
									
								}
									
							} 
							
							if (getlocationBlockPlayerNamepos3 != null){
								
								if (getlocationBlockPlayerNamepos3.equals(playerName)){
									
									String estadohaspos3 = proteccionData.getString(material + "." + hashpos3 + ".estado");
									
									proteccionData.set(material + "." + hash + ".playerName", playerName);
									proteccionData.set(material + "." + hash + ".X", locationBlockX);
									proteccionData.set(material + "." + hash + ".Y", locationBlockY);
									proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
									proteccionData.set(material + "." + hash + ".estado", estadohaspos3);
									proteccionData.set(material + "." + hash + ".doble", true);
									proteccionData.set(material + "." + hash + ".dobleHash", hashpos3);
									
									proteccionData.set(material + "." + hashpos3 + ".doble", true);
									proteccionData.set(material + "." + hashpos3 + ".dobleHash", hash);
									
								} else {
									
									eventPlace.setCancelled(true);
									
								}
									
							} 
							
							if (getlocationBlockPlayerNamepos4 != null){
								
								if (getlocationBlockPlayerNamepos4.equals(playerName)){
									
									String estadohaspos4 = proteccionData.getString(material + "." + hashpos4 + ".estado");
									
									proteccionData.set(material + "." + hash + ".playerName", playerName);
									proteccionData.set(material + "." + hash + ".X", locationBlockX);
									proteccionData.set(material + "." + hash + ".Y", locationBlockY);
									proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
									proteccionData.set(material + "." + hash + ".estado", estadohaspos4);
									proteccionData.set(material + "." + hash + ".doble", true);
									proteccionData.set(material + "." + hash + ".dobleHash", hashpos4);
									
									proteccionData.set(material + "." + hashpos4 + ".doble", true);
									proteccionData.set(material + "." + hashpos4 + ".dobleHash", hash);
									
								} else {
									
									eventPlace.setCancelled(true);
									
								}
									
							} 
							
						}
						
					}
					
					//---> Protección Hopper contra cofre y cofre trampa
					
					if(placeBlock.getType().equals(Material.HOPPER)){
						
						material = "Hopper";
						
						locationBlock = placeBlock.getLocation().add(0, 1, 0);
						
						if(locationBlock.getBlock().getType() == Material.CHEST || locationBlock.getBlock().getType() == Material.TRAPPED_CHEST || locationBlock.getBlock().getType() == Material.DROPPER || locationBlock.getBlock().getType() == Material.FURNACE){
							
							eventPlace.setCancelled(true);
							
						} else {
							
							proteccionData.set(material + "." + hash + ".playerName", playerName);
							proteccionData.set(material + "." + hash + ".X", locationBlockX);
							proteccionData.set(material + "." + hash + ".Y", locationBlockY);
							proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
							proteccionData.set(material + "." + hash + ".estado", "privado");
							
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
	}

	@SuppressWarnings("unused")
	public static void brokeBlockPlayerEventsP(BlockBreakEvent eventPlace){

		String playerUUID = eventPlace.getPlayer().getUniqueId().toString();
		String playerName = eventPlace.getPlayer().getName();
		Player craftPlayer = (Player) eventPlace.getPlayer();
		
		Block brokeBlock = eventPlace.getBlock();

		int locationBlockX = brokeBlock.getLocation().getBlockX();
		int locationBlockY = brokeBlock.getLocation().getBlockY();
		int locationBlockZ = brokeBlock.getLocation().getBlockZ();


		File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
		FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);

		String material = null;

		try {
			try {
				try {

					if(brokeBlock.getType() == Material.CHEST || brokeBlock.getType() == Material.HOPPER || brokeBlock.getType() == Material.TRAPPED_CHEST || brokeBlock.getType() == Material.FURNACE || brokeBlock.getType() == Material.ANVIL || brokeBlock.getType() == Material.DROPPER || brokeBlock.getType() == Material.JUKEBOX || brokeBlock.getType() == Material.ENCHANTMENT_TABLE || brokeBlock.getType() == Material.ENDER_CHEST){

						if(brokeBlock.getType() == Material.CHEST){
							material = "Chest";
						} else if (brokeBlock.getType() == Material.HOPPER){
							material = "Hopper";
						} else if (brokeBlock.getType() == Material.TRAPPED_CHEST){
							material = "TrappedChest";
						} else if (brokeBlock.getType() == Material.FURNACE){
							material = "Furnace";
						} else if (brokeBlock.getType() == Material.ANVIL){
							material = "Anvil";
						} else if (brokeBlock.getType() == Material.DROPPER){
							material = "Dropper";
						} else if (brokeBlock.getType() == Material.JUKEBOX){
							material = "Jukebox";
						} else if (brokeBlock.getType() == Material.ENCHANTMENT_TABLE){
							material = "EnchantmentTable";
						} else if (brokeBlock.getType() == Material.ENDER_CHEST){
							material = "EnderChest";
						} 

						proteccionData.load(protecciondata);

						String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);

						Boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");

						if(getdobleChest.equals(true)) {

							String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");

							proteccionData.set(material + "." + getdobleChestHash + ".doble", false);
							proteccionData.set(material + "." + getdobleChestHash + ".dobleHash", null);

						}
						proteccionData.set(material + "." + hash, null);
						proteccionData.save(protecciondata);
					}
					
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
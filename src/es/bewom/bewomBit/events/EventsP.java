package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsP {

	public static void blockPlacePlayerEventsP(BlockPlaceEvent eventPlace) throws SQLException, IOException, InvalidConfigurationException {

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

		proteccionData.load(protecciondata);

		// Proteccion

		if(placeBlock.getType() == Material.FURNACE 
				|| placeBlock.getType() == Material.ANVIL 
				|| placeBlock.getType() == Material.WOODEN_DOOR){

			if (placeBlock.getType() == Material.FURNACE){
				material = "Furnace";
			}
			else if (placeBlock.getType() == Material.ANVIL){
				material = "Anvil";
			}
			else if (placeBlock.getType() == Material.WOODEN_DOOR){
				material = "WoodenDoor";
			}			
			actualizarEstado (proteccionData, material, hash, playerName, playerUUID, locationBlockX, locationBlockY, locationBlockZ, "privado");
		}
		else if(placeBlock.getType() == Material.DROPPER ||
				placeBlock.getType() == Material.JUKEBOX ||
				placeBlock.getType() == Material.ENCHANTMENT_TABLE ||
				placeBlock.getType() == Material.ENDER_CHEST) {

			if(placeBlock.getType() == Material.DROPPER){
				material = "Dropper";
			}
			else if (placeBlock.getType() == Material.JUKEBOX){
				material = "Jukebox";
			}
			else if (placeBlock.getType() == Material.ENCHANTMENT_TABLE){
				material = "EnchantmentTable";
			}
			else if (placeBlock.getType() == Material.ENDER_CHEST){
				material = "EnderChest";
			} 			
			actualizarEstado (proteccionData, material, hash, playerName, playerUUID, locationBlockX, locationBlockY, locationBlockZ, "publico");
		}

		if(placeBlock.getType().equals(Material.CHEST) || placeBlock.getType().equals(Material.TRAPPED_CHEST)){

			if(placeBlock.getType() == Material.CHEST){
				material = "Chest";
			}
			else if (placeBlock.getType() == Material.TRAPPED_CHEST){
				material = "TrappedChest";
			}

			String getlocationBlockPlayerNamepos1 = proteccionData.getString(material + "." + hashpos1 + ".playerName");
			String getlocationBlockPlayerNamepos2 = proteccionData.getString(material + "." + hashpos2 + ".playerName");
			String getlocationBlockPlayerNamepos3 = proteccionData.getString(material + "." + hashpos3 + ".playerName");
			String getlocationBlockPlayerNamepos4 = proteccionData.getString(material + "." + hashpos4 + ".playerName");

			if (getlocationBlockPlayerNamepos1 == null && getlocationBlockPlayerNamepos2 == null && getlocationBlockPlayerNamepos3 == null && getlocationBlockPlayerNamepos4 == null){
				actualizarEstado (proteccionData, material, hash, playerName, locationBlockX, locationBlockY, locationBlockZ, "privado", false);
			}
			else {

				if (getlocationBlockPlayerNamepos1 != null){
					
					if (getlocationBlockPlayerNamepos1.equals(playerName)){
						actualizarEstado (proteccionData, material, hash, playerName, hashpos1, locationBlockX, locationBlockY, locationBlockZ);						
					}
					else {
						eventPlace.setCancelled(true);
					}
				} 

				if (getlocationBlockPlayerNamepos2 != null){
					if (getlocationBlockPlayerNamepos2.equals(playerName)){
						actualizarEstado (proteccionData, material, hash, playerName, hashpos2, locationBlockX, locationBlockY, locationBlockZ);
					}
					else {
						eventPlace.setCancelled(true);
					}
				}

				if (getlocationBlockPlayerNamepos3 != null){
					if (getlocationBlockPlayerNamepos3.equals(playerName)){
						actualizarEstado (proteccionData, material, hash, playerName, hashpos3, locationBlockX, locationBlockY, locationBlockZ);
					}
					else {
						eventPlace.setCancelled(true);
					}
				} 

				if (getlocationBlockPlayerNamepos4 != null){
					if (getlocationBlockPlayerNamepos4.equals(playerName)){
						actualizarEstado (proteccionData, material, hash, playerName, hashpos4, locationBlockX, locationBlockY, locationBlockZ);
					}
					else {
						eventPlace.setCancelled(true);
					}
				} 
			}
		}

		//---> Protección Hopper contra cofre y cofre trampa

		if(placeBlock.getType().equals(Material.HOPPER)){

			material = "Hopper";

			locationBlock = placeBlock.getLocation().add(0, 1, 0);

			if(locationBlock.getBlock().getType() == Material.CHEST ||
					locationBlock.getBlock().getType() == Material.TRAPPED_CHEST ||
					locationBlock.getBlock().getType() == Material.DROPPER ||
					locationBlock.getBlock().getType() == Material.FURNACE){
				eventPlace.setCancelled(true);
			}
			else {
				actualizarEstado (proteccionData, material, hash, playerName, locationBlockX, locationBlockY, locationBlockZ, "privado");
			}
		}
		proteccionData.save(protecciondata);
	}

	@SuppressWarnings({"unused", "deprecation" })
	public static void brokeBlockPlayerEventsP(BlockBreakEvent eventPlace) throws FileNotFoundException, IOException, InvalidConfigurationException{

		Player craftPlayer = (Player) eventPlace.getPlayer();

		Block brokeBlock = eventPlace.getBlock();

		int locationBlockX = brokeBlock.getLocation().getBlockX();
		int locationBlockY = brokeBlock.getLocation().getBlockY();
		int locationBlockZ = brokeBlock.getLocation().getBlockZ();


		File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
		FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);

		String material = null;

		if(brokeBlock.getType() == Material.CHEST 
				|| brokeBlock.getType() == Material.HOPPER 
				|| brokeBlock.getType() == Material.TRAPPED_CHEST 
				|| brokeBlock.getType() == Material.FURNACE 
				|| brokeBlock.getType() == Material.ANVIL 
				|| brokeBlock.getType() == Material.DROPPER 
				|| brokeBlock.getType() == Material.JUKEBOX 
				|| brokeBlock.getType() == Material.ENCHANTMENT_TABLE 
				|| brokeBlock.getType() == Material.ENDER_CHEST
				|| brokeBlock.getType() == Material.WOODEN_DOOR){

			if(brokeBlock.getType() == Material.CHEST){
				material = "Chest";
			}
			else if (brokeBlock.getType() == Material.HOPPER){
				material = "Hopper";
			}
			else if (brokeBlock.getType() == Material.TRAPPED_CHEST){
				material = "TrappedChest";
			}
			else if (brokeBlock.getType() == Material.FURNACE){
				material = "Furnace";
			}
			else if (brokeBlock.getType() == Material.ANVIL){
				material = "Anvil";
			}
			else if (brokeBlock.getType() == Material.DROPPER){
				material = "Dropper";
			}
			else if (brokeBlock.getType() == Material.JUKEBOX){
				material = "Jukebox";
			}
			else if (brokeBlock.getType() == Material.ENCHANTMENT_TABLE){
				material = "EnchantmentTable";
			}
			else if (brokeBlock.getType() == Material.ENDER_CHEST){
				material = "EnderChest";
			} 
			else if (brokeBlock.getType() == Material.WOODEN_DOOR){
				material = "WoodenDoor";
			} 

			proteccionData.load(protecciondata);

			String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);

			Boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");

			if(getdobleChest.equals(true)) {

				String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");

				proteccionData.set(material + "." + getdobleChestHash + ".doble", false);
				proteccionData.set(material + "." + getdobleChestHash + ".dobleHash", null);

			}

			if (brokeBlock.getType() == Material.WOODEN_DOOR){

				if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){
					proteccionData.set(material + "." + hash, null);
				}
				else if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){

					String hashW = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
					proteccionData.set(material + "." + hashW, null);
				}
			}
			else {
				proteccionData.set(material + "." + hash, null);
			}
			proteccionData.save(protecciondata);
		}

		//---> Protección Puertas

		Location locationBlock = eventPlace.getBlock().getLocation().add(0, 1, 0);
		Location locationBlock1 = eventPlace.getBlock().getLocation().add(0, 2, 0);
	}
	
	public static void playerInteractEventsP(PlayerInteractEvent eventInteract) throws FileNotFoundException, IOException, InvalidConfigurationException{

		String playerName = eventInteract.getPlayer().getName();
		Player craftPlayer = (Player) eventInteract.getPlayer();

		File amigosdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File f1 = new File(amigosdata, File.separator + "amigos.yml");
		FileConfiguration amigosData = YamlConfiguration.loadConfiguration(f1);

		if (eventInteract.getAction() == Action.RIGHT_CLICK_BLOCK || eventInteract.getAction() == Action.LEFT_CLICK_BLOCK){

			File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
			FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);

			int locationBlockX = 0;
			int locationBlockY = 0;
			int locationBlockZ = 0;

			String getlocationBlockPlayerName = null;
			int getlocationBlockX = 0;
			int getlocationBlockY = 0;
			int getlocationBlockZ = 0;
			String getlocationBlockEstado = null;

			locationBlockX = eventInteract.getClickedBlock().getLocation().getBlockX();
			locationBlockY = eventInteract.getClickedBlock().getLocation().getBlockY();
			locationBlockZ = eventInteract.getClickedBlock().getLocation().getBlockZ();

			String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);

			String material = null;
			String nombreMaterial = null;

			proteccionData.load(protecciondata);
			amigosData.load(f1);

			//proteccion
			if(eventInteract.getClickedBlock().getType() == Material.CHEST 
					|| eventInteract.getClickedBlock().getType() == Material.HOPPER 
					|| eventInteract.getClickedBlock().getType() == Material.TRAPPED_CHEST 
					|| eventInteract.getClickedBlock().getType() == Material.FURNACE 
					|| eventInteract.getClickedBlock().getType() == Material.ANVIL 
					|| eventInteract.getClickedBlock().getType() == Material.DROPPER 
					|| eventInteract.getClickedBlock().getType() == Material.JUKEBOX 
					|| eventInteract.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE 
					|| eventInteract.getClickedBlock().getType() == Material.ENDER_CHEST
					|| eventInteract.getClickedBlock().getType() == Material.WOODEN_DOOR){

				if(eventInteract.getClickedBlock().getType() == Material.CHEST){
					material = "Chest";
					nombreMaterial = "Este cofre";
				}
				else if (eventInteract.getClickedBlock().getType() == Material.HOPPER){
					material = "Hopper";
					nombreMaterial = "Este hopper";
				}
				else if (eventInteract.getClickedBlock().getType() == Material.TRAPPED_CHEST){
					material = "TrappedChest";
					nombreMaterial = "Este cofre trampa";
				}
				else if (eventInteract.getClickedBlock().getType() == Material.FURNACE){
					material = "Furnace";
					nombreMaterial = "Este horno";
				}
				else if (eventInteract.getClickedBlock().getType() == Material.ANVIL){
					material = "Anvil";
					nombreMaterial = "Este yunque";
				}
				else if (eventInteract.getClickedBlock().getType() == Material.DROPPER){
					material = "Dropper";
					nombreMaterial = "Este dropper";
				}
				else if (eventInteract.getClickedBlock().getType() == Material.JUKEBOX){
					material = "Jukebox";
					nombreMaterial = "Esta jukebox";
				}
				else if (eventInteract.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE){
					material = "EnchantmentTable";
					nombreMaterial = "Este mesa de encantamientos";
				}
				else if (eventInteract.getClickedBlock().getType() == Material.ENDER_CHEST){
					material = "EnderChest"; 
					nombreMaterial = "Este enderchest";
				} 
				else if (eventInteract.getClickedBlock().getType() == Material.WOODEN_DOOR){
					material = "WoodenDoor"; 
					nombreMaterial = "Esta puerta";
				}

				List<String> pLista = proteccionData.getStringList(material + "." + hash + ".miembros");

				getlocationBlockPlayerName = proteccionData.getString(material + "." + hash + ".playerName");
				getlocationBlockX = proteccionData.getInt(material + "." + hash + ".X");
				getlocationBlockY = proteccionData.getInt(material + "." + hash + ".Y");
				getlocationBlockZ = proteccionData.getInt(material + "." + hash + ".Z");
				getlocationBlockEstado = proteccionData.getString(material + "." + hash + ".estado");

				List<String> pListaP = amigosData.getStringList(playerName + ".amigos");

				String gethash = Integer.toString(getlocationBlockX) + Integer.toString(getlocationBlockY) + Integer.toString(getlocationBlockZ);

				if (eventInteract.getClickedBlock().getType() == Material.WOODEN_DOOR){

					if(eventInteract.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){

						if (gethash.equals(hash)){

							if (getlocationBlockPlayerName.equals(playerName) 
									|| getlocationBlockEstado.equals("publico") 
									|| pLista.toString().contains(playerName) 
									|| pListaP.contains(getlocationBlockPlayerName)){

							}
							else if (getlocationBlockPlayerName.equals(null)) {

							} else {

								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
								eventInteract.setCancelled(true);

							}
						}
						else {

							if (getlocationBlockPlayerName == null) {								
								actualizarEstado (proteccionData, material, hash, "Steve", locationBlockX, locationBlockY, locationBlockZ, "publico");
							}
							else {
								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
								eventInteract.setCancelled(true);
							}
						}
					}
					else if(eventInteract.getClickedBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){

						String hashW = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);

						List<String> pListaW = proteccionData.getStringList(material + "." + hashW + ".miembros");

						String getlocationBlockPlayerNameW = proteccionData.getString(material + "." + hashW + ".playerName");
						int getlocationBlockXW = proteccionData.getInt(material + "." + hashW + ".X");
						int getlocationBlockYW = proteccionData.getInt(material + "." + hashW + ".Y");
						int getlocationBlockZW = proteccionData.getInt(material + "." + hashW + ".Z");
						String getlocationBlockEstadoW = proteccionData.getString(material + "." + hashW + ".estado");

						String gethashW = Integer.toString(getlocationBlockXW) + Integer.toString(getlocationBlockYW) + Integer.toString(getlocationBlockZW);

						List<String> pListaPW = amigosData.getStringList(playerName + ".amigos");

						if (gethashW.equals(hashW)){

							if (getlocationBlockPlayerNameW.equals(playerName) 
									|| getlocationBlockEstadoW.equals("publico") 
									|| pListaW.toString().contains(playerName) 
									|| pListaPW.contains(getlocationBlockPlayerNameW)){

							}
							else if (getlocationBlockPlayerNameW.equals(null)) {

							} else {

								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerNameW + ".");
								eventInteract.setCancelled(true);

							}
						}
						else {

							if (getlocationBlockPlayerNameW == null) {								
								actualizarEstado (proteccionData, material, hashW, "Steve", locationBlockX, locationBlockY, locationBlockZ, "publico");
							}
							else {

								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerNameW + " / " + playerName + ".");
								eventInteract.setCancelled(true);

							}
						}

					}

				} else {

					if (gethash.equals(hash)){

						if (getlocationBlockPlayerName.equals(playerName) 
								|| getlocationBlockEstado.equals("publico") 
								|| pLista.toString().contains(playerName) 
								|| pListaP.contains(getlocationBlockPlayerName)){

						}
						else if (getlocationBlockPlayerName.equals(null)) {

						} else {

							craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
							eventInteract.setCancelled(true);

						}
					}
					else {

						if (getlocationBlockPlayerName == null) {					
							actualizarEstado (proteccionData, material, hash, "Steve", locationBlockX, locationBlockY, locationBlockZ, "publico");
						}
						else {

							craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
							eventInteract.setCancelled(true);
						}
					}
				}
			}
			amigosData.save(f1);
			proteccionData.save(protecciondata);
		}
	}

	public static void actualizarEstado (FileConfiguration proteccionData, String material, String hash, String playerName, String playerUUID, int locationBlockX, int locationBlockY, int locationBlockZ, String estado){

		proteccionData.set(material + "." + hash + ".playerName", playerName);
		proteccionData.set(material + "." + hash + ".playerUUID", playerUUID);
		proteccionData.set(material + "." + hash + ".X", locationBlockX);
		proteccionData.set(material + "." + hash + ".Y", locationBlockY);
		proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
		proteccionData.set(material + "." + hash + ".estado", estado);
	}

	public static void actualizarEstado (FileConfiguration proteccionData, String material, String hash, String playerName, int locationBlockX, int locationBlockY, int locationBlockZ, String estado, boolean doble){

		proteccionData.set(material + "." + hash + ".playerName", playerName);
		proteccionData.set(material + "." + hash + ".X", locationBlockX);
		proteccionData.set(material + "." + hash + ".Y", locationBlockY);
		proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
		proteccionData.set(material + "." + hash + ".estado", estado);
		proteccionData.set(material + "." + hash + ".doble", doble);
	}

	public static void actualizarEstado (FileConfiguration proteccionData, String material, String hash, String playerName, String hashpos, int locationBlockX, int locationBlockY, int locationBlockZ){

		String estadohaspos = proteccionData.getString(material + "." + hashpos + ".estado");

		proteccionData.set(material + "." + hash + ".playerName", playerName);
		proteccionData.set(material + "." + hash + ".X", locationBlockX);
		proteccionData.set(material + "." + hash + ".Y", locationBlockY);
		proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
		proteccionData.set(material + "." + hash + ".estado", estadohaspos);
		proteccionData.set(material + "." + hash + ".doble", true);
		proteccionData.set(material + "." + hash + ".dobleHash", hashpos);
		
		proteccionData.set(material + "." + hashpos + ".doble", true);
		proteccionData.set(material + "." + hashpos + ".dobleHash", hash);

		List<String> pList = new ArrayList<String>(); 
		List<String> pLista = proteccionData.getStringList(material + "." + hashpos + ".miembros");

		pList.addAll(pLista);

		proteccionData.set(material + "." + hash + ".miembros", pList);
	}
	
	public static void actualizarEstado (FileConfiguration proteccionData, String material, String hash, String playerName, int locationBlockX, int locationBlockY, int locationBlockZ, String estado){		
		
		proteccionData.set(material + "." + hash + ".playerName", playerName);
		proteccionData.set(material + "." + hash + ".X", locationBlockX);
		proteccionData.set(material + "." + hash + ".Y", locationBlockY);
		proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
		proteccionData.set(material + "." + hash + ".estado", estado);
	}
}

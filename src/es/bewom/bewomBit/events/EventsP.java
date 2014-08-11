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
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsP {

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

					// Proteccion

					if(placeBlock.getType() == Material.FURNACE || placeBlock.getType() == Material.ANVIL){

						if (placeBlock.getType() == Material.FURNACE){
							material = "Furnace";
						}
						else if (placeBlock.getType() == Material.ANVIL){
							material = "Anvil";
						}

						proteccionData.set(material + "." + hash + ".playerName", playerName);
						proteccionData.set(material + "." + hash + ".playerUUID", playerUUID);
						proteccionData.set(material + "." + hash + ".X", locationBlockX);
						proteccionData.set(material + "." + hash + ".Y", locationBlockY);
						proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
						proteccionData.set(material + "." + hash + ".estado", "privado");
						proteccionData.set(material + "." + hash + ".miembrosbool", false);

					} else if(placeBlock.getType() == Material.DROPPER || placeBlock.getType() == Material.JUKEBOX || placeBlock.getType() == Material.ENCHANTMENT_TABLE || placeBlock.getType() == Material.ENDER_CHEST) {

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

						proteccionData.set(material + "." + hash + ".playerName", playerName);
						proteccionData.set(material + "." + hash + ".playerUUID", playerUUID);
						proteccionData.set(material + "." + hash + ".X", locationBlockX);
						proteccionData.set(material + "." + hash + ".Y", locationBlockY);
						proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
						proteccionData.set(material + "." + hash + ".estado", "publico");
						proteccionData.set(material + "." + hash + ".miembrosbool", false);

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

							proteccionData.set(material + "." + hash + ".playerName", playerName);
							proteccionData.set(material + "." + hash + ".X", locationBlockX);
							proteccionData.set(material + "." + hash + ".Y", locationBlockY);
							proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
							proteccionData.set(material + "." + hash + ".estado", "privado");
							proteccionData.set(material + "." + hash + ".doble", false);

						}
						else {

							if (getlocationBlockPlayerNamepos1 != null){

								if (getlocationBlockPlayerNamepos1.equals(playerName)){

									String estadohaspos1 = proteccionData.getString(material + "." + hashpos1 + ".estado");
									Boolean membool = proteccionData.getBoolean(material + "." + hashpos1 + ".miembrosbool");

									proteccionData.set(material + "." + hash + ".playerName", playerName);
									proteccionData.set(material + "." + hash + ".X", locationBlockX);
									proteccionData.set(material + "." + hash + ".Y", locationBlockY);
									proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
									proteccionData.set(material + "." + hash + ".estado", estadohaspos1);
									proteccionData.set(material + "." + hash + ".doble", true);
									proteccionData.set(material + "." + hash + ".dobleHash", hashpos1);
									proteccionData.set(material + "." + hash + ".miembrosbool", membool);

									proteccionData.set(material + "." + hashpos1 + ".doble", true);
									proteccionData.set(material + "." + hashpos1 + ".dobleHash", hash);

									List<String> pList1 = new ArrayList<String>(); 
									List<String> pLista = proteccionData.getStringList(material + "." + hashpos1 + ".miembros");

									pList1.addAll(pLista);

									proteccionData.set(material + "." + hash + ".miembros", pList1);

								}
								else {

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

									List<String> pList1 = new ArrayList<String>(); 
									List<String> pLista = proteccionData.getStringList(material + "." + hashpos2 + ".miembros");

									pList1.addAll(pLista);

									proteccionData.set(material + "." + hash + ".miembros", pList1);

								}
								else {

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

									List<String> pList1 = new ArrayList<String>(); 
									List<String> pLista = proteccionData.getStringList(material + "." + hashpos3 + ".miembros");

									pList1.addAll(pLista);

									proteccionData.set(material + "." + hash + ".miembros", pList1);

								}
								else {

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

									List<String> pList1 = new ArrayList<String>(); 
									List<String> pLista = proteccionData.getStringList(material + "." + hashpos4 + ".miembros");

									pList1.addAll(pLista);

									proteccionData.set(material + "." + hash + ".miembros", pList1);

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

						if(locationBlock.getBlock().getType() == Material.CHEST || locationBlock.getBlock().getType() == Material.TRAPPED_CHEST || locationBlock.getBlock().getType() == Material.DROPPER || locationBlock.getBlock().getType() == Material.FURNACE){

							eventPlace.setCancelled(true);

						}
						else {

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

	public static void deathPlayerEventsP (PlayerDeathEvent eventDeath){

		Player playerCraft = eventDeath.getEntity();
		String playerName = playerCraft.getName();

		int X = playerCraft.getLocation().getBlockX();
		int Y = playerCraft.getLocation().getBlockY();
		int Z = playerCraft.getLocation().getBlockZ();

		Bukkit.getWorld("world").getBlockAt(X, Y, Z).setType(Material.CHEST);
		Location loc = new Location(Bukkit.getWorld("world"), X, Y + 1, Z);
		loc.getBlock().setType(Material.SKULL);
		BlockState state = loc.getBlock().getState();

		Skull s = (Skull) state;
		s.setSkullType(SkullType.PLAYER);
		s.setOwner(playerName);
		loc.getBlock().getChunk().load();
		s.update(true);
	}

	@SuppressWarnings({ "unused", "static-access" })
	public static void playerInteractEventsP(PlayerInteractEvent eventInteract){

		String playerUUID = eventInteract.getPlayer().getUniqueId().toString();
		String playerName = eventInteract.getPlayer().getName();
		Player craftPlayer = (Player) eventInteract.getPlayer();

		boolean playerIsCongelado = false;
		boolean isCongelado = false;

		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);


		if (eventInteract.getAction() == eventInteract.getAction().RIGHT_CLICK_BLOCK || eventInteract.getAction() == eventInteract.getAction().LEFT_CLICK_BLOCK){

			File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
			FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);

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

			String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);

			String material = null;
			String nombreMaterial = null;

			try {
				try {
					try {
						proteccionData.load(protecciondata);

						//proteccion
						if(eventInteract.getClickedBlock().getType() == Material.CHEST || eventInteract.getClickedBlock().getType() == Material.HOPPER || eventInteract.getClickedBlock().getType() == Material.TRAPPED_CHEST || eventInteract.getClickedBlock().getType() == Material.FURNACE || eventInteract.getClickedBlock().getType() == Material.ANVIL || eventInteract.getClickedBlock().getType() == Material.DROPPER || eventInteract.getClickedBlock().getType() == Material.JUKEBOX || eventInteract.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE || eventInteract.getClickedBlock().getType() == Material.ENDER_CHEST){

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

							List<String> pLista = proteccionData.getStringList(material + "." + hash + ".miembros");

							getlocationBlockHash = proteccionData.getInt(material + "." + hash);
							getlocationBlockPlayerName = proteccionData.getString(material + "." + hash + ".playerName");
							getlocationBlockX = proteccionData.getInt(material + "." + hash + ".X");
							getlocationBlockY = proteccionData.getInt(material + "." + hash + ".Y");
							getlocationBlockZ = proteccionData.getInt(material + "." + hash + ".Z");
							getlocationBlockEstado = proteccionData.getString(material + "." + hash + ".estado");

							String gethash = Integer.toString(getlocationBlockX) + Integer.toString(getlocationBlockY) + Integer.toString(getlocationBlockZ);

							if (gethash.equals(hash)){

								if (getlocationBlockPlayerName.equals(playerName) || getlocationBlockEstado.equals("publico") || pLista.toString().contains(playerName)){

								}
								else if (getlocationBlockPlayerName.equals(null)) {

								} else {

									craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
									eventInteract.setCancelled(true);

								}
							}
							else {

								if (getlocationBlockPlayerName == null) {

									proteccionData.set(material + "." + hash + ".playerName", "Steve");
									proteccionData.set(material + "." + hash + ".X", locationBlockX);
									proteccionData.set(material + "." + hash + ".Y", locationBlockY);
									proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
									proteccionData.set(material + "." + hash + ".estado", "publico");


								}
								else {

									craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
									eventInteract.setCancelled(true);

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
		}


		if (eventInteract.getAction() == eventInteract.getAction().RIGHT_CLICK_BLOCK){

			if(craftPlayer.getItemInHand().getType().equals(Material.HOPPER_MINECART) || craftPlayer.getItemInHand().getType().equals(Material.DISPENSER)){

				eventInteract.setCancelled(true);

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

		//congelado

		if (!craftPlayer.hasPermission("bewom.admin") || !craftPlayer.hasPermission("bewom.mod")){
			if (playerIsCongelado || isCongelado){

				eventInteract.setCancelled(true);
				craftPlayer.sendMessage(ChatColor.RED + "Has sido congelado temporalmente.");
			}
		}		        
	}

	public static void actualizarEstado (FileConfiguration proteccionData, String material, String hash, String playerName, String playerUUID, String locationBlockX, String locationBlockY, String locationBlockZ){
		proteccionData.set(material + "." + hash + ".playerName", playerName);
		proteccionData.set(material + "." + hash + ".playerUUID", playerUUID);
		proteccionData.set(material + "." + hash + ".X", locationBlockX);
		proteccionData.set(material + "." + hash + ".Y", locationBlockY);
		proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
		proteccionData.set(material + "." + hash + ".estado", "privado");
		proteccionData.set(material + "." + hash + ".miembrosbool", false);
	}
}

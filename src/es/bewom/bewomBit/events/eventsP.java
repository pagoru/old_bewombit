package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
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
import org.bukkit.event.entity.PlayerDeathEvent;

public class eventsP {

	@SuppressWarnings("unused")
	public static void brokeBlockPlayerEventsP (BlockBreakEvent eventPlace){

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
}
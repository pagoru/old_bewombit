package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class brokeBlockPlayer implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@SuppressWarnings("unused")
	@EventHandler
	public void OnBreak(BlockBreakEvent eventPlace) throws SQLException, IOException {
		
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
						
						int hash = locationBlockX * 3 + locationBlockY * 2 + locationBlockZ *5;
						
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
		
		//congelar
		
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
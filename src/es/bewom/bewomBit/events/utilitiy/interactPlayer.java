package es.bewom.bewomBit.events.utilitiy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
							} else if (eventInteract.getClickedBlock().getType() == Material.HOPPER){
								material = "Hopper";
								nombreMaterial = "Este hopper";
							} else if (eventInteract.getClickedBlock().getType() == Material.TRAPPED_CHEST){
								material = "TrappedChest";
								nombreMaterial = "Este cofre trampa";
							} else if (eventInteract.getClickedBlock().getType() == Material.FURNACE){
								material = "Furnace";
								nombreMaterial = "Este horno";
							} else if (eventInteract.getClickedBlock().getType() == Material.ANVIL){
								material = "Anvil";
								nombreMaterial = "Este yunque";
							} else if (eventInteract.getClickedBlock().getType() == Material.DROPPER){
								material = "Dropper";
								nombreMaterial = "Este dropper";
							} else if (eventInteract.getClickedBlock().getType() == Material.JUKEBOX){
								material = "Jukebox";
								nombreMaterial = "Esta jukebox";
							} else if (eventInteract.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE){
								material = "EnchantmentTable";
								nombreMaterial = "Este mesa de encantamientos";
							} else if (eventInteract.getClickedBlock().getType() == Material.ENDER_CHEST){
								material = "EnderChest";
								nombreMaterial = "Este enderchest";
							} 
							
							
							getlocationBlockHash = proteccionData.getInt(material + "." + hash);
							getlocationBlockPlayerName = proteccionData.getString(material + "." + hash + ".playerName");
							getlocationBlockX = proteccionData.getInt(material + "." + hash + ".X");
							getlocationBlockY = proteccionData.getInt(material + "." + hash + ".Y");
							getlocationBlockZ = proteccionData.getInt(material + "." + hash + ".Z");
							getlocationBlockEstado = proteccionData.getString(material + "." + hash + ".estado");
							
							String gethash = Integer.toString(getlocationBlockX) + Integer.toString(getlocationBlockY) + Integer.toString(getlocationBlockZ);
							
							if (gethash.equals(hash)){
								
								if (getlocationBlockPlayerName.equals(playerName) || getlocationBlockEstado.equals("publico")){
									
								} else if (getlocationBlockPlayerName.equals(null)) {
									
								} else {
									
									craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
									eventInteract.setCancelled(true);
									
								}
								
							} else {
								
								if (getlocationBlockPlayerName == null) {
									
									proteccionData.set(material + "." + hash + ".playerName", "Steve");
									proteccionData.set(material + "." + hash + ".X", locationBlockX);
									proteccionData.set(material + "." + hash + ".Y", locationBlockY);
									proteccionData.set(material + "." + hash + ".Z", locationBlockZ);
									proteccionData.set(material + "." + hash + ".estado", "publico");
									
									
								} else {
									
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

}
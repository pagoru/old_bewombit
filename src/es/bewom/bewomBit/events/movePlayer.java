package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import es.bewom.bewomBit.bewomBit;

public class movePlayer implements Listener{

	public static bewomBit plugin;

	public movePlayer (bewomBit instance){
		plugin = instance;
	}

	@SuppressWarnings("unused")
	@EventHandler
	public void onPlayerMove (PlayerMoveEvent eventMove){
		
		Player craftPlayer = eventMove.getPlayer(); //craftPlayer Player
		String playerName = eventMove.getPlayer().getName(); //limpio String 
		String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player
		
		boolean playerIsCongelado = false;
		
		boolean isCongelado = false;
		
		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
		
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
		
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
	
				craftPlayer.teleport(craftPlayer);
				craftPlayer.sendMessage(ChatColor.RED + "Has sido congelado temporalmente.");
			}
		}
		
	}
}

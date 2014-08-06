package es.bewom.bewomBit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

public class connectPlayer implements Listener  {
	
	public static Scoreboard board;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException {
		
		Player craftPlayer = eventConnect.getPlayer(); //craftPlayer Player
		String playerName = eventConnect.getPlayer().getName(); //limpio String 
		String playerUUID = eventConnect.getPlayer().getUniqueId().toString(); //UUID Player
		
		
		eventConnect.setJoinMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha entrado en el servidor.");		
		
		// ---> Scoreboards nicks <--- //
		
		bewomBit.teamAdmin.removePlayer(craftPlayer);
		bewomBit.teamMod.removePlayer(craftPlayer);
		bewomBit.teamVip.removePlayer(craftPlayer);
		
		if (craftPlayer.hasPermission("bewom.admin")) {
			Bukkit.getLogger().info("player " + playerName +" is administrator");
			bewomBit.teamAdmin.addPlayer(craftPlayer);
			
			
		} else if (craftPlayer.hasPermission("bewom.mod")) {
			Bukkit.getLogger().info("player " + playerName +" is moderator.");
			bewomBit.teamMod.addPlayer(craftPlayer);
		
			
		} else if (craftPlayer.hasPermission("bewom.vip")) {
			Bukkit.getLogger().info("player "+ playerName +" is vip.");
			bewomBit.teamVip.addPlayer(craftPlayer);
			
			
		} else {
			
		}
		
		// ---> Perfiles Individuales <--- //	
		
		// Creacion de la carpeta individual 
		
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
		   
		try {
			userdata.mkdir();
			f.createNewFile();
		} catch (IOException e) {
		  
			e.printStackTrace();
		}
	  
		try {
			try {
				try {
					playerData.load(f);
					
					// Información que cargar/guardar para el jugador
					
					playerData.set("PlayerName", playerName);
					playerData.set("Chat", "global");
					playerData.save(f);
					
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

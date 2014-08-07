package es.bewom.bewomBit.events;

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
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class connectPlayer implements Listener  {
	
	private static Scoreboard board;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException {
		
		Player craftPlayer = eventConnect.getPlayer(); //craftPlayer Player
		String playerName = eventConnect.getPlayer().getName(); //limpio String 
		String playerUUID = eventConnect.getPlayer().getUniqueId().toString(); //UUID Player
		
		
		eventConnect.setJoinMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha entrado en el servidor.");		
		
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
					
					
					// ---> Scoreboards individuales <--- //
					
					ScoreboardManager manager = Bukkit.getScoreboardManager();
					board = manager.getMainScoreboard();
					
					Team teamUser = board.getTeam(playerName);
					
					if (teamUser == null) {
						
						if (craftPlayer.hasPermission("bewom.admin")) {
							
							teamUser = board.registerNewTeam(playerName);
							teamUser.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "");
							teamUser.setDisplayName(playerName);
							teamUser.addPlayer(craftPlayer);
							
						} else if (craftPlayer.hasPermission("bewom.mod")) {
							
							teamUser = board.registerNewTeam(playerName);
							teamUser.setPrefix(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "");
							teamUser.setDisplayName(playerName);
							teamUser.addPlayer(craftPlayer);
							
						} else if (craftPlayer.hasPermission("bewom.vip")) {
							
							teamUser = board.registerNewTeam(playerName);
							teamUser.setPrefix(ChatColor.DARK_AQUA + "" );
							teamUser.setDisplayName(playerName);
							teamUser.addPlayer(craftPlayer);
						
						}
						
					} else {
						teamUser.unregister();
						
						if (craftPlayer.hasPermission("bewom.admin")) {
							
							teamUser = board.registerNewTeam(playerName);
							teamUser.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "");
							teamUser.setDisplayName(playerName);
							teamUser.addPlayer(craftPlayer);
							
						} else if (craftPlayer.hasPermission("bewom.mod")) {
							
							teamUser = board.registerNewTeam(playerName);
							teamUser.setPrefix(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "");
							teamUser.setDisplayName(playerName);
							teamUser.addPlayer(craftPlayer);
							
						} else if (craftPlayer.hasPermission("bewom.vip")) {
							
							teamUser = board.registerNewTeam(playerName);
							teamUser.setPrefix(ChatColor.DARK_AQUA + "" );
							teamUser.setDisplayName(playerName);
							teamUser.addPlayer(craftPlayer);
						
						}
					}
					
					String lastMessage = playerData.getString("LastMessage");
					
					if(lastMessage == null){
						
						playerData.set("LastMessage", "Bienvenido");
						
					}

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

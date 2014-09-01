package es.bewom.bewomBit.groups.congelar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventsCongelar {
	
	public static void onPreprocessCommandEvent (PlayerCommandPreprocessEvent eventPreprocessCommand) throws SQLException, IOException, InvalidConfigurationException {
		
		String playerUUID = eventPreprocessCommand.getPlayer().getUniqueId().toString();
		Player craftPlayer = (Player) eventPreprocessCommand.getPlayer();
		
		boolean playerIsCongelado = false;
		boolean isCongelado = false;
		
		File data1 = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);	
										
		playerData.load(f);
		
		playerIsCongelado = playerData.getBoolean("Congelado");
		
		playerData.save(f);
		
		
		Data.load(data);
		
		isCongelado = Data.getBoolean("Congelado");
		
		Data.save(data);
							
		if (!craftPlayer.hasPermission("bewom.admin") || !craftPlayer.hasPermission("bewom.mod")){
			if (playerIsCongelado || isCongelado){
	
				eventPreprocessCommand.setCancelled(true);

			}
		}
				
	}
		
	@SuppressWarnings("unused")
	public static void onPlayerChatEventsCongelar(AsyncPlayerChatEvent eventChat) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		String message = eventChat.getMessage();
		
		String playerUUID = eventChat.getPlayer().getUniqueId().toString();
		String playerName = eventChat.getPlayer().getName();
		Player craftPlayer = (Player) eventChat.getPlayer();
		
		boolean playerIsCongelado = false;
		
		boolean isCongelado = false;
		
		File data1 = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
		
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
		playerData.load(f);

		playerData.set("LastMessage", message);
		
		playerIsCongelado = playerData.getBoolean("Congelado");
		
		playerData.save(f);
		
		
		Data.load(data);
		
		isCongelado = Data.getBoolean("Congelado");
		
		Data.save(data);
		
		if (!craftPlayer.hasPermission("bewom.admin") || !craftPlayer.hasPermission("bewom.mod")){
			if (playerIsCongelado || isCongelado){
				
				eventChat.setCancelled(true);
				
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void onPlayerInteract(PlayerInteractEvent eventInteract) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		String playerUUID = eventInteract.getPlayer().getUniqueId().toString();
		String playerName = eventInteract.getPlayer().getName();
		Player craftPlayer = (Player) eventInteract.getPlayer();
		
		boolean playerIsCongelado = false;
		boolean isCongelado = false;

		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
		
		File data1 = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

		playerData.load(f);
		playerIsCongelado = playerData.getBoolean("Congelado");
		playerData.save(f);


		Data.load(data);
		isCongelado = Data.getBoolean("Congelado");
		Data.save(data);
		
		if (!craftPlayer.hasPermission("bewom.admin") || !craftPlayer.hasPermission("bewom.mod")){
			if (playerIsCongelado || isCongelado){

				eventInteract.setCancelled(true);
				
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void movePlayerEventsCongelar (PlayerMoveEvent eventMove) throws FileNotFoundException, IOException, InvalidConfigurationException{
		Player craftPlayer = eventMove.getPlayer(); //craftPlayer Player
		String playerName = eventMove.getPlayer().getName(); //limpio String 
		String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player

		boolean playerIsCongelado = false;

		boolean isCongelado = false;

		File data1 = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

		playerData.load(f);

		playerIsCongelado = playerData.getBoolean("Congelado");

		playerData.save(f);


		Data.load(data);

		isCongelado = Data.getBoolean("Congelado");

		Data.save(data);

		if (!craftPlayer.hasPermission("bewom.admin") || !craftPlayer.hasPermission("bewom.mod")){
			if (playerIsCongelado || isCongelado){
				
				craftPlayer.teleport(craftPlayer);
				
			}
		}
	}
}

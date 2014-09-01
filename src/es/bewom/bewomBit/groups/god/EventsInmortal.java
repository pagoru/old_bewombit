package es.bewom.bewomBit.groups.god;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventsInmortal {
	
	public static void onDamageEntity(EntityDamageEvent eventDamage) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		 if(eventDamage.getEntity() instanceof Player){
			 
			Player craftPlayer = (Player) eventDamage.getEntity();
			 
			UUID playerUUID = craftPlayer.getUniqueId();
			 
			File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
			File f = new File(userdata, File.separator + playerUUID + ".yml");
			FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);	
											
			playerData.load(f);
			
			Boolean playerIsGod = playerData.getBoolean("Dios");
			
			playerData.save(f);
				
			if(playerIsGod == true){
				
				eventDamage.setCancelled(true);
				
				craftPlayer.setHealth(20);
				craftPlayer.setFoodLevel(20);	
				
			}
			 
		 }
		
	}
	
	public static void onPlayerMove (PlayerMoveEvent eventMove) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		String playerUUID = eventMove.getPlayer().getUniqueId().toString();
		Player craftPlayer = (Player) eventMove.getPlayer();
		
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
		playerData.load(f);
		
		Boolean playerIsGod = playerData.getBoolean("Dios");
		
		playerData.save(f);
		
		if(playerIsGod == true){
			
			craftPlayer.setHealth(20);
			craftPlayer.setFoodLevel(20);	
			
		}
		
	}
	
}

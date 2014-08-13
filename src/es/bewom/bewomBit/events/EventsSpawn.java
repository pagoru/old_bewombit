package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsSpawn {


	@EventHandler
	public static void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException {
		
		Player craftPlayer = eventConnect.getPlayer(); //craftPlayer Player
		
		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);


		try {
			try {
				try {
					Data.load(data);
					
					String spawn = Data.getString("Spawn");
					
					if(spawn != null){
						
						if(spawn.equals("permanente")){
							
							craftPlayer.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
							
						}
						
					}
					
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
		
	}
	
}

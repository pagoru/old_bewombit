package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsTeleport {
	
	@SuppressWarnings("unused")
	public static void playerInteractEventsP(PlayerInteractEvent eventInteract) throws FileNotFoundException, IOException, InvalidConfigurationException{

		String playerUUID = eventInteract.getPlayer().getUniqueId().toString();
		String playerName = eventInteract.getPlayer().getName();
		Player craftPlayer = (Player) eventInteract.getPlayer();
		
		int locationBlockX = 0;
		int locationBlockY = 0;
		int locationBlockZ = 0;
		
		if (eventInteract.getAction() == Action.RIGHT_CLICK_BLOCK || eventInteract.getAction() == Action.LEFT_CLICK_BLOCK){
						
			if(eventInteract.getClickedBlock().getType() == Material.WOODEN_DOOR){
				
				locationBlockX = eventInteract.getClickedBlock().getLocation().getBlockX();
				locationBlockY = eventInteract.getClickedBlock().getLocation().getBlockY();
				locationBlockZ = eventInteract.getClickedBlock().getLocation().getBlockZ();
				
				float YawFloat = craftPlayer.getLocation().getYaw();
				float PitchFloat = craftPlayer.getLocation().getPitch();
				
				String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
				String hashMinus = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
				
				File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
				File data = new File(data1, File.separator + "teleport.yml");
				FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
				
				Data.load(data);				
				
				if(Data.contains(hash) || Data.contains(hashMinus)){
					
					if(eventInteract.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){
						
						String nameDest = Data.getString(hash + ".Destino");
						
						String getHashDest = Data.getString("Names." + nameDest);
						
						double X = Data.getInt(getHashDest + ".X");
						double Y = Data.getInt(getHashDest + ".Y");
						double Z = Data.getInt(getHashDest + ".Z");
						String World = Data.getString(getHashDest + ".World");
						
						Location teleport = new Location(Bukkit.getWorld(World), X+0.5, Y, Z+0.5, YawFloat, PitchFloat);
						craftPlayer.teleport(teleport);
							
						eventInteract.setCancelled(true);
						
					} else if (eventInteract.getClickedBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){
						
						String nameDest = Data.getString(hashMinus + ".Destino");
						
						String getHashDest = Data.getString("Names." + nameDest);
						
						double X = Data.getInt(getHashDest + ".X");
						double Y = Data.getInt(getHashDest + ".Y");
						double Z = Data.getInt(getHashDest + ".Z");
						String World = Data.getString(getHashDest + ".World");
						
						Location teleport = new Location(Bukkit.getWorld(World), X+0.5, Y, Z+0.5, YawFloat, PitchFloat);
						craftPlayer.teleport(teleport);
							
						eventInteract.setCancelled(true);
						
					}
					
				} 
				
				Data.save(data);
				
			}
			
		}
		
	}
	
}

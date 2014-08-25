package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

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
	
	@EventHandler
	public static void onPlayerMove (PlayerMoveEvent eventMove) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		Player craftPlayer = (Player) eventMove.getPlayer();
		Block block = craftPlayer.getLocation().add(0, 1, 0).getBlock().getRelative(BlockFace.DOWN);
		
		if(block.getType() == Material.TRIPWIRE){
			
			int locationBlockX = craftPlayer.getLocation().getBlockX();
			int locationBlockY = craftPlayer.getLocation().getBlockY();
			int locationBlockZ = craftPlayer.getLocation().getBlockZ();
			
			float YawFloat = craftPlayer.getLocation().getYaw();
			float PitchFloat = craftPlayer.getLocation().getPitch();

			String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
			
			String hash1 = Integer.toString(locationBlockX+1) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
			String hash2 = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ+1);
			String hash3 = Integer.toString(locationBlockX-1) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
			String hash4 = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ-1);
			
			File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File data = new File(data1, File.separator + "teleport.yml");
			FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
			
			int sumaX = 0;
			int sumaZ = 0;
			
			Data.load(data);	
			
			if(Data.contains(hash)){
				
				String nameDest = Data.getString(hash + ".Destino");
				
				String getHashDest = Data.getString("Names." + nameDest);
				
				double X = Data.getInt(getHashDest + ".X");
				double Y = Data.getInt(getHashDest + ".Y");
				double Z = Data.getInt(getHashDest + ".Z");
				String World = Data.getString(getHashDest + ".World");
				String seeCardinal = Data.getString(getHashDest + ".Cardinal");
				
				if(seeCardinal.equals("N")){
					sumaX = 1;
				} else if(seeCardinal.equals("S")){
					sumaX = -1;
				} else if(seeCardinal.equals("E")){
					sumaZ = 1;
				} else if(seeCardinal.equals("W")){
					sumaZ = -1;
				}
								
				Location teleport = new Location(Bukkit.getWorld(World), X+0.5+(sumaX), Y, Z+0.5+(sumaZ), YawFloat, PitchFloat);
				craftPlayer.teleport(teleport);
				
			} else if(Data.contains(hash1)){
				
				String nameDest = Data.getString(hash1 + ".Destino");
				
				String getHashDest = Data.getString("Names." + nameDest);
				
				double X = Data.getInt(getHashDest + ".X");
				double Y = Data.getInt(getHashDest + ".Y");
				double Z = Data.getInt(getHashDest + ".Z");
				String World = Data.getString(getHashDest + ".World");
				String seeCardinal = Data.getString(getHashDest + ".Cardinal");
				
				if(seeCardinal.equals("N")){
					sumaX = 1;
				} else if(seeCardinal.equals("S")){
					sumaX = -1;
				} else if(seeCardinal.equals("E")){
					sumaZ = 1;
				} else if(seeCardinal.equals("W")){
					sumaZ = -1;
				}
				
				Location teleport = new Location(Bukkit.getWorld(World), X+0.5+(sumaX), Y, Z+0.5+(sumaZ), YawFloat, PitchFloat);
				craftPlayer.teleport(teleport);
				
			} else if(Data.contains(hash2)){
				
				String nameDest = Data.getString(hash2 + ".Destino");
				
				String getHashDest = Data.getString("Names." + nameDest);
				
				double X = Data.getInt(getHashDest + ".X");
				double Y = Data.getInt(getHashDest + ".Y");
				double Z = Data.getInt(getHashDest + ".Z");
				String World = Data.getString(getHashDest + ".World");
				String seeCardinal = Data.getString(getHashDest + ".Cardinal");
				
				if(seeCardinal.equals("N")){
					sumaX = 1;
				} else if(seeCardinal.equals("S")){
					sumaX = -1;
				} else if(seeCardinal.equals("E")){
					sumaZ = 1;
				} else if(seeCardinal.equals("W")){
					sumaZ = -1;
				}
				
				Location teleport = new Location(Bukkit.getWorld(World), X+0.5+(sumaX), Y, Z+0.5+(sumaZ), YawFloat, PitchFloat);
				craftPlayer.teleport(teleport);
				
			} else if(Data.contains(hash3)){
				
				String nameDest = Data.getString(hash3 + ".Destino");
				
				String getHashDest = Data.getString("Names." + nameDest);
				
				double X = Data.getInt(getHashDest + ".X");
				double Y = Data.getInt(getHashDest + ".Y");
				double Z = Data.getInt(getHashDest + ".Z");
				String World = Data.getString(getHashDest + ".World");
				String seeCardinal = Data.getString(getHashDest + ".Cardinal");
				
				if(seeCardinal.equals("N")){
					sumaX = 1;
				} else if(seeCardinal.equals("S")){
					sumaX = -1;
				} else if(seeCardinal.equals("E")){
					sumaZ = 1;
				} else if(seeCardinal.equals("W")){
					sumaZ = -1;
				}
				
				Location teleport = new Location(Bukkit.getWorld(World), X+0.5+(sumaX), Y, Z+0.5+(sumaZ), YawFloat, PitchFloat);
				craftPlayer.teleport(teleport);
				
			} else if(Data.contains(hash4)){
				
				String nameDest = Data.getString(hash4 + ".Destino");
				
				String getHashDest = Data.getString("Names." + nameDest);
				
				double X = Data.getInt(getHashDest + ".X");
				double Y = Data.getInt(getHashDest + ".Y");
				double Z = Data.getInt(getHashDest + ".Z");
				String World = Data.getString(getHashDest + ".World");
				String seeCardinal = Data.getString(getHashDest + ".Cardinal");
				
				if(seeCardinal.equals("N")){
					sumaX = 1;
				} else if(seeCardinal.equals("S")){
					sumaX = -1;
				} else if(seeCardinal.equals("E")){
					sumaZ = 1;
				} else if(seeCardinal.equals("W")){
					sumaZ = -1;
				}
				
				Location teleport = new Location(Bukkit.getWorld(World), X+0.5+(sumaX), Y, Z+0.5+(sumaZ), YawFloat, PitchFloat);
				craftPlayer.teleport(teleport);
				
			}
			
			
			Data.save(data);	
			
		}
		
	}
	
}

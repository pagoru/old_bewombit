package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
		
		File amigosdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File f1 = new File(amigosdata, File.separator + "amigos.yml");
		FileConfiguration amigosData = YamlConfiguration.loadConfiguration(f1);
		
		File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
		FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);
		
		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "teleport.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
		
		if (eventInteract.getAction() == Action.RIGHT_CLICK_BLOCK || eventInteract.getAction() == Action.LEFT_CLICK_BLOCK){
						
			if(eventInteract.getClickedBlock().getType() == Material.WOODEN_DOOR){
				
				locationBlockX = eventInteract.getClickedBlock().getLocation().getBlockX();
				locationBlockY = eventInteract.getClickedBlock().getLocation().getBlockY();
				locationBlockZ = eventInteract.getClickedBlock().getLocation().getBlockZ();
				
				float YawFloat = craftPlayer.getLocation().getYaw();
				float PitchFloat = craftPlayer.getLocation().getPitch();
				
				String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
				String hashMinus = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
								
				proteccionData.load(protecciondata);
				amigosData.load(f1);
				Data.load(data);
				
				String material = "WoodenDoor";
				
				String getlocationBlockPlayerName = proteccionData.getString(material + "." + hash + ".playerName");
				String getlocationBlockEstado = proteccionData.getString(material + "." + hash + ".estado");
				
				List<String> pListaP = amigosData.getStringList(playerName + ".amigos");
				List<String> pLista = proteccionData.getStringList(material + "." + hash + ".miembros");
				
				
				String getlocationBlockPlayerNameW = proteccionData.getString(material + "." + hashMinus + ".playerName");
				String getlocationBlockEstadoW = proteccionData.getString(material + "." + hashMinus + ".estado");
				
				List<String> pListaW = proteccionData.getStringList(material + "." + hashMinus + ".miembros");
				
				
				if(Data.contains(hash) || Data.contains(hashMinus)){
					
					if(eventInteract.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){
						
						String nameDest = Data.getString(hash + ".Destino");
						
						if (getlocationBlockPlayerName.equals(playerName) 
								|| getlocationBlockEstado.equals("publico") 
								|| pLista.toString().contains(playerName) 
								|| pListaP.contains(getlocationBlockPlayerName)){
							
							
							if(nameDest != null){
								
								String getHashDest = Data.getString("Names." + nameDest);
								
								double X = Data.getInt(getHashDest + ".X");
								double Y = Data.getInt(getHashDest + ".Y");
								double Z = Data.getInt(getHashDest + ".Z");
								String World = Data.getString(getHashDest + ".World");
								
								Location teleport = new Location(Bukkit.getWorld(World), X+0.5, Y, Z+0.5, YawFloat, PitchFloat);
								craftPlayer.teleport(teleport);
									
								eventInteract.setCancelled(true);
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + "La puerta no tiene destino.");
								eventInteract.setCancelled(true);
								
							}

						} else {

							eventInteract.setCancelled(true);

						}
						
					} else if (eventInteract.getClickedBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){
						
						String nameDest = Data.getString(hashMinus + ".Destino");
						
						if (getlocationBlockPlayerNameW.equals(playerName) 
								|| getlocationBlockEstadoW.equals("publico") 
								|| pListaW.toString().contains(playerName) 
								|| pListaP.contains(getlocationBlockPlayerName)){
						
							if(nameDest != null){
							
								String getHashDest = Data.getString("Names." + nameDest);
								
								double X = Data.getInt(getHashDest + ".X");
								double Y = Data.getInt(getHashDest + ".Y");
								double Z = Data.getInt(getHashDest + ".Z");
								String World = Data.getString(getHashDest + ".World");
								
								Location teleport = new Location(Bukkit.getWorld(World), X+0.5, Y, Z+0.5, YawFloat, PitchFloat);
								craftPlayer.teleport(teleport);
									
								eventInteract.setCancelled(true);
							
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + "La puerta no tiene destino.");
								eventInteract.setCancelled(true);
								
							}
						
						} else {

							eventInteract.setCancelled(true);

						}
						
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
				
				if(nameDest != null){
					
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
				
			} else if(Data.contains(hash1)){
				
				String nameDest = Data.getString(hash1 + ".Destino");
				
				if(nameDest != null){
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
				
			} else if(Data.contains(hash2)){
				
				String nameDest = Data.getString(hash2 + ".Destino");
				
				if(nameDest != null){
					
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
				
			} else if(Data.contains(hash3)){
				
				String nameDest = Data.getString(hash3 + ".Destino");
				
				if(nameDest != null){
					
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
					
			} else if(Data.contains(hash4)){
				
				String nameDest = Data.getString(hash4 + ".Destino");
				
				if(nameDest != null){
				
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
			}
			
			
			Data.save(data);	
			
		}
		
	}
	
}

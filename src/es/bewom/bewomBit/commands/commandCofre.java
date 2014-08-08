package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class commandCofre {
	
	@SuppressWarnings({ "deprecation", "unused" })
	public static boolean commandcofre(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("cofre")){
			
			Player craftPlayer = (Player) sender;
			String playerName = craftPlayer.getName();
			
			if (craftPlayer.getTargetBlock(null, 5).getType() == Material.CHEST){
				
				if (args.length == 0) {
					
				} else if (args.length == 1){
					
					int locationBlockX = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockX();
					int locationBlockY = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockY();
					int locationBlockZ = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockZ();
					
					int hash = locationBlockX * 3 + locationBlockY * 2 + locationBlockZ *5;
					
					File cofredata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
					File cofredata = new File(cofredata1, File.separator + "cofres.yml");
					FileConfiguration cofreData = YamlConfiguration.loadConfiguration(cofredata);
					
					try {
						try {
							try {
								cofreData.load(cofredata);
								
								int getlocationBlockHash = 0;
								String getlocationBlockPlayerName = null;
								String getlocationBlockPlayerUUID = null;
								int getlocationBlockX = 0;
								int getlocationBlockY = 0;
								int getlocationBlockZ = 0;
								String getlocationBlockEstado = null;
								
								int gethash = 0;
								
								getlocationBlockHash = cofreData.getInt("Chests." + hash);
								getlocationBlockPlayerName = cofreData.getString("Chests." + hash + ".playerName");
								getlocationBlockPlayerUUID = cofreData.getString("Chests." + hash + ".playerUUID");
								getlocationBlockX = cofreData.getInt("Chests." + hash + ".X");
								getlocationBlockY = cofreData.getInt("Chests." + hash + ".Y");
								getlocationBlockZ = cofreData.getInt("Chests." + hash + ".Z");
								getlocationBlockEstado = cofreData.getString("Chests."+ hash + ".estado");
								
								gethash = getlocationBlockX * 3 + getlocationBlockY * 2 + getlocationBlockZ *5;
								
								if (gethash == hash){
									
									if (getlocationBlockPlayerName.equals(playerName)){
										
										if (args[0].equals("privado")){
											
											cofreData.set("Chests."+ hash + ".estado", "privado");
											
										} else  if (args[0].equals("publico")) {
											
											cofreData.set("Chests."+ hash + ".estado", "publico");
											
										}
										
									} else {
										
										craftPlayer.sendMessage("No tienes permiso para modificar este cofre");
										
									}
									
								}
								
								
								cofreData.save(cofredata);
					
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
					
				return true;
			}
			
			
		}
		return false;
		
		
	
	}
	
	
	
}



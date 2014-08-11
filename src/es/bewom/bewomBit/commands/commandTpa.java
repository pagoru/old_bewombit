package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class commandTpa implements Listener {

	@SuppressWarnings({ "deprecation", "unused" })
	public static boolean commandtpa(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("tpa")){
			
			Player craftPlayer = (Player) sender;
			String playerName = craftPlayer.getName();
			UUID playerUUID = craftPlayer.getUniqueId();
			String playerUUIDString = craftPlayer.getUniqueId().toString(); //UUID Player
			
			Player craftPlayerArgs = null;
			String playerUUIDArgs = null; //UUID Player
			
			String tpa = null;
			
			if (args.length == 1){
				
				if (Bukkit.getServer().getPlayer(args[0]) != null){
					
					Location locationPlayer = craftPlayer.getLocation();
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					playerUUIDArgs = craftPlayerArgs.getUniqueId().toString();
					
					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUIDArgs + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
					try {
						try {
							try {
								playerData.load(f);
								
								playerData.set("Tpa", playerName);
								
								craftPlayerArgs.sendMessage(ChatColor.GRAY + "El usuario " + playerName + " quiere que vengas donde esta el.");
								craftPlayerArgs.sendMessage(ChatColor.GRAY + "Si aceptas, escribe en el chat " + ChatColor.RED + "/tpa aceptar" + ChatColor.GRAY + ".");
								craftPlayerArgs.sendMessage(ChatColor.GRAY + "Si no quieres, escribe en el chat " + ChatColor.RED + "/tpa denegar" + ChatColor.GRAY + ".");
								
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
					
				} else if (args[0].equals("aceptar")){
					
					Location locationPlayer = craftPlayer.getLocation();
					
					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUID + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
					try {
						try {
							try {
								playerData.load(f);
								
								String playerNameTpa = playerData.getString("Tpa");
								
								if(playerNameTpa != null){
									if (craftPlayer.getServer().getPlayer(playerNameTpa) != null){
										
										Player playerCraftTpa = craftPlayer.getServer().getPlayer(playerNameTpa);
										
										craftPlayer.teleport(playerCraftTpa);
										
										craftPlayer.sendMessage(ChatColor.GRAY + "Te has teletransportado con exito a " + playerNameTpa + ".");
										
										playerData.set("Tpa", null);
										
									} else {
	
										craftPlayer.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
										
										playerData.set("Tpa", null);
	
									}
									
								}  else {
									
									craftPlayer.sendMessage(ChatColor.RED + "No puedes aceptar un tpa de 'nadie'.");
									
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
					
				} else if (args[0].equals("denegar")){
					
					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUID + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
					try {
						try {
							try {
								playerData.load(f);
								
								String playerNameTpa = playerData.getString("Tpa");
								
								if(playerNameTpa != null){
									if (craftPlayer.getServer().getPlayer(playerNameTpa) != null){
										
										craftPlayer.sendMessage(ChatColor.RED + "Has denegado el tpa.");
										
										playerData.set("Tpa", null);
										
									} else {
	
										craftPlayer.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
										
										playerData.set("Tpa", null);
	
									}
									
								}  else {
									
									craftPlayer.sendMessage(ChatColor.RED + "No puedes denegar un tpa de 'nadie'.");
									
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

				} else {
					
					craftPlayer.sendMessage(ChatColor.RED + "No se entiende el comando.");
					
				}
			
			}
			
			return true;		
		}
		
		return false;
	}

}
package es.bewom.bewomBit.groups.spawn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandSpawn {
	
	public static boolean commandspawn(CommandSender sender, Command cmd, String label, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		if (label.equalsIgnoreCase("spawn")){
			
			Player craftPlayer = ((OfflinePlayer) sender).getPlayer(); //craftPlayer Player
			
			if(args.length == 0){
				
				craftPlayer.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
				
			} else if (args.length == 1){
				
				if(args[0].equals("set")){
					
					int playerX = craftPlayer.getLocation().getBlockX();
					int playerY = craftPlayer.getLocation().getBlockY();
					int playerZ = craftPlayer.getLocation().getBlockZ();
					
					Bukkit.getServer().getWorld(craftPlayer.getWorld().getName()).setSpawnLocation(playerX, playerY, playerZ);
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Ha sido establecido el lugar de spawn!");
				} 
				
			} else if (args.length == 2){
				
				if(args[0].equals("set")){
					
					File data1 = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
					File data = new File(data1, File.separator + "config.yml");
					FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

					Data.load(data);
		
					if(args[1].equals("permanente")){
									
						Data.set("Spawn", "permanente");
						
						craftPlayer.sendMessage(ChatColor.GRAY + "El lugar de spawn ahora esta de forma permanente!");
						
					} else if (args[1].equals("dinamico")){
						
						Data.set("Spawn", null);
						
						craftPlayer.sendMessage(ChatColor.GRAY + "El lugar de spawn ahora ya no esta de forma permanente!");
						
					}
					
					Data.save(data);
					
							
					
				}
				
			}
			
		}
		
		return false;
		
	}

}

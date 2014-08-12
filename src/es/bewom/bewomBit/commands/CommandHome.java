package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandHome implements Listener {

	public static boolean commandhome(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("home")){
			
			Player craftPlayer = (Player) sender;
			UUID playerUUID = craftPlayer.getUniqueId();
						
			if (args.length == 1){
				
				File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
				File f = new File(userdata, File.separator + playerUUID + ".yml");
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
				
				try {
					try {
						try {
							playerData.load(f);
							
							String world = playerData.getString("Homes." + args[0] + ".World");
							int x = playerData.getInt("Homes." + args[0] + ".X");
							int y = playerData.getInt("Homes." + args[0] + ".Y");
							int z = playerData.getInt("Homes." + args[0] + ".Z");
							
							if (world != null){
								
								Location teleport = new Location(Bukkit.getWorld(world), x, y, z);
								craftPlayer.teleport(teleport);
							
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
			
			return true;
		}
		return false;
	}
}
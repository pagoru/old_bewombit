package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandSetHome implements Listener {

	public static boolean commandsethome(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("sethome")){
			
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
							
							playerData.set("Homes", args[0]);
							playerData.set("Homes." + args[0] + ".World", craftPlayer.getWorld().getName());
							playerData.set("Homes." + args[0] + ".X", craftPlayer.getLocation().getBlockX());
							playerData.set("Homes." + args[0] + ".Y", craftPlayer.getLocation().getBlockY());
							playerData.set("Homes." + args[0] + ".Z", craftPlayer.getLocation().getBlockZ());
							
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

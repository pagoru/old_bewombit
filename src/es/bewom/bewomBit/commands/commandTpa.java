package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
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
		
		Player craftPlayer = (Player) sender;
		String playerName = craftPlayer.getName();
		String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player
		
		Player craftPlayerArgs = null;
		String playerUUIDArgs = null; //UUID Player
		
		String tpa = null;
		

		
		if (args.length == 1){
			
			if (craftPlayer.getServer().getPlayer(args[0]) != null){
				
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
		
		}
					

		
		return false;
	}

}
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

	public static boolean commandhome(CommandSender sender, Command cmd, String label, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		if (label.equalsIgnoreCase("home")){
			
			Player craftPlayer = (Player) sender;
			UUID playerUUID = craftPlayer.getUniqueId();
						
			if (args.length == 1){
				
				File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
				File f = new File(userdata, File.separator + playerUUID + ".yml");
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

				playerData.load(f);
				
				String world = playerData.getString("Homes." + args[0] + ".World");
				double x = playerData.getDouble("Homes." + args[0] + ".X");
				double y = playerData.getDouble("Homes." + args[0] + ".Y");
				double z = playerData.getDouble("Homes." + args[0] + ".Z");
				double Pitch = playerData.getDouble("Homes." + args[0] + ".Pitch");
				double Yaw = playerData.getDouble("Homes." + args[0] + ".Yaw");
				
				float PitchFloat = (float) Pitch;
				float YawFloat = (float) Yaw;
				
				if (world != null){
					
					Location teleport = new Location(Bukkit.getWorld(world), x, y, z, YawFloat, PitchFloat);
					craftPlayer.teleport(teleport);
				
				}
				
				playerData.save(f);
	
			}
			
			return true;
		}
		return false;
	}
}
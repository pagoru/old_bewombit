package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.utility.PlayerUtility;

public class CommandTeleport {
	
	@SuppressWarnings("deprecation")
	public static boolean commandteleport(CommandSender sender, Command cmd, String label, String[] args) throws IOException, InvalidConfigurationException{

		if (label.equalsIgnoreCase("teleport")){
			
			Player craftPlayer = (Player) sender;	
			
			int locationBlockX = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockX();
			int locationBlockY = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockY();
			int locationBlockZ = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockZ();

			String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
			String hashMinus = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
			
			String seeCardinal = PlayerUtility.getCardinalDirection(craftPlayer);
				
			File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File data = new File(data1, File.separator + "teleport.yml");
			FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
			
			Data.load(data);
			
			if(args.length == 2){

				if (craftPlayer.getTargetBlock(null, 5).getType() == Material.WOODEN_DOOR){
				
					if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){
						
						String exNames = Data.getString("Names." + args[0]);
						
						if(exNames != null){
							
							Data.set(exNames, null);
							
						}
						
						Data.set("Names." + args[0], hash);
						
						Data.set(hash + ".Name", args[0]); 
						Data.set(hash + ".Destino", args[1]);
						
						Data.set(hash + ".X", locationBlockX);
						Data.set(hash + ".Y", locationBlockY);
						Data.set(hash + ".Z", locationBlockZ);
						Data.set(hash + ".World", craftPlayer.getWorld().getName());
						Data.set(hash + ".Cardinal", seeCardinal);
						
						craftPlayer.sendMessage(" > "+ args[0] + "-" + args[1]);
						
					} else if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){
						
						String exNames = Data.getString("Names." + args[0]);
						
						if(exNames != null){
							
							Data.set(exNames, null);
							
						}
						
						Data.set("Names." + args[0], hashMinus);
						
						Data.set(hashMinus + ".Name", args[0]);
						Data.set(hashMinus + ".Destino", args[1]);
						
						Data.set(hashMinus + ".X", locationBlockX);
						Data.set(hashMinus + ".Y", locationBlockY-1);
						Data.set(hashMinus + ".Z", locationBlockZ);
						Data.set(hashMinus + ".World", craftPlayer.getWorld().getName());
						Data.set(hash + ".Cardinal", seeCardinal);
						
						craftPlayer.sendMessage(" > "+ args[0] + "-" + args[1]);
						
					}
				
				}
				
				if(craftPlayer.getTargetBlock(null, 5).getType() == Material.TRIPWIRE){
					
					String exNames = Data.getString("Names." + args[0]);
					
					if(exNames != null){
						
						Data.set(exNames, null);
						
					}
					
					Data.set("Names." + args[0], hash);
					
					Data.set(hash + ".Name", args[0]); 
					Data.set(hash + ".Destino", args[1]);
					
					Data.set(hash + ".X", locationBlockX);
					Data.set(hash + ".Y", locationBlockY);
					Data.set(hash + ".Z", locationBlockZ);
					Data.set(hash + ".World", craftPlayer.getWorld().getName());
					Data.set(hash + ".Cardinal", seeCardinal);
					
					craftPlayer.sendMessage(" > trip "+ args[0] + "-" + args[1]);
					
				}
			}
					
			Data.save(data);
			
		}

		return false;
	}
}

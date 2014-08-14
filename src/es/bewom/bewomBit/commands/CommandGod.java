package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandGod {
	
	@SuppressWarnings("deprecation")
	public static boolean commandgod(CommandSender sender, Command cmd, String label, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException{

		if (label.equalsIgnoreCase("god")){
			
			Player craftPlayer = (Player) sender;
			UUID playerUUID = craftPlayer.getUniqueId();
			
			if(args.length == 0){
				
				File data = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
				File play = new File(data, File.separator + playerUUID + ".yml");
				FileConfiguration Data = YamlConfiguration.loadConfiguration(play);
				
				Data.load(play);
				
				Boolean dios = Data.getBoolean("Dios");
					
				if(dios == false){
					
					Data.set("Dios", true);
					sender.sendMessage(ChatColor.GRAY + "Modo Dios activado.");
					
				} else {
					
					Data.set("Dios", false);
					sender.sendMessage(ChatColor.RED + "Modo Dios desactivado.");
					
				}
				
				Data.save(play);
				
			} else if (args.length == 1){
				
				if (sender.getServer().getPlayer(args[0]) != null){
					
					UUID argUUID = sender.getServer().getPlayer(args[0]).getUniqueId();
					
					File argsdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File arg = new File(argsdata, File.separator + argUUID + ".yml");
					FileConfiguration argData = YamlConfiguration.loadConfiguration(arg);
					
					argData.load(arg);
					
					Boolean dios = argData.getBoolean("Dios");
						
					if(dios == false){
						
						argData.set("Dios", true);
						sender.sendMessage(ChatColor.GRAY + "Modo Dios activado para " + args[0] + ".");
					
					} else {
						
						argData.set("Dios", false);
						sender.sendMessage(ChatColor.RED + "Modo Dios desactivado para " + args[0] + ".");
						
					}
					
					argData.save(arg);
					
				}
				
			}
			return true;
			
		}
		return false;
	}
}
package es.bewom.bewomBit.groups.home;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandDelHome {

	public static boolean commanddelhome(CommandSender sender, Command cmd, String label, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		if (label.equalsIgnoreCase("delhome")){
			
			Player craftPlayer = (Player) sender;
			UUID playerUUID = craftPlayer.getUniqueId();
						
			if (args.length == 1){
				
				File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
				File f = new File(userdata, File.separator + playerUUID + ".yml");
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
				
				playerData.load(f);
											
				List<String> pList = new ArrayList<String>();
				List<String> pLista = playerData.getStringList("Homes.List");
				
				if (pLista.contains(args[0])){
					
					pLista.remove(args[0]);
					pList.addAll(pLista);
					
					playerData.set("Homes." + args[0], null);
					playerData.set("Homes.List", pList);
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Ha sido eliminada " + args[0] + " de tu lista de homes.");
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.RED + "No tienes un home con este nombre.");
					
				}
											
				playerData.save(f);
	
			} else {
				
				craftPlayer.sendMessage(ChatColor.RED + "La forma correcta es /delhome [nombre casa].");
				
			}
			
			return true;
		}
		return false;
	}
	
}
package es.bewom.bewomBit.groups.home;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.bukkit.event.Listener;

public class CommandSetHome implements Listener {

	public static boolean commandsethome(CommandSender sender, Command cmd, String label, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		if (label.equalsIgnoreCase("sethome")){
			
			Player craftPlayer = (Player) sender;
			UUID playerUUID = craftPlayer.getUniqueId();
						
			if (args.length == 1){
				
				File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
				File f = new File(userdata, File.separator + playerUUID + ".yml");
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

				playerData.load(f);
				
				List<String> pLista = playerData.getStringList("Homes.List");
				
				if (!pLista.contains(args[0])){
					
					pLista.add(args[0]);
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Has a�adido " + args[0] + " a tu lista de homes.");
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Has sustituido la localizaci�n de " + args[0] + " de tu lista de homes.");
					
				}
				
				playerData.set("Homes.List", pLista);
				
				playerData.set("Homes." + args[0] + ".World", craftPlayer.getWorld().getName());
				playerData.set("Homes." + args[0] + ".X", craftPlayer.getLocation().getX());
				playerData.set("Homes." + args[0] + ".Y", craftPlayer.getLocation().getY());
				playerData.set("Homes." + args[0] + ".Z", craftPlayer.getLocation().getZ());
				playerData.set("Homes." + args[0] + ".Pitch", craftPlayer.getLocation().getPitch());
				playerData.set("Homes." + args[0] + ".Yaw", craftPlayer.getLocation().getYaw());
				
				playerData.save(f);

			} else {
				
				craftPlayer.sendMessage(ChatColor.RED + "La forma correcta es /sethome [nombre casa].");
				
			}
			
			return true;
		}
		return false;
	}
}

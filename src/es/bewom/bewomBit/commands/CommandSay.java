package es.bewom.bewomBit.commands;

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

public class CommandSay {

	public static boolean commandsay(CommandSender sender, Command cmd, String label, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException{

		if (label.equalsIgnoreCase("say")){
			if (args.length >= 1){
				
				String broadcast = ChatColor.DARK_GREEN + "/"+ ChatColor.MAGIC + "b" + ChatColor.DARK_GREEN + "/"+  ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "WOM" + ChatColor.DARK_GREEN + " < ";
				String texto = "";
				for (int i = 0; i < args.length; i++) {
					texto += args[i] + " ";
				}
				Bukkit.getServer().broadcastMessage(broadcast + ChatColor.GREEN + texto);
			
			} else {
				
				Player craftPlayer = ((OfflinePlayer) sender).getPlayer(); //craftPlayer Player
				String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player
				
				File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
				File f = new File(userdata, File.separator + playerUUID + ".yml");
				FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

				playerData.load(f);
				
				String getPlayerChat = playerData.getString("Chat");
				
				if(getPlayerChat.equals("global")){
					
					playerData.set("Chat", "say");
					
				} else if (getPlayerChat.equals("say")){
					
					playerData.set("Chat", "global");
					
				}
				
				playerData.save(f);

	
			}
			return true;
		}
		return false;
	}
}
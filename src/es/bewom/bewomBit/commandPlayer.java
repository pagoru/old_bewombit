package es.bewom.bewomBit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandPlayer implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		Player craftPlayer = (Player) sender; //craftPlayer Player
		String playerName = sender.getName(); //limpio String
		
		
		// ---> say <--- //
		
		String broadcast = ChatColor.DARK_GREEN + ": " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "WOM" + ChatColor.DARK_GREEN + " broadcast < ";
		
		if (craftPlayer.hasPermission("bewom.admin")){
			if (label.equalsIgnoreCase("say")){
	            String texto = "";
	            for (int i = 0; i < args.length; i++) {
	                    texto += args[i] + " ";
	            }
				Bukkit.getServer().broadcastMessage(broadcast + ChatColor.GREEN + texto);
				return true;
			}
		}
		
		// ---> tphere <--- //
		
		if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){
			if (label.equalsIgnoreCase("tphere")){
				if (args.length == 1){
					Location locationPlayer = craftPlayer.getLocation();
					
					
				}
			}
		}
			
		return false;
		
	}

}

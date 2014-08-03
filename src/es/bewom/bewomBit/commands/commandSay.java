package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class commandSay {
	
	public static boolean commandsay(CommandSender sender, Command cmd, String label, String[] args){
		String broadcast = ChatColor.DARK_GREEN + ": " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "WOM" + ChatColor.DARK_GREEN + " broadcast < ";
		if (label.equalsIgnoreCase("say")){
			String texto = "";
			for (int i = 0; i < args.length; i++) {
				texto += args[i] + " ";
			}

			Bukkit.getServer().broadcastMessage(broadcast + ChatColor.GREEN + texto);
			return true;
		}
		return false;
	}
}


package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import es.bewom.bewomBit.commands.utility.commandUtilities;

public class commandSay {

	public static boolean commandsay(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("say")){
			if (args.length >= 1){
				String broadcast = ChatColor.DARK_GREEN + "/"+ ChatColor.MAGIC + "b" + ChatColor.DARK_GREEN + "/"+  ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "WOM" + ChatColor.DARK_GREEN + " < ";
				String texto = "";
				for (int i = 0; i < args.length; i++) {
					texto += args[i] + " ";
				}
				Bukkit.getServer().broadcastMessage(broadcast + ChatColor.GREEN + texto);
			}
			else {
				commandUtilities.formaCorrecta(sender, "/say <mensaje>");
			}
			return true;
		}
		return false;
	}
}


package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandKick {

	@SuppressWarnings("deprecation")
	public static boolean commandkick(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("kick")){
			Player craftPlayerArgs;
			String broadcast = ChatColor.DARK_BLUE + ": " + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "WOM" + ChatColor.DARK_BLUE + " < ";

			if (args.length == 1) {
				if (sender.getServer().getPlayer(args[0]) != null){

					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					String playerArgsName = craftPlayerArgs.getName();

					craftPlayerArgs.kickPlayer("");

					Bukkit.getServer().broadcastMessage(broadcast + ChatColor.BLUE + "El jugador " + playerArgsName + " ha sido kickeado.");

				} else {

					sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
					return true;
				}

			} else if (args.length > 1){
				if (sender.getServer().getPlayer(args[0]) != null){

					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					String playerArgsName = craftPlayerArgs.getName();

					String texto = "";
					for (int i = 1; i < args.length; i++) {
						texto += args[i] + " ";
					}

					craftPlayerArgs.kickPlayer("");

					Bukkit.getServer().broadcastMessage(broadcast + ChatColor.BLUE + "El jugador " + playerArgsName + " ha sido kickeado por " + texto);
				} else {

					sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "La forma correcta es /kick <player> [motivo]");
				return true;
			}
		} 
		return false;
	}
}

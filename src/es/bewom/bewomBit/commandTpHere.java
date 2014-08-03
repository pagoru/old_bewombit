package es.bewom.bewomBit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandTpHere {
	
	@SuppressWarnings("deprecation")
	public static boolean commandTpHere (CommandSender sender, Command cmd, String label, String[] args){
		Player craftPlayer = (Player) sender;
		Player craftPlayerArgs;

		if (label.equalsIgnoreCase("tphere")){

			if (args.length == 1){
				if (craftPlayer.getServer().getPlayer(args[0]) != null){

					Location locationPlayer = craftPlayer.getLocation();
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					craftPlayerArgs.teleport(locationPlayer);
					String playerArgsName = craftPlayerArgs.getName();

					craftPlayer.sendMessage(ChatColor.GRAY + "El jugador " + playerArgsName + " se ha tepeado a ti.");

					return true;

				} else {
					craftPlayer.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "La forma correcta es /tphere <player>");
				return true;
			}
		}
		return false;
	}
}

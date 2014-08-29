package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.utility.UUIDFetcher;

public class CommandTpHere {
	
	public static boolean commandtphere(CommandSender sender, Command cmd, String label, String[] args) throws Exception{
		if (label.equalsIgnoreCase("tphere")){
			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;

			if (args.length == 1){
				if (craftPlayer.getServer().getPlayer(UUIDFetcher.getUUIDOf(args[0])) != null){

					Location locationPlayer = craftPlayer.getLocation();
					craftPlayerArgs = Bukkit.getServer().getPlayer(UUIDFetcher.getUUIDOf(args[0]));
					craftPlayerArgs.teleport(locationPlayer);
					String playerArgsName = craftPlayerArgs.getName();

					craftPlayer.sendMessage(ChatColor.GRAY + "El jugador " + playerArgsName + " se ha tepeado a ti.");

				} else {
					craftPlayer.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
					
				}
			} else {
				sender.sendMessage(ChatColor.RED + "La forma correcta es /tphere <player>");
				
			}
			return true;
		}
		return false;
	}
}
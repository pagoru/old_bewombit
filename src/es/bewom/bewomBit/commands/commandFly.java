package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandFly {

	@SuppressWarnings("deprecation")
	public static boolean commandfly (CommandSender sender, Command cmd, String label, String[] args){
		if (label.equalsIgnoreCase("fly")){
			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;

			if (args.length == 0){

				if (!craftPlayer.getAllowFlight()){
					craftPlayer.setAllowFlight(true);
					craftPlayer.sendMessage(ChatColor.GRAY + "Modo de vuelo activado!");
					return true;
				}
				else{
					craftPlayer.setAllowFlight(false);
					craftPlayer.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado!");
					return true;
				}

			}
			else {
				if (args.length == 1){

					if (sender.getServer().getPlayer(args [0]) != null){
						craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
						if (!craftPlayerArgs.getAllowFlight()){
							craftPlayerArgs.setAllowFlight(true);
							if (craftPlayer.getName() != craftPlayerArgs.getName()){
								craftPlayer.sendMessage(ChatColor.GRAY + "Modo de vuelo activado para "+craftPlayerArgs.getName()+"!");
							}
							craftPlayerArgs.sendMessage(ChatColor.GRAY + "Modo de vuelo activado!");
							return true;
						}
						else{
							craftPlayerArgs.setAllowFlight(false);
							if (craftPlayer.getName() != craftPlayerArgs.getName()){
								craftPlayer.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado para "+craftPlayerArgs.getName()+"!");
							}
							craftPlayerArgs.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado!");
							return true;
						}
					}
					sender.sendMessage(ChatColor.RED + "El jugador no esta conectado!");
					return true;
				}
				else {
					sender.sendMessage(ChatColor.RED + "La forma correcta es /fly [player]");
					return true;
				}
			}
		}
		return false;
	}
}

package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.commands.utility.commandUtilities;

public class commandFly {

	@SuppressWarnings("deprecation")
	public static boolean commandfly (CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("fly")){
			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;

			if (args.length == 0){
				cambiarModoDeVuelo (craftPlayer);
			}
			else {
				if (args.length == 1){

					if (sender.getServer().getPlayer(args [0]) != null){
						craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
						cambiarModoDeVuelo (craftPlayer, craftPlayerArgs);
					}
					commandUtilities.jugadorDesconectado(sender);

				}
				else {
					commandUtilities.formaCorrecta(sender, "/fly [player]");
				}
			}
			return true;
		}
		return false;
	}

	public static void cambiarModoDeVuelo (Player player){
		if (!player.getAllowFlight()){
			player.setAllowFlight(true);
			player.sendMessage(ChatColor.GRAY + "Modo de vuelo activado!");
		}
		else {
			player.setAllowFlight(false);
			player.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado!");
		}
	}

	public static void cambiarModoDeVuelo (Player player, Player playerArgs){
		if (!playerArgs.getAllowFlight()){
			playerArgs.setAllowFlight(true);
			if (player.getName() != playerArgs.getName()){
				player.sendMessage(ChatColor.GRAY + "Modo de vuelo activado para "+playerArgs.getName()+"!");
			}
			playerArgs.sendMessage(ChatColor.GRAY + "Modo de vuelo activado!");

		}
		else{
			playerArgs.setAllowFlight(false);
			if (player.getName() != playerArgs.getName()){
				player.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado para "+playerArgs.getName()+"!");
			}
			playerArgs.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado!");
		}
	}
}
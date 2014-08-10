package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandKill {

	@SuppressWarnings("deprecation")
	public static boolean commandkill(CommandSender sender, Command cmd, String label, String [] args){

		if (label.equalsIgnoreCase("kill")){
			
			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;

			if (args.length == 1) {

				if (sender.getServer().getPlayer(args [0]) != null){

					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					int nivel = craftPlayerArgs.getLevel();
					craftPlayerArgs.setHealth(0);
					String playerArgsName = craftPlayerArgs.getName();
					
					switch (nivel){

					case 0:
						craftPlayer.sendMessage(ChatColor.GRAY + "Has matado a " + playerArgsName + ", no tenia niveles.");
						break;
					case 1:
						craftPlayer.sendMessage(ChatColor.GRAY + "Has matado a " + playerArgsName + ", tenia " + nivel + " nivel.");
						break;
					default:
						craftPlayer.sendMessage(ChatColor.GRAY + "Has matado a " + playerArgsName + ", tenia " + nivel + " niveles.");
						break;
					}

				} else {
					
					craftPlayer.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
					
				}

			} else {
				
				sender.sendMessage(ChatColor.RED + "La forma correcta es /kill <player>");
				
			}

		}
		return false;
	}


}

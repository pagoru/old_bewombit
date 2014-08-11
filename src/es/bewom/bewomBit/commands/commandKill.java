package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.commands.utility.commandUtilities;

public class commandKill {

	@SuppressWarnings("deprecation")
	public static boolean commandkill(CommandSender sender, Command cmd, String label, String [] args){

		if (label.equalsIgnoreCase("kill")){

			if (args.length == 1) {

				if (commandUtilities.comprobarJugador(sender, args [0])){

					Player craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					int nivel = craftPlayerArgs.getLevel();
					craftPlayerArgs.setHealth(0);
					mostrarExperiencia (sender, craftPlayerArgs, nivel);
				}
				else {
					commandUtilities.jugadorDesconectado(sender);
				}

			} else {
				commandUtilities.formaCorrecta(sender, "/kill <player>");
			}
			return true;
		}
		return false;
	}
	
	public static void mostrarExperiencia (CommandSender sender, Player playerArgs, int nivel){
		switch (nivel){

		case 0:
			sender.sendMessage(ChatColor.GRAY + "Has matado a " + playerArgs.getName() + ", no tenia niveles.");
			break;
		case 1:
			sender.sendMessage(ChatColor.GRAY + "Has matado a " + playerArgs.getName() + ", tenia " + nivel + " nivel.");
			break;
		default:
			sender.sendMessage(ChatColor.GRAY + "Has matado a " + playerArgs.getName() + ", tenia " + nivel + " niveles.");
			break;
		}
	}
}
package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandKick {

	@SuppressWarnings("deprecation")
	public static boolean commandkick(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("kick")){

			Player craftPlayerArgs;
			String broadcast = ChatColor.DARK_BLUE + "/"+ ChatColor.MAGIC + "b" + ChatColor.DARK_BLUE + "/" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "WOM" + ChatColor.DARK_BLUE + " < ";

			if (args.length == 1) {

				if (CommandUtilities.comprobarJugador(sender, args [0])){

					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);

					craftPlayerArgs.kickPlayer (ChatColor.BLUE + "Has sido kickeado.");
					Bukkit.getServer().broadcastMessage(broadcast + ChatColor.BLUE + "El jugador " + craftPlayerArgs.getName() + " ha sido kickeado.");

				}
				else {	
					CommandUtilities.jugadorDesconectado(sender);
				}
			}

			else if (args.length > 1){

				if (CommandUtilities.comprobarJugador(sender, args [0])){

					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					String texto = "";

					for (int i = 1; i < args.length; i++) {
						texto += args[i] + " ";
					}
					craftPlayerArgs.kickPlayer(ChatColor.BLUE + "Has sido kickeado por " + texto);
					Bukkit.getServer().broadcastMessage(broadcast + ChatColor.BLUE + "El jugador " + craftPlayerArgs.getName() + " ha sido kickeado por " + texto);
				}
				else{
					CommandUtilities.jugadorDesconectado(sender);
				}
			}
			else {
				CommandUtilities.formaCorrecta(sender, "/kick <player> [motivo]");
			}
			return true;
		} 
		return false;
	}
}
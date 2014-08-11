package es.bewom.bewomBit.commands.utility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


public class commandUtilities {

	public static void jugadorDesconectado (CommandSender sender){
		sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
	}

	public static void formaCorrecta (CommandSender sender, String formaCorrecta){
		sender.sendMessage(ChatColor.RED + "La forma correcta es " + formaCorrecta);
	}

	@SuppressWarnings("deprecation")
	public static boolean comprobarJugador (CommandSender sender, String playerName){
		if (sender.getServer().getPlayer(playerName) != null) {
			return true;
		}
		return false;
	}
}
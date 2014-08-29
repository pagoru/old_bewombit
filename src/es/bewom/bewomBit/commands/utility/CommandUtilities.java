package es.bewom.bewomBit.commands.utility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import es.bewom.bewomBit.utility.UUIDFetcher;


public class CommandUtilities {

	public static void jugadorDesconectado (CommandSender sender){
		sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
	}

	public static void formaCorrecta (CommandSender sender, String formaCorrecta){
		sender.sendMessage(ChatColor.RED + "La forma correcta es " + formaCorrecta);
	}

	public static boolean comprobarJugador (CommandSender sender, String playerName) throws Exception{
		if (sender.getServer().getOfflinePlayer(UUIDFetcher.getUUIDOf(playerName)) != null) {
			return true;
		}
		return false;
	}
	
	public static int getInt (String cadena) throws Exception {
		int numero;
		try {
			numero = Integer.parseInt(cadena);
		}
		catch (NumberFormatException e){
			throw new Exception();
		}
		return numero;
	}
	
	public static float getFloat (String cadena) throws Exception {
		float numero;
		try {
			numero = Float.parseFloat(cadena);
		}
		catch (NumberFormatException e){
			throw new Exception();
		}
		return numero;
	}
	
	public static double getDouble (String cadena) throws Exception {
		double numero;
		try {
			numero = Double.parseDouble(cadena);
		}
		catch (NumberFormatException e){
			throw new Exception();
		}
		return numero;
	}
}
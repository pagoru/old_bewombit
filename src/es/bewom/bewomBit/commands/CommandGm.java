package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandGm {

	@SuppressWarnings({ "deprecation" })
	public static boolean commandgm(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("gm")){
			
			if (args.length == 1){
				
				Player craftPlayer = (Player) sender;
				
				cambiarModoDeJuego (craftPlayer, args);
			}
			
			else if (args.length == 2) {
				if (sender.getServer().getPlayer(args [1]) != null){

					Player craftPlayerArgs = Bukkit.getServer().getPlayer(args[1]);
					cambiarModoDeJuego (sender, craftPlayerArgs, args);
				} else {
					CommandUtilities.jugadorDesconectado(sender);
				}
			} else {
				CommandUtilities.formaCorrecta(sender, "/gm [gamemode] [player]");
			}
			return true;
		}
		return false;
	}

	public static void cambiarModoDeJuego (Player player, String [] args){

		if (args[0].equals("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")) {
			player.sendMessage(ChatColor.GRAY + "Tu modo de juego es Survival.");
			player.setGameMode(GameMode.SURVIVAL);
		}
		else if (args[0].equals("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("creativo")) {
			player.sendMessage(ChatColor.GRAY + "Tu modo de juego es Creativo.");
			player.setGameMode(GameMode.CREATIVE);
		}
		else if (args[0].equals("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("aventura")) {
			player.sendMessage(ChatColor.GRAY + "Tu modo de juego es Aventura.");
			player.setGameMode(GameMode.ADVENTURE);
		}
		else {
			player.sendMessage (ChatColor.RED + "Debes escribir 0, 1 o 2!");
		}
	}

	public static void cambiarModoDeJuego (CommandSender sender, Player playerArgs, String [] args){

		if (args[0].equals("0")) {
			sender.sendMessage(ChatColor.GRAY + "El modo de juego de " + args[1] + " es Survival.");
			playerArgs.setGameMode(GameMode.SURVIVAL);
		}
		else if (args[0].equals("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("creativo")) {
			sender.sendMessage(ChatColor.GRAY + "El modo de juego de " + args[1] + " es Creativo.");
			playerArgs.setGameMode(GameMode.CREATIVE);
		}
		else if (args[0].equals("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("aventura")) {
			sender.sendMessage(ChatColor.GRAY + "El modo de juego de " + args[1] + " es Aventura.");
			playerArgs.setGameMode(GameMode.ADVENTURE);
		}
		else{
			sender.sendMessage (ChatColor.RED + "Debes escribir 0, 1 o 2!");
		}
	}
}
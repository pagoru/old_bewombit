package es.bewom.bewomBit.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.commands.utility.commandUtilities;

public class commandSeen {

	@SuppressWarnings("deprecation")
	public static boolean commandseen(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("seen")){

			if (args.length == 1) {
				if (commandUtilities.comprobarJugador(sender, args [0])){
					
					Player craftPlayer = (Player) sender;
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(args[0]);

					long craftPlayerArgsLast = (int) craftPlayerArgs.getLastPlayed();

					Date date = new Date(craftPlayerArgsLast); // *1000 is to convert seconds to milliseconds
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));

					craftPlayer.sendMessage(ChatColor.GRAY + "El jugador " + args[0] + " esta desconectado desde el " + dateFormat.format(date));
				}
				commandUtilities.comprobarJugador(sender, args [0]);

			}
			else {
				commandUtilities.formaCorrecta(sender, "/seen <player>");

			}
			return true;
		}
		return false;
	}
}

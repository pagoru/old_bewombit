package es.bewom.bewomBit.commands;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandV {

	static Logger log = Logger.getLogger("Minecraft");

	@SuppressWarnings({ "deprecation" })
	public static boolean commandv(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("v")){

			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;

			if(args.length == 2) {

				if (CommandUtilities.comprobarJugador(sender, args [0])){
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[1]);

					if (args[0].equals("ocultar")){
						
						craftPlayerArgs.hidePlayer(craftPlayer);
						craftPlayer.sendMessage(ChatColor.GRAY + "Estas oculto para el jugador " + args[1] + ".");

					} else if (args[0].equals("mostrar")){
					
						craftPlayerArgs.showPlayer(craftPlayer);	
						craftPlayer.sendMessage(ChatColor.GRAY + "Ya no estas oculto para el jugador " + args[1] + ".");
						
					}
					
				} else {
					
					CommandUtilities.formaCorrecta(sender, "/v <mostrar/ocultar> <player>");
					
				}

			} else if (args.length == 1){
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Estas oculto para todos los jugadores en linea.");
				
//			    for (Player players : Bukkit.getOnlinePlayers()){
//			    	
//			    	players.hidePlayer(craftPlayer);
//			    }
				
			}
			
			return true;
		}
		return false;
	}
}
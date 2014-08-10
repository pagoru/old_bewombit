package es.bewom.bewomBit.commands;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandGm {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@SuppressWarnings({ "unused", "deprecation" })
	public static boolean commandgm(CommandSender sender, Command cmd, String label, String[] args){
		
		Player craftPlayer = (Player) sender;
		String playerName = sender.getName();
		String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player
		Player craftPlayerArgs;
		
		if (label.equalsIgnoreCase("gm")){
			
			if (args.length == 1){
				
				if (args[0].equals("0")) {
					
					craftPlayer.setGameMode(GameMode.SURVIVAL);
					
				} else if (args[0].equals("1")) {
					
					craftPlayer.setGameMode(GameMode.CREATIVE);
					
				} else if (args[0].equals("2")) {
					
					craftPlayer.setGameMode(GameMode.ADVENTURE);
					
				} 

			} else if(args.length == 2) {
				
				if (sender.getServer().getPlayer(args [1]) != null){

					craftPlayerArgs = Bukkit.getServer().getPlayer(args[1]);
					String playerArgsName = craftPlayerArgs.getName();
					
					if (args[0].equals("0")) {
						
						craftPlayer.sendMessage(ChatColor.GRAY + "Se ha cambiado el modo de juego de " + args[1] + " a Survival.");
						craftPlayerArgs.setGameMode(GameMode.SURVIVAL);
						
					} else if (args[0].equals("1")) {
						
						craftPlayer.sendMessage(ChatColor.GRAY + "Se ha cambiado el modo de juego de " + args[1] + " a Creativo.");
						craftPlayerArgs.setGameMode(GameMode.CREATIVE);
						
					} else if (args[0].equals("2")) {
						
						craftPlayer.sendMessage(ChatColor.GRAY + "Se ha cambiado el modo de juego de " + args[1] + " a Aventura.");
						craftPlayerArgs.setGameMode(GameMode.ADVENTURE);
						
					} 
				
				}
			
			} 
		}
		return false;
		
	}
}

package es.bewom.bewomBit.commands;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandV {
	
static Logger log = Logger.getLogger("Minecraft");
	
	@SuppressWarnings({ "unused", "deprecation" })
	public static boolean commandv(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("v")){
			
			Player craftPlayer = (Player) sender;
			String playerName = sender.getName();
			String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player
			Player craftPlayerArgs;
			
			if (args.length == 0){
				
				craftPlayer.sendMessage(ChatColor.RED+ "El comando esta incompleto, usa /v [mostrar/ocultar].");
				
			} else if(args.length == 2) {
				
				if (args[0].equals("ocultar")){
					
					if (sender.getServer().getPlayer(args [1]) != null){
						
						craftPlayerArgs = Bukkit.getServer().getPlayer(args[1]);
						String playerArgsName = craftPlayerArgs.getName();
						
						craftPlayerArgs.hidePlayer(craftPlayer);	
						craftPlayer.sendMessage(ChatColor.GRAY + "Estas oculto para el jugador " + args[1] + ".");
	
					}
										
				} else if (args[0].equals("mostrar")){
					
					if (sender.getServer().getPlayer(args [1]) != null){
						
						craftPlayerArgs = Bukkit.getServer().getPlayer(args[1]);
						String playerArgsName = craftPlayerArgs.getName();
						
						craftPlayerArgs.showPlayer(craftPlayer);	
						craftPlayer.sendMessage(ChatColor.GRAY + "Ya no estas oculto para el jugador " + args[1] + ".");
		
					
					}
					
					
				}
			} 
			return true;
			
		}
		return false;
		
	}
	
}

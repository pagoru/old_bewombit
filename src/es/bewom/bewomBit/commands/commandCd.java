package es.bewom.bewomBit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandCd {
	
	@SuppressWarnings("deprecation")
	public static boolean commandcd(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("cd")){
			
			if (args.length == 2){
			
				if (args[0] == "jugador"){
					
					if (sender.getServer().getPlayer(args[0]) != null){
						
						Player craftPlayer = (Player) sender;
						Player craftPlayerArgs;
						
					}
					
				} else if (args[0] == "chat") {
					
					
					
				}
			}
		}
		return false;
	}
}

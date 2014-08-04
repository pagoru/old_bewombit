package es.bewom.bewomBit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandSuicide {
	
	public static boolean commandsuicide(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("suicide")){
			Player craftPlayer = (Player) sender;
					
			craftPlayer.setHealth(0);
			
			craftPlayer.sendMessage(ChatColor.GRAY + "Te has suicidado.");

			return true;
				
		}
		return false;
	}
		

}

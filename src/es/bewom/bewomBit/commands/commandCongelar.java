package es.bewom.bewomBit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.bewomBit;

public class commandCongelar {

	public static boolean commandcongelar (CommandSender sender, Command cmd, String commandLabel, String [] args){
		if (commandLabel.equalsIgnoreCase("congelar")){
			Player craftPlayer = (Player) sender;
			if (args.length == 0){
				if (!bewomBit.isCongelar()){
					bewomBit.setCongelar(true);
					craftPlayer.sendMessage(ChatColor.GRAY + "Usuarios congelados.");
				}
				else{
					bewomBit.setCongelar(false);
					craftPlayer.sendMessage(ChatColor.GRAY + "Usuarios descongelados.");
				}
			}
		}
		return false;
	}
}
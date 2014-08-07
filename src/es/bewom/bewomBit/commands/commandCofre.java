package es.bewom.bewomBit.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandCofre {
	
	@SuppressWarnings("deprecation")
	public static boolean commandcofre(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("cofre")){
			
			Player craftPlayer = (Player) sender;
			String playerName = craftPlayer.getName();
			
			if (craftPlayer.getTargetBlock(null, 5).getType() == Material.CHEST){
				craftPlayer.sendMessage("CHEST");
			}
			
		}
		return false;
	
	}
	
}

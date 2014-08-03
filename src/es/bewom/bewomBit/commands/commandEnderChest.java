package es.bewom.bewomBit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class commandEnderChest {
	
	public static boolean commandenderchest(CommandSender sender, Command cmd, String label, String[] args){
		Player craftPlayer = (Player) sender;
		
		if (label.equalsIgnoreCase("enderchest")){
			
				
				Inventory craftPlayerInventory = craftPlayer.getEnderChest();
				craftPlayer.openInventory(craftPlayerInventory);
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Abierto el enderchest.");

				return true;
				
			
		} 
		return false;
	
	}

}

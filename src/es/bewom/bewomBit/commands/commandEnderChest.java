package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import es.bewom.bewomBit.commands.utility.commandUtilities;

public class commandEnderChest {
	
	@SuppressWarnings("deprecation")
	public static boolean commandenderchest(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("enderchest") || label.equalsIgnoreCase("ender") || label.equalsIgnoreCase("end")){
			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;
			
			if (args.length == 0){
				
				Inventory craftPlayerInventory = craftPlayer.getEnderChest();
				craftPlayer.openInventory(craftPlayerInventory);
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Has abierto el enderchest.");
			}
			
			if (args.length == 1) {
				
				if (sender.getServer().getPlayer(args [0]) != null){
					
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					
					Inventory craftPlayerArgsInventory = craftPlayerArgs.getEnderChest();
					craftPlayer.openInventory(craftPlayerArgsInventory);
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Has abierto el enderchest de " + craftPlayerArgs.getName() + ".");
				} else {
					commandUtilities.jugadorDesconectado(sender);
				}
				
			} else {
				commandUtilities.formaCorrecta(sender, "/enderchest [player], /ender [player] o /end [player]");
			}
			return true;
		}
		return false;
	}
}

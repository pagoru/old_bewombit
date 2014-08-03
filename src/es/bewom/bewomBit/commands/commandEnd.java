package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class commandEnd {
	
	@SuppressWarnings("deprecation")
	public static boolean commandend(CommandSender sender, Command cmd, String label, String[] args){
		Player craftPlayer = (Player) sender;
		Player craftPlayerArgs;
		
		if (label.equalsIgnoreCase("end")){
			
			if (args.length == 1) {
				
				if (sender.getServer().getPlayer(args [0]) != null){
					
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					Inventory craftPlayerArgsInventory = craftPlayerArgs.getEnderChest();
					craftPlayer.openInventory(craftPlayerArgsInventory);
					String playerArgsName = craftPlayerArgs.getName();
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Abierto el enderchest del jugador " + playerArgsName + ".");

					return true;
					
				} else {
					craftPlayer.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
					return true;
				}
				
			} else {
				sender.sendMessage(ChatColor.RED + "La forma correcta es /end <player>");
				return true;
			}
			
		}
		
		return false;
	
	}

}

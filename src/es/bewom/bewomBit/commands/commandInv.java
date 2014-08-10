package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class commandInv {
	
	@SuppressWarnings("deprecation")
	public static boolean commandinv(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("inv")){
			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;
			
			if (args.length == 1) {
				
				if (sender.getServer().getPlayer(args [0]) != null){
					
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					PlayerInventory craftPlayerArgsInventory = craftPlayerArgs.getInventory();
					craftPlayer.openInventory(craftPlayerArgsInventory);
					String playerArgsName = craftPlayerArgs.getName();
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Abierto el inventario del jugador " + playerArgsName + ".");
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.RED + "El jugador no esta conectado.");

				}
				
			} else {
				
				sender.sendMessage(ChatColor.RED + "La forma correcta es /inv <player>");
				
			}
			
		}
		
		return false;
	
	}

}

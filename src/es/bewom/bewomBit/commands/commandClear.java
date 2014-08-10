package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class commandClear {
	
	@SuppressWarnings("deprecation")
	public static boolean commandclear(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("clear")){
			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;
			
			if (args.length == 0){
				craftPlayer.getInventory().clear();
				craftPlayer.getInventory().setHelmet(new ItemStack(Material.AIR));
				craftPlayer.getInventory().setChestplate(new ItemStack(Material.AIR));
				craftPlayer.getInventory().setLeggings(new ItemStack(Material.AIR));
				craftPlayer.getInventory().setBoots(new ItemStack(Material.AIR));
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Se ha eliminado tu inventario!");
				
			} else if (args.length == 1){
				
				if (sender.getServer().getPlayer(args [0]) != null){
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					
					craftPlayerArgs.getInventory().clear();
					craftPlayerArgs.getInventory().setHelmet(new ItemStack(Material.AIR));
					craftPlayerArgs.getInventory().setChestplate(new ItemStack(Material.AIR));
					craftPlayerArgs.getInventory().setLeggings(new ItemStack(Material.AIR));
					craftPlayerArgs.getInventory().setBoots(new ItemStack(Material.AIR));

					craftPlayerArgs.sendMessage(ChatColor.GRAY + "Se ha eliminado tu inventario!");
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Se ha eliminado el inventario de " + args[0] + "!");
					
				} else {
					
					sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");

				}
				
			} else {
				
				sender.sendMessage(ChatColor.RED + "La forma correcta es /clear [player]");
				
			}
			return true;
		}
		
		return false;
	
		
	}

}

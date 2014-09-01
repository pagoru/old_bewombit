package es.bewom.bewomBit.groups.see;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import es.bewom.bewomBit.utility.commands.CommandUtilities;

public class CommandEnderChest {
	
	@SuppressWarnings("deprecation")
	public static boolean commandenderchest(CommandSender sender, Command cmd, String label, String[] args) throws Exception{
		
		if (label.equalsIgnoreCase("enderchest") || label.equalsIgnoreCase("ender") || label.equalsIgnoreCase("end")){
			Player craftPlayer = (Player) sender;
			Player craftPlayerArgs;
			
			if (args.length == 0){
				
				Inventory craftPlayerInventory = craftPlayer.getEnderChest();
				craftPlayer.openInventory(craftPlayerInventory);
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Has abierto el enderchest.");
			}
			
			else if (args.length == 1) {
				
				if (sender.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId()) != null){
					
					craftPlayerArgs = Bukkit.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId());
					
					Inventory craftPlayerArgsInventory = craftPlayerArgs.getEnderChest();
					craftPlayer.openInventory(craftPlayerArgsInventory);
					
					craftPlayer.sendMessage(ChatColor.GRAY + "Has abierto el enderchest de " + craftPlayerArgs.getName() + ".");
				}
				else {
					CommandUtilities.jugadorDesconectado(sender);
				}
				
			}
			else {
				CommandUtilities.formaCorrecta(sender, "/enderchest [player], /ender [player] o /end [player]");
			}
			return true;
		}
		return false;
	}
}
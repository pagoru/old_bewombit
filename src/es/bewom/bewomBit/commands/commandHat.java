package es.bewom.bewomBit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class commandHat {

	public static boolean commandhat(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("hat")){
			Player craftPlayer = (Player) sender;
			
			ItemStack craftPlayerItem = craftPlayer.getItemInHand();
			ItemStack craftPlayerHat = craftPlayer.getInventory().getHelmet();
			
			craftPlayer.setItemInHand(craftPlayerHat);
			craftPlayer.getInventory().setHelmet(craftPlayerItem);
			
			craftPlayer.sendMessage(ChatColor.GRAY + "Bonito sombrero!");
			
		}
		
		return false;
	}

}

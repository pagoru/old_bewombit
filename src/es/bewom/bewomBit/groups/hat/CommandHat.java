package es.bewom.bewomBit.groups.hat;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandHat {

	public static boolean commandhat(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("hat")){

			Player craftPlayer = (Player) sender;
			
			ItemStack itemInHat = craftPlayer.getInventory().getHelmet();
			ItemStack itemInHand = craftPlayer.getItemInHand();
			
			if(craftPlayer.getItemInHand().getType() != Material.AIR){
				
				craftPlayer.getInventory().setHelmet(itemInHand);
				craftPlayer.setItemInHand(itemInHat);
				craftPlayer.sendMessage(ChatColor.GRAY + "Bonito sombrero!");
				
			} else {
				
				craftPlayer.sendMessage(ChatColor.RED + "No puedes colocarte aire como sombrero!");
			}
			
			return true;
		}
		return false;
	}

}
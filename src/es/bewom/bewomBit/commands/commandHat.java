package es.bewom.bewomBit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandHat {

	public static boolean commandhat(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("hat")){

			Player craftPlayer = (Player) sender;
			ponerItemEnManoDeSombrero (craftPlayer);
			return true;
		}
		return false;
	}

	public static void ponerItemEnManoDeSombrero (Player player){
		player.setItemInHand(player.getInventory().getHelmet());
		player.getInventory().setHelmet(player.getItemInHand());
		player.sendMessage(ChatColor.GRAY + "Bonito sombrero!");
	}
}

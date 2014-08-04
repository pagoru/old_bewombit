package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class commandHeal {

	@SuppressWarnings("deprecation")
	public static boolean commandheal (CommandSender sender, Command cmd, String label, String[] args){


		if (label.equalsIgnoreCase("heal")){
			Player craftPlayer = (Player) sender;
			if (args.length == 0){
				craftPlayer.setHealth(20);
				craftPlayer.setFoodLevel(20);
				craftPlayer.setSaturation(20);
				craftPlayer.setFireTicks(0);
				craftPlayer.setExhaustion(0);
				craftPlayer.setRemainingAir(craftPlayer.getMaximumAir());
				for (PotionEffect effect : craftPlayer.getActivePotionEffects()) {
					craftPlayer.removePotionEffect(effect.getType());
				}
				craftPlayer.sendMessage(ChatColor.GRAY + "Has sido curado.");
			}

			if (args.length == 1){
				Player craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
				craftPlayerArgs.setHealth(20);
				craftPlayerArgs.setFoodLevel(20);
				craftPlayerArgs.setSaturation(20);
				craftPlayerArgs.setFireTicks(0);
				craftPlayerArgs.setExhaustion(0);
				craftPlayerArgs.setRemainingAir(craftPlayer.getMaximumAir());
				for (PotionEffect effect : craftPlayerArgs.getActivePotionEffects()) {
					craftPlayerArgs.removePotionEffect(effect.getType());
				}
				craftPlayer.sendMessage(ChatColor.GRAY + craftPlayerArgs.getName() + " ha sido curado.");
				craftPlayerArgs.sendMessage(ChatColor.GRAY + "Has sido curado.");
			}
		}
		return false;
	}
}

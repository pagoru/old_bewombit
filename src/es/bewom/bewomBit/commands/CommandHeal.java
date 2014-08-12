package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandHeal {

	@SuppressWarnings("deprecation")
	public static boolean commandheal (CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("heal")){
			Player craftPlayer = (Player) sender;
			if (args.length == 0){

				curarJugador (craftPlayer);
			}

			else if (args.length == 1){
				if (sender.getServer().getPlayer(args [0]) != null){
					Player craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					curarJugador (craftPlayerArgs);
					craftPlayer.sendMessage(ChatColor.GRAY + craftPlayerArgs.getName() + " ha sido curado.");
				} else {
					CommandUtilities.jugadorDesconectado(sender);
				}
			}
			else{
				CommandUtilities.formaCorrecta(sender, "/heal [player]");
			}
			return true;
		}
		return false;
	}

	public static void curarJugador (Player player){
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setSaturation(20);
		player.setFireTicks(0);
		player.setExhaustion(0);
		player.setRemainingAir(player.getMaximumAir());
		for (PotionEffect effect : player.getActivePotionEffects()) {
			player.removePotionEffect(effect.getType());
		}
		player.sendMessage(ChatColor.GRAY + "Has sido curado.");
	}
}
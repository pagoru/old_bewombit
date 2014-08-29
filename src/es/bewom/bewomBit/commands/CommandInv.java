package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandInv {

	@SuppressWarnings("deprecation")
	public static boolean commandinv(CommandSender sender, Command cmd, String label, String[] args) throws Exception{

		if (label.equalsIgnoreCase("inv")){
			Player craftPlayer = (Player) sender;

			if (args.length == 1) {

				if (sender.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId()) != null){

					Player craftPlayerArgs = Bukkit.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId());
					PlayerInventory craftPlayerArgsInventory = craftPlayerArgs.getInventory();
					craftPlayer.openInventory(craftPlayerArgsInventory);

					craftPlayer.sendMessage(ChatColor.GRAY + "Abierto el inventario del jugador " + craftPlayerArgs.getName() + ".");

				}
				else {
					CommandUtilities.jugadorDesconectado(sender);
				}
			} else {
				CommandUtilities.formaCorrecta(sender, "/inv <player>");
			}
			return true;
		}
		return false;
	}
}
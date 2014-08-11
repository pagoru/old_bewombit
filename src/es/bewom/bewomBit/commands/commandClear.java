package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import es.bewom.bewomBit.commands.utility.commandUtilities;

public class commandClear {

	@SuppressWarnings("deprecation")
	public static boolean commandclear(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("clear")){
			Player craftPlayer = (Player) sender;

			if (args.length == 0){
				eliminarInventario (craftPlayer);
			}

			else if (args.length == 1){

				if (sender.getServer().getPlayer(args [0]) != null){
					Player craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					eliminarInventario (craftPlayerArgs);
					craftPlayer.sendMessage(ChatColor.GRAY + "Se ha eliminado el inventario de " + args[0] + "!");
				}
				else {
					commandUtilities.jugadorDesconectado(sender);
				}
			}
			else {
				commandUtilities.formaCorrecta(sender, "/clear [player]");
			}
			return true;
		}
		return false;
	}

	public static void eliminarInventario (Player player){

		player.getInventory().clear();
		player.getInventory().setHelmet(new ItemStack(Material.AIR));
		player.getInventory().setChestplate(new ItemStack(Material.AIR));
		player.getInventory().setLeggings(new ItemStack(Material.AIR));
		player.getInventory().setBoots(new ItemStack(Material.AIR));
		player.sendMessage(ChatColor.GRAY + "Se ha eliminado tu inventario!");
	}
}

package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandClear {

	@SuppressWarnings("deprecation")
	public static boolean commandclear(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("clear")){

			if (args.length == 0){
				Player craftPlayer = (Player) sender;
				eliminarInventario (craftPlayer);
			}

			else if (args.length == 1){

				if (sender.getServer().getPlayer(args [0]) != null){
					Player craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					eliminarInventario (craftPlayerArgs);
					sender.sendMessage(ChatColor.GRAY + "Se ha eliminado el inventario de " + args[0] + "!");
				}
				else {
					CommandUtilities.jugadorDesconectado(sender);
				}
			}
			else {
				CommandUtilities.formaCorrecta(sender, "/clear [player]");
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

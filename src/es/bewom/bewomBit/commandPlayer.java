package es.bewom.bewomBit;

import es.bewom.bewomBit.commands.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandPlayer implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		Player craftPlayer = (Player) sender; //craftPlayer Player

		// ---> Comandos de administracion <--- //

		if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){

			if(commandSay.commandsay(sender, cmd, label, args)){
				return true;
			}
			if(commandTpHere.commandtphere(sender, cmd, label, args)){
				return true;
			}
			if(commandFly.commandfly(sender, cmd, label, args)){
				return true;
			}
			if(commandClear.commandclear(sender, cmd, label, args)){
				return true;
			}
			if(commandInv.commandinv(sender, cmd, label, args)){
				return true;
			}
			if(commandEnderChest.commandenderchest(sender, cmd, label, args)){
				return true;
			}
			if(commandKill.commandkill(sender, cmd, label, args)){
				return true;
			}
			
		} else {
			
			sender.sendMessage(ChatColor.RED + "No tienes permisos para usar este comando!");
		
		}
		
		// ---> Comandos de vips <--- //
		
		if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod") || craftPlayer.hasPermission("bewom.vip")) {
			
			if(commandHat.commandhat(sender, cmd, label, args)){
				return true;
			}
			if(commandEnderChest.commandenderchest(sender, cmd, label, args)){
				return true;
			}
			if(commandSuicide.commandsuicide(sender, cmd, label, args)){
				return true;
			}
			
		} else {
			
			sender.sendMessage(ChatColor.RED + "No tienes permisos para usar este comando!");
		
		}
		return false;
	}
}

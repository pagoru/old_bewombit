package es.bewom.bewomBit.commands.utility;

import es.bewom.bewomBit.commands.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandPlayer implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		// ---> Comandos de administracion <--- //

		if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod")){

			if(CommandSay.commandsay(sender, cmd, label, args)){
				return true;
			}
			if(CommandTpHere.commandtphere(sender, cmd, label, args)){
				return true;
			}
			if(CommandFly.commandfly(sender, cmd, label, args)){
				return true;
			}
			if(CommandClear.commandclear(sender, cmd, label, args)){
				return true;
			}
			if(CommandInv.commandinv(sender, cmd, label, args)){
				return true;
			}
			if(CommandEnderChest.commandenderchest(sender, cmd, label, args)){
				return true;
			}
			if(CommandKill.commandkill(sender, cmd, label, args)){
				return true;
			}
			if(CommandHeal.commandheal(sender,cmd,label,args)){
				return true;
			}
			if(CommandKick.commandkick(sender, cmd, label, args)){
				return true;
			}
			if(CommandCongelar.commandcongelar(sender, cmd, label, args)){
				return true;
			}
			if(CommandGm.commandgm(sender, cmd, label, args)){
				return true;
			}
			if(CommandV.commandv(sender, cmd, label, args)){
				return true;
			}
			if(CommandSpawner.commandspawner(sender, cmd, label, args)){
				return true;
			}
		}
		
		// ---> Comandos de vips <--- //
		
		if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod") || sender.hasPermission("bewom.vip")) {
			
			if(CommandHat.commandhat(sender, cmd, label, args)){
				return true;
			}
			if(CommandEnderChest.commandenderchest(sender, cmd, label, args)){
				return true;
			}
			if(CommandSuicide.commandsuicide(sender, cmd, label, args)){
				return true;
			}
			if(CommandSeen.commandseen(sender, cmd, label, args)){
				return true;
			}
			if(CommandTpa.commandtpa(sender, cmd, label, args)){
				return true;
			}
			if(CommandTpaHere.commandtpahere(sender, cmd, label, args)){
				return true;
			}
			if(CommandSetHome.commandsethome(sender, cmd, label, args)){
				return true;
			}
			if(CommandHome.commandhome(sender, cmd, label, args)){
				return true;
			}
		} 
		
		// ---> Comandos cualquiera <--- //
		
		if(CommandMp.commandmp(sender, cmd, label, args)){
			return true;
		}
		if(CommandP.commandp(sender, cmd, label, args)){
			return true;
		}
		return false;
	}
}
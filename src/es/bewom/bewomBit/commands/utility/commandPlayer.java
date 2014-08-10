package es.bewom.bewomBit.commands.utility;

import es.bewom.bewomBit.commands.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class commandPlayer implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		// ---> Comandos de administracion <--- //

		if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod")){

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
			if(commandHeal.commandheal(sender,cmd,label,args)){
				return true;
			}
			if(commandKick.commandkick(sender, cmd, label, args)){
				return true;
			}
			if(commandCongelar.commandcongelar(sender, cmd, label, args)){
				return true;
			}
			if(commandGm.commandgm(sender, cmd, label, args)){
				return true;
			}
			if(commandV.commandv(sender, cmd, label, args)){
				return true;
			}
			
			
		}
		
		// ---> Comandos de vips <--- //
		
		if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod") || sender.hasPermission("bewom.vip")) {
			
			if(commandHat.commandhat(sender, cmd, label, args)){
				return true;
			}
			if(commandEnderChest.commandenderchest(sender, cmd, label, args)){
				return true;
			}
			if(commandSuicide.commandsuicide(sender, cmd, label, args)){
				return true;
			}
			if(commandSeen.commandseen(sender, cmd, label, args)){
				return true;
			}
			if(commandTpa.commandtpa(sender, cmd, label, args)){
				return true;
			}
			if(commandTpaHere.commandtpahere(sender, cmd, label, args)){
				return true;
			}
			
		} 
		
		// ---> Comandos cualquiera <--- //
		
		if(commandMp.commandmp(sender, cmd, label, args)){
			return true;
		}
		if(commandP.commandp(sender, cmd, label, args)){
			return true;
		}
		
		return false;
	}
}

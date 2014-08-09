package es.bewom.bewomBit.commands.utility;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import es.bewom.bewomBit.commands.commandsAutoComplete.commandAutoP;

public class autoCompleteTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(commandAutoP.commandautop(sender, cmd, alias, args) != null){
			
			return commandAutoP.commandautop(sender, cmd, alias, args);
			
		} 
		
		return null;
	}

}

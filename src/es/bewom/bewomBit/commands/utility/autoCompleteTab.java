package es.bewom.bewomBit.commands.utility;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import es.bewom.bewomBit.commands.commandsAutoComplete.commandAutoGm;
import es.bewom.bewomBit.commands.commandsAutoComplete.commandAutoP;
import es.bewom.bewomBit.commands.commandsAutoComplete.commandAutoSpawner;
import es.bewom.bewomBit.commands.commandsAutoComplete.commandAutoTpa;
import es.bewom.bewomBit.commands.commandsAutoComplete.commandAutoTpaHere;
import es.bewom.bewomBit.commands.commandsAutoComplete.commandAutoV;

public class autoCompleteTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(commandAutoP.commandautop(sender, cmd, alias, args) != null){
			
			return commandAutoP.commandautop(sender, cmd, alias, args);
			
		} 
		
		if(commandAutoGm.commandautogm(sender, cmd, alias, args) != null){
			
			return commandAutoGm.commandautogm(sender, cmd, alias, args);
			
		} 
		
		if(commandAutoV.commandautov(sender, cmd, alias, args) != null){
			
			return commandAutoV.commandautov(sender, cmd, alias, args);
			
		}
		
		if(commandAutoTpa.commandautotpa(sender, cmd, alias, args) != null){
			
			return commandAutoTpa.commandautotpa(sender, cmd, alias, args);
			
		} 

		if(commandAutoTpaHere.commandautotpahere(sender, cmd, alias, args) != null){
			
			return commandAutoTpaHere.commandautotpahere(sender, cmd, alias, args);
			
		} 
		
		if(commandAutoSpawner.commandautospawner(sender, cmd, alias, args) != null){
			
			return commandAutoSpawner.commandautospawner(sender, cmd, alias, args);
			
		} 
		
		return null;
	}

}

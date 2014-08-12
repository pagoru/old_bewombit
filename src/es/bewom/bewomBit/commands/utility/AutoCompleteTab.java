package es.bewom.bewomBit.commands.utility;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import es.bewom.bewomBit.commands.autocomplete.CommandAutoDelHome;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoGm;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoHome;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoP;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoSpawner;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoTpa;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoTpaHere;
import es.bewom.bewomBit.commands.autocomplete.CommandAutoV;

public class AutoCompleteTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(CommandAutoP.commandautop(sender, cmd, alias, args) != null){
			return CommandAutoP.commandautop(sender, cmd, alias, args);
		} 	
		
		if(CommandAutoGm.commandautogm(sender, cmd, alias, args) != null){
			return CommandAutoGm.commandautogm(sender, cmd, alias, args);
		} 	
		
		if(CommandAutoV.commandautov(sender, cmd, alias, args) != null){			
			return CommandAutoV.commandautov(sender, cmd, alias, args);			
		}	
		
		if(CommandAutoTpa.commandautotpa(sender, cmd, alias, args) != null){			
			return CommandAutoTpa.commandautotpa(sender, cmd, alias, args);			
		} 
		
		if(CommandAutoSpawner.commandautospawner(sender, cmd, alias, args) != null){
			return CommandAutoSpawner.commandautospawner(sender, cmd, alias, args);
		} 
		
		if(CommandAutoTpaHere.commandautotpahere(sender, cmd, alias, args) != null){			
			return CommandAutoTpaHere.commandautotpahere(sender, cmd, alias, args);			
		} 	
		if(CommandAutoHome.commandautohome(sender, cmd, alias, args) != null){			
			return CommandAutoHome.commandautohome(sender, cmd, alias, args);			
		} 
		if(CommandAutoDelHome.commandautodelhome(sender, cmd, alias, args) != null){			
			return CommandAutoDelHome.commandautodelhome(sender, cmd, alias, args);			
		} 
		return null;
	}
}
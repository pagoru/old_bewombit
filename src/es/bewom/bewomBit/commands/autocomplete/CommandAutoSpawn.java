package es.bewom.bewomBit.commands.autocomplete;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoSpawn {
	
	public static List<String> commandautospawn(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("spawn")){
			
			if (args.length == 1){
				
				List<String> pList =  Arrays.asList("set");  
				
				return pList;
				
			} else if (args.length == 2){
				
				List<String> pList =  Arrays.asList("permanente", "dinamico");  
				
				return pList;
			}
			
		}
		
		return null;
	}
}

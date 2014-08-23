package es.bewom.bewomBit.commands.autocomplete;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoXray {
	
	public static List<String> commandautoxray(CommandSender sender, Command cmd, String alias, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("xray")){
			
			List<String> pList = null;
			
			if (args.length == 1){
				
				pList =  Arrays.asList("top");
				
			} else if (args.length == 2){
				
				pList =  Arrays.asList("limpiar");
				
			}
			
			return pList;
			
		}
		return null;
	}
}

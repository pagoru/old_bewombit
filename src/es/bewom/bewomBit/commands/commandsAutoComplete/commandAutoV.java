package es.bewom.bewomBit.commands.commandsAutoComplete;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class commandAutoV {

	public static List<String> commandautov(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("v")){
			
			if (args.length == 1){
				
				List<String> pList =  Arrays.asList("mostrar","ocultar");  

				return pList;
			} 		
		}
		return null;
	}
}

package es.bewom.bewomBit.groups.v;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoV {

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
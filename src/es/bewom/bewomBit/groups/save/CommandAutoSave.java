package es.bewom.bewomBit.groups.save;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoSave {

	public static List<String> commandautosave(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("save")){
			
			if (args.length == 1){
				
				List<String> pList =  Arrays.asList("jugadores","mundos","all");  	
				return pList;
				
			}
		}
		return null;
	
	}
}

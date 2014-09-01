package es.bewom.bewomBit.groups.lag;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoLag {
	
	public static List<String> commandautolag(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("lag")){
			
			if (args.length == 1){
				
				List<String> pList =  Arrays.asList("tps");  
				
				return pList;
			}
		}
		return null;
	}

}

package es.bewom.bewomBit.commands.autocomplete;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoBan {
	
	public static List<String> commandautoban(CommandSender sender, Command cmd, String alias, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("ban")){
			
			List<String> pList = null;
			
			if (args.length == 2){
				
				pList =  Arrays.asList("temporal","permanente");  
				
			} else if(args.length == 3){
				
				if(args[1].equals("temporal")){
					
					pList =  Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");  
					
				}
				
				
			} else if(args.length == 4){
				
				if(args[1].equals("temporal")){
					
					pList =  Arrays.asList("s","m","h","d");  
					
				}
			}
			
			return pList;
		}
		return null;

	}
	
}
	
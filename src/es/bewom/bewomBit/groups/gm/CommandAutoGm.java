package es.bewom.bewomBit.groups.gm;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoGm {

	public static List<String> commandautogm(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("gm")){
			
			if (args.length == 1){
				
				List<String> pList =  Arrays.asList("0","1","2");  
				
				return pList;
			}
		}
		return null;
	}
}
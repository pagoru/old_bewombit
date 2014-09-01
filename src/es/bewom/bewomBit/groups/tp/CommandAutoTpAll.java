package es.bewom.bewomBit.groups.tp;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoTpAll {
	
	public static List<String> commandautotpall (CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("tpall")){
			
			if (args.length == 1){
				
				List<String> pList =  Arrays.asList("vip","novip","all");  

				return pList;
			} 		
		}
		return null;
	}
}

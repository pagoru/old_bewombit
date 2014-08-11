package es.bewom.bewomBit.commands.commandsAutoComplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class commandAutoGm {

	public static List<String> commandautogm(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("gm")){
			
			if (args.length == 1){
				List<String> pList = new ArrayList<String>();  
		        
				pList.add("0");
				pList.add("1");
				pList.add("2");
				
				return pList;
			}
		}
		return null;
	}
}

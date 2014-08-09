package es.bewom.bewomBit.commands.utility;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class autoCompleteTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("p") && args.length == 1){
			
			List<String> pList = new ArrayList<String>();  
		         
			pList.add("private");
			pList.add("publico");
			
			return pList;
			
		}
		
		if(cmd.getName().equalsIgnoreCase("p") && args.length == 2 && args[0].equals("cambiar")){
			
			List<String> pList = new ArrayList<String>();  
	         
			pList.add("propietario");
			pList.add("estado");
			
			return pList;
			
		}
		
		if(cmd.getName().equalsIgnoreCase("p") && args.length == 3 && args[0].equals("cambiar") && args[1].equals("estado")){
			
			List<String> pList = new ArrayList<String>();  
	         
			pList.add("privado");
			pList.add("publico");
			
			return pList;
			
		}
		
		
		return null;
	}

}

package es.bewom.bewomBit.commands.utility;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class autoCompleteTab implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		Player craftPlayer = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("p")){
			
			if (args.length == 1){
				List<String> pList = new ArrayList<String>();  
		         
				pList.add("private");
				pList.add("publico");
				
				if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){
					pList.add("cambiar");
				}
				
				return pList;
			} else if(args.length == 2 && args[0].equals("cambiar")){
				
				if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){
					List<String> pList = new ArrayList<String>();  
			        
					pList.add("propietario");
					pList.add("estado");
					
					return pList;
				}
				
			} else if(args.length == 3 && args[0].equals("cambiar") && args[1].equals("estado")){
			
				if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){
					List<String> pList = new ArrayList<String>();  
			         
					pList.add("privado");
					pList.add("publico");
					
					return pList;
				}
			}
		
		}
		return null;
	}

}

package es.bewom.bewomBit.commands.commandsAutoComplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandAutoP {

	public static List<String> commandautop(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("p")){
			
			Player craftPlayer = (Player) sender;
			
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

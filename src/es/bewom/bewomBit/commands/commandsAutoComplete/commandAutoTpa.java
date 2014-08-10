package es.bewom.bewomBit.commands.commandsAutoComplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandAutoTpa {
	
public static List<String> commandautotpa(CommandSender sender, Command cmd, String alias, String[] args) {
		
	if(cmd.getName().equalsIgnoreCase("tpa")){
		
		if (args.length == 1){
			
			List<String> pList = new ArrayList<String>(); 
			
			pList.add("aceptar");
			pList.add("denegar");
			
			for (Object object : Bukkit.getOnlinePlayers().toArray()) {
				
				Player craftlist = (Player) object;
				pList.add(craftlist.getName());
			}

			return pList;
		} 
		
	}
	return null;
	}
}

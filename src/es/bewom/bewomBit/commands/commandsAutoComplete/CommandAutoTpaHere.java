package es.bewom.bewomBit.commands.commandsAutoComplete;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAutoTpaHere {
	
public static List<String> commandautotpahere(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("tpahere")){
						
			if (args.length == 1){
				
				List<String> pList = Arrays.asList("aceptar","denegar");
				
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

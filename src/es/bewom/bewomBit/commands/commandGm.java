package es.bewom.bewomBit.commands;

import java.util.logging.Logger;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandGm {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@SuppressWarnings({ "unused" })
	public static boolean commandgm(CommandSender sender, Command cmd, String label, String[] args){
		
		Player craftPlayer = (Player) sender;
		String playerName = sender.getName();
		String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player
		
		if (label.equalsIgnoreCase("gm")){
			
			if (args.length == 1){
				
				if (args[0].equals("0")) {
					
					craftPlayer.setGameMode(GameMode.SURVIVAL);
					
				} else if (args[0].equals("1")) {
					
					craftPlayer.setGameMode(GameMode.CREATIVE);
					
				} else if (args[0].equals("2")) {
					
					craftPlayer.setGameMode(GameMode.ADVENTURE);
					
				} 

			} 

			return true;
		}
		return false;
	}
}

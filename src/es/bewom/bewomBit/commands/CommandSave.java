package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandSave {
	
	public static boolean commandsave(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("save")){
			
			if (args.length == 0){
				
				CommandUtilities.formaCorrecta(sender, "/save [jugadores/mundos/all]");
				
			} else if (args.length == 1){
				
				if(args[0].equals("jugadores")){
					
					for (Player p : Bukkit.getServer().getOnlinePlayers()){
						
						p.saveData();
						sender.sendMessage(ChatColor.YELLOW + "Usuario " + p.getName() + " guardado.");
						
					}
					
				} else if(args[0].equals("mundos")){
					
					java.util.List<World> listWorlds = Bukkit.getServer().getWorlds();
					
					for (World w : listWorlds){
						
						w.save();
						sender.sendMessage(ChatColor.YELLOW + w.getName() + " guardado.");
						
					}
					
				} else if(args[0].equals("all")){
					
					for (Player p : Bukkit.getOnlinePlayers()){
						
						p.saveData();
						sender.sendMessage(ChatColor.YELLOW + "Usuario " + p.getName() + " guardado.");
						
					}
					
					java.util.List<World> listWorlds = Bukkit.getServer().getWorlds();
					
					for (World w : listWorlds){
						
						w.save();
						sender.sendMessage(ChatColor.YELLOW + w.getName() + " guardado.");
						
					}
					
				}
				
			}
			
			return true;	
			
		}
		
		return false;	
		
	}
	
}

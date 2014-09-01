package es.bewom.bewomBit.groups.save;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.utility.commands.CommandUtilities;

public class CommandSave {

	public static boolean commandsave(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equalsIgnoreCase("save")){

			if (args.length == 1){

				if(args[0].equals("jugadores")){
					savePlayers (sender);
				}
				else if(args[0].equals("mundos")){
					saveWorlds (sender);
				}
				else if(args[0].equals("all")){
					savePlayers (sender);
					saveWorlds (sender);
				}
				else {
					CommandUtilities.formaCorrecta(sender, "/save [jugadores/mundos/all]");
				}
			}
			else {
				CommandUtilities.formaCorrecta(sender, "/save [jugadores/mundos/all]");
			}
			return true;			
		}		
		return false;		
	}

	public static void savePlayers (CommandSender sender){
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()){
			p.saveData();
			sender.sendMessage(ChatColor.YELLOW + "Usuario " + p.getName() + " guardado.");
		}
	}
	
	public static void saveWorlds (CommandSender sender){
		
		java.util.List<World> listWorlds = Bukkit.getServer().getWorlds();
		for (World w : listWorlds){
			w.save();
			sender.sendMessage(ChatColor.YELLOW + w.getName() + " guardado.");
		}
	}
}
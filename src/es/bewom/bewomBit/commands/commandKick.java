package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandKick {
	
	@SuppressWarnings("deprecation")
	public static boolean commandkick(CommandSender sender, Command cmd, String label, String[] args){
		Player craftPlayerArgs;
		
		String broadcast = ChatColor.DARK_BLUE + "/"+ ChatColor.MAGIC + "b" + ChatColor.DARK_BLUE + "/" + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "WOM" + ChatColor.DARK_BLUE + " < ";
		
		
		if (label.equalsIgnoreCase("kick")){
			
			if (args.length == 1) {
				
				if (sender.getServer().getPlayer(args[0]) != null){
					
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					String playerArgsName = craftPlayerArgs.getName();
					
					craftPlayerArgs.kickPlayer(ChatColor.BLUE + "Has sido kickeado.");
					
					Bukkit.getServer().broadcastMessage(broadcast + ChatColor.BLUE + "El jugador " + playerArgsName + " ha sido kickeado.");
					
				} else {
					
					sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");

				}
				
			} else if (args.length > 1){
				
				if (sender.getServer().getPlayer(args[0]) != null){
				
					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					String playerArgsName = craftPlayerArgs.getName();
					
					String texto = "";
					for (int i = 1; i < args.length; i++) {
						texto += args[i] + " ";
					}
					
					craftPlayerArgs.kickPlayer(ChatColor.BLUE + "Has sido kickeado por " + texto);
					
					Bukkit.getServer().broadcastMessage(broadcast + ChatColor.BLUE + "El jugador " + playerArgsName + " ha sido kickeado por " + texto);
					
				} else {
					
					sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");

				}
			} else {
				
				sender.sendMessage(ChatColor.RED + "La forma correcta es /kick <player> [motivo]");

			}
		} 
		return false;
	}
}

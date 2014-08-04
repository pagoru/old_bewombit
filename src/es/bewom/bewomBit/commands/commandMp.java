
package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import es.bewom.bewomBit.bewomBit;
import es.bewom.bewomBit.connectPlayer;

public class commandMp {
	
	@SuppressWarnings("deprecation")
	public static boolean commandmp(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("mp")){
			
			if (args.length == 0){
				Player craftPlayer = (Player) sender;
				
				if (sender.hasPermission("bewom.admin")){
					
					if (craftPlayer.getScoreboard().getPlayerTeam(craftPlayer).getDisplayName() != "bewom_Admin"){
						sender.sendMessage(ChatColor.GRAY + "Has salido de el chat privado.");
						
						
						connectPlayer.playerArgsNameTeamAdmin.removePlayer(craftPlayer);
							bewomBit.teamAdmin.addPlayer(craftPlayer);
					} else {
						sender.sendMessage(ChatColor.RED + "No puedes salir del chat general!");
						return true;
					}
					
				} 
				
			} else if (args.length == 1){
				
				if (sender.getServer().getPlayer(args[0]) != null){
					
					if (sender.hasPermission("bewom.admin")){
						
						Player craftPlayer = (Player) sender;
						connectPlayer.playerArgsCraft = Bukkit.getServer().getPlayer(args[0]);
						String playerArgsName = connectPlayer.playerArgsCraft.getName();
						
						sender.sendMessage(ChatColor.GRAY + "Has entrado en el chat privado de " + playerArgsName + ", usa /mp para salir.");
						
						
						if (connectPlayer.playerArgsNameTeamAdmin == null) {
							connectPlayer.playerArgsNameTeamAdmin = connectPlayer.board.registerNewTeam(playerArgsName + "_ad");
							connectPlayer.playerArgsNameTeamAdmin.addPlayer(craftPlayer);
						} else {
							connectPlayer.playerArgsNameTeamAdmin.addPlayer(craftPlayer);
							
						}
						return true;
					}
					
					
					return true;
				} else {
					
					sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "La forma correcta es /mp <player>");
				return true;
			}
		}
		return false;
	}
}

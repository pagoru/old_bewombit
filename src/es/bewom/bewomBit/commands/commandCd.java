
package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import es.bewom.bewomBit.bewomBit;
import es.bewom.bewomBit.chatPlayer;

public class commandCd {
	
	@SuppressWarnings("deprecation")
	public static boolean commandcd(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("cd")){
			
			if (args.length == 0){
				Player craftPlayer = (Player) sender;
				
				chatPlayer.playerArgsNameTeam.removePlayer(craftPlayer);
				
				sender.sendMessage(ChatColor.GRAY + "Has salido de el chat privado.");
				
			} else if (args.length == 1){
				
				if (sender.getServer().getPlayer(args[0]) != null){
					
					Player craftPlayer = (Player) sender;
					chatPlayer.playerArgsCraft = Bukkit.getServer().getPlayer(args[0]);
					String playerArgsName = chatPlayer.playerArgsCraft.getName();
					
					sender.sendMessage(ChatColor.GRAY + "Has entrado en el chat privado de " + playerArgsName + ", usa /cd para salir.");
					
					chatPlayer.playerArgsNameTeam = chatPlayer.board.getTeam(playerArgsName);
					
					
					if (chatPlayer.playerArgsNameTeam == null) {
						chatPlayer.playerArgsNameTeam = chatPlayer.board.registerNewTeam(playerArgsName);
						chatPlayer.playerArgsNameTeam.addPlayer(craftPlayer);
					} else {
						chatPlayer.playerArgsNameTeam.addPlayer(craftPlayer);
						
					}
					
					
					return true;
				} else {
					
					sender.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "La forma correcta es /cd <player>");
				return true;
			}
		}
		return false;
	}
}

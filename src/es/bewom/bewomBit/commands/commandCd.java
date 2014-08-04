package es.bewom.bewomBit.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.chatPlayer;

public class commandCd {
	
	@SuppressWarnings("deprecation")
	public static boolean commandcd(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("cd")){
			
			if (args.length == 1){
					
				if (sender.getServer().getPlayer(args[0]) != null){
					
					Player craftPlayer = (Player) sender;
					Player craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					String playerArgsName = craftPlayerArgs.getName();
					
					sender.sendMessage("asasdas");
					
					chatPlayer.chatPlayerPrivate = true;
					chatPlayer.chatPlayerPrivateName = playerArgsName;
					chatPlayer.chatPlayerPrivateNameCraft = craftPlayerArgs;
					
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

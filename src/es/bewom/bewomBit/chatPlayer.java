package es.bewom.bewomBit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatPlayer implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent eventChat) {
			
		String message = eventChat.getMessage();
		Player craftPlayer = eventChat.getPlayer(); //craftPlayer Player
		String playerName = eventChat.getPlayer().getName(); //limpio String 

		if (craftPlayer.hasPermission("bewom.admin")) {
			eventChat.setFormat(ChatColor.DARK_RED + ": " + ChatColor.DARK_RED + "" + ChatColor.BOLD + playerName + ChatColor.RESET + " < " + ChatColor.WHITE  + ChatColor.BOLD + message);
			
		} else if (craftPlayer.hasPermission("bewom.mod")) {
			eventChat.setFormat(ChatColor.DARK_GREEN + ": " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + playerName + ChatColor.RESET + " < " + ChatColor.WHITE  + ChatColor.BOLD + message);
			
		} else if (craftPlayer.hasPermission("bewom.vip")) {
			eventChat.setFormat(ChatColor.DARK_AQUA + ": " + ChatColor.DARK_AQUA + playerName + " < " + message);
			
		} else {
			eventChat.setFormat(": " + playerName + " < " + ChatColor.GRAY + message);
			
		}
		
		
	}
	

}

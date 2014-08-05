package es.bewom.bewomBit;

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
		
		String admin = ChatColor.DARK_RED + "/" + ChatColor.DARK_RED + "" + ChatColor.BOLD + playerName;
		String mod = ChatColor.DARK_GREEN + "/" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + playerName;
		String vip =  ChatColor.DARK_AQUA + "/" + playerName;
		String steve = "/" + playerName;
		
		String adminModText = ChatColor.RESET + " < " + ChatColor.WHITE  + "" + ChatColor.BOLD;
		String vipDefaultText = " < ";
		
		
		if (craftPlayer.hasPermission("bewom.admin")) {			
			eventChat.setFormat(admin + adminModText + message);
			
		} else if (craftPlayer.hasPermission("bewom.mod")) {
			eventChat.setFormat(mod + adminModText + message);
			
		} else if (craftPlayer.hasPermission("bewom.vip")) {
			eventChat.setFormat(vip + vipDefaultText + message);
			
		} else {
			eventChat.setFormat(steve + vipDefaultText + message);
			
		}
			
		
	}
	

}

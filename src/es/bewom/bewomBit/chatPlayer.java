package es.bewom.bewomBit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatPlayer implements Listener {
	
	public static boolean chatPlayerPrivate;
	public static String chatPlayerPrivateName;
	public static Player chatPlayerPrivateNameCraft;

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent eventChat) {
			
		String message = eventChat.getMessage();
		Player craftPlayer = eventChat.getPlayer(); //craftPlayer Player
		String playerName = eventChat.getPlayer().getName(); //limpio String 
		
		String admin =  "/ " + ChatColor.DARK_RED + "" + ChatColor.BOLD + playerName + ChatColor.RESET + " < " + ChatColor.WHITE  + ChatColor.BOLD;
		String mod = "/ " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + playerName + ChatColor.RESET + " < " + ChatColor.WHITE  + ChatColor.BOLD;
		String vip = "/ " + ChatColor.DARK_AQUA + playerName + " < ";
		String steve = "/ " + playerName + " < ";
		
		if (chatPlayerPrivate == true){
			
			if (craftPlayer.hasPermission("bewom.admin")) {
				eventChat.setFormat("");
				eventChat.getPlayer().sendMessage("/" + chatPlayerPrivateName + admin + message);
				chatPlayerPrivateNameCraft.sendMessage("/" + chatPlayerPrivateName + admin + message);
				
			} else if (craftPlayer.hasPermission("bewom.mod")) {
				eventChat.setFormat("/" + chatPlayerPrivateName + mod + message);
				
			} else if (craftPlayer.hasPermission("bewom.vip")) {
				eventChat.setFormat("/" + chatPlayerPrivateName + vip + message);
				
			} else {
				eventChat.setFormat("/" + chatPlayerPrivateName + steve + message);
				
			}
			
		} else {
			
			if (craftPlayer.hasPermission("bewom.admin")) {
				eventChat.setFormat(admin + message);
				
			} else if (craftPlayer.hasPermission("bewom.mod")) {
				eventChat.setFormat(mod + message);
				
			} else if (craftPlayer.hasPermission("bewom.vip")) {
				eventChat.setFormat(vip + message);
				
			} else {
				eventChat.setFormat(steve + message);
				
			}
			
		}
			
		
	}
	

}

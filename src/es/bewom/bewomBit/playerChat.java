package es.bewom.bewomBit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


@SuppressWarnings("deprecation")
public class playerChat implements Listener {
	
	@SuppressWarnings("unused")
	private static Scoreboard board;
	private static Boolean colorChange = true;

	@EventHandler
	public void onPlayerChat(PlayerChatEvent eventChat) {
		String message = eventChat.getMessage();
		Player craftPlayer = eventChat.getPlayer(); //craftPlayer Player
		String playerName = eventChat.getPlayer().getName(); //limpio String 

		if (craftPlayer.hasPermission("bewom.admin")) {
			if (colorChange == true) {
				eventChat.setFormat(">> "+ ChatColor.DARK_RED + "" + ChatColor.BOLD + playerName + ChatColor.RESET + " < " + ChatColor.GRAY + ChatColor.BOLD + message);
				colorChange = false;
			} else {
				eventChat.setFormat(">> "+ ChatColor.DARK_RED + "" + ChatColor.BOLD + playerName + ChatColor.RESET + " < " + ChatColor.WHITE  + ChatColor.BOLD + message);
				colorChange = true;
			}
			
		} else {
			
			if (colorChange == true) {
				eventChat.setFormat(">> "+ playerName + " < " + ChatColor.GRAY + message);
				colorChange = false;
			} else {
				eventChat.setFormat(">> "+ playerName + " < " + ChatColor.WHITE + message);
				colorChange = true;
			}
			
		}
		
		
	}

	

}

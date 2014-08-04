package es.bewom.bewomBit;

import java.lang.reflect.Array;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import es.bewom.bewomBit.commands.commandCd;

public class chatPlayer implements Listener {
	
	public static Scoreboard board;
	public static Player playerArgsCraft;
	public static Team playerArgsNameTeam;

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent eventChat) {
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
			
		String message = eventChat.getMessage();
		Player craftPlayer = eventChat.getPlayer(); //craftPlayer Player
		String playerName = eventChat.getPlayer().getName(); //limpio String 
		
		String admin = ChatColor.DARK_RED + "" + ChatColor.BOLD + playerName + ChatColor.RESET + " < " + ChatColor.WHITE  + ChatColor.BOLD;
		String mod = ChatColor.DARK_GREEN + "" + ChatColor.BOLD + playerName + ChatColor.RESET + " < " + ChatColor.WHITE  + ChatColor.BOLD;
		String vip =  ChatColor.DARK_AQUA + playerName + " < ";
		String steve =  playerName + " < ";
		
		
		if (craftPlayer.hasPermission("bewom.admin")) {
			
			if (board.getPlayerTeam(craftPlayer) == playerArgsNameTeam) {

				playerArgsNameTeam.addPlayer(craftPlayer);
				
				String DisplayName3 = playerArgsNameTeam.getDisplayName();
				
				String adminPrivateMe =  ChatColor.YELLOW + "/" +  DisplayName3 + "/" + admin + message;
				
				String adminPrivate =  ChatColor.YELLOW + "/p/" + admin + message;
				
				craftPlayer.sendMessage(adminPrivateMe);
				playerArgsCraft.sendMessage(adminPrivate);
				
				eventChat.setCancelled(true);
				
			}
			
			eventChat.setFormat(ChatColor.DARK_AQUA + "/" + admin + message);
			
		} else if (craftPlayer.hasPermission("bewom.mod")) {
			eventChat.setFormat(ChatColor.DARK_AQUA + "/" + mod + message);
			
		} else if (craftPlayer.hasPermission("bewom.vip")) {
			eventChat.setFormat(ChatColor.DARK_AQUA + "/" + vip + message);
			
		} else {
			eventChat.setFormat(ChatColor.DARK_AQUA + "/" + steve + message);
			
		}
			
		
	}
	

}

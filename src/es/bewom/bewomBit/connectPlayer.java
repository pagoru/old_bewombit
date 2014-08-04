package es.bewom.bewomBit;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class connectPlayer implements Listener {
	
	public static String[][] playerArray = new String[1200][2];
	public static String chatPlayerName;
	public static Scoreboard board;
	public static Player playerArgsCraft;
	public static Team playerArgsNameTeamAdmin;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException {
		
		Player craftPlayer = eventConnect.getPlayer(); //craftPlayer Player
		String playerName = eventConnect.getPlayer().getName(); //limpio String 
		
		eventConnect.setJoinMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha entrado en el servidor.");
		
		// ---> chats
		
		chatPlayerName = playerName + "_chat";
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		
		playerArgsNameTeamAdmin = board.getTeam(playerName + "_ad");
		
		
		// ---> Scoreboards nicks <--- //
		
		bewomBit.teamAdmin.removePlayer(craftPlayer);
		bewomBit.teamMod.removePlayer(craftPlayer);
		bewomBit.teamVip.removePlayer(craftPlayer);
		
		if (craftPlayer.hasPermission("bewom.admin")) {
			Bukkit.getLogger().info("player " + playerName +" is administrator");
			bewomBit.teamAdmin.addPlayer(craftPlayer);
			
			
		} else if (craftPlayer.hasPermission("bewom.mod")) {
			Bukkit.getLogger().info("player " + playerName +" is moderator.");
			bewomBit.teamMod.addPlayer(craftPlayer);
		
			
		} else if (craftPlayer.hasPermission("bewom.vip")) {
			Bukkit.getLogger().info("player "+ playerName +" is vip.");
			bewomBit.teamVip.addPlayer(craftPlayer);
			
			
		} else {
			
		}
	}

}

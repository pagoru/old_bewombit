package es.bewom.bewomBit;

import java.lang.reflect.Array;
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

import es.bewom.bewomBit.commands.commandCd;

public class connectPlayer implements Listener {
	public static String[][] playerArray = new String[1200][2];
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException {
		
		Player craftPlayer = eventConnect.getPlayer(); //craftPlayer Player
		String playerName = eventConnect.getPlayer().getName(); //limpio String 
		
		eventConnect.setJoinMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha entrado en el servidor.");
		
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

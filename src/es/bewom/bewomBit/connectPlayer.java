package es.bewom.bewomBit;

import java.sql.SQLException;
  
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class connectPlayer implements Listener {
	//Paco
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException {
		
		Player craftPlayer = eventConnect.getPlayer(); //craftPlayer Player
		String playerName = eventConnect.getPlayer().getName(); //limpio String 
		
		bewomBit.teamAdmin.removePlayer(craftPlayer);
		bewomBit.teamMod.removePlayer(craftPlayer);
		bewomBit.teamVip.removePlayer(craftPlayer);
		
		if (craftPlayer.hasPermission("bewom.admin")) {
			Bukkit.getLogger().info("[bewomBit] "+ playerName +" >> admin.");
			bewomBit.teamAdmin.addPlayer(craftPlayer);
			
		} else if (craftPlayer.hasPermission("bewom.mod")) {
			Bukkit.getLogger().info("[bewomBit] "+ playerName +" >> mod.");
			bewomBit.teamMod.addPlayer(craftPlayer);
			
		} else if (craftPlayer.hasPermission("bewom.vip")) {
			Bukkit.getLogger().info("[bewomBit] "+ playerName +" >> vip.");
			bewomBit.teamVip.addPlayer(craftPlayer);
		}
	}

}

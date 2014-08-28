package es.bewom.bewomBit.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventsWelcomePlayer {

	public static void connectPlayerEventsWelcomePlayer (PlayerJoinEvent eventConnect){

		String playerName = eventConnect.getPlayer().getName(); //limpio String 
		
		eventConnect.setJoinMessage("");
		
		for(Player craftPlayer: Bukkit.getServer().getOnlinePlayers()){
			
			if(!craftPlayer.getName().equals(playerName)){
				
				craftPlayer.sendMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha entrado en el servidor.");
				
			} 
			
		}

	}
	
	public static void connectPlayerEventsGoodbyePlayer (PlayerQuitEvent eventQuit){

		String playerName = eventQuit.getPlayer().getName(); //limpio String 
		
		eventQuit.setQuitMessage("");
		
		for(Player craftPlayer: Bukkit.getServer().getOnlinePlayers()){
			
			if(!craftPlayer.getName().equals(playerName)){
				
				craftPlayer.sendMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha salido del servidor.");	
				
			}
			
		}

	}
}
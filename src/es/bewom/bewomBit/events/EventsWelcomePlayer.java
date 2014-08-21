package es.bewom.bewomBit.events;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventsWelcomePlayer {

	public static void connectPlayerEventsWelcomePlayer (PlayerJoinEvent eventConnect){

		String playerName = eventConnect.getPlayer().getName(); //limpio String 
		
		eventConnect.setJoinMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha entrado en el servidor.");	

	}
	
	public static void connectPlayerEventsGoodbyePlayer (PlayerQuitEvent eventQuit){

		String playerName = eventQuit.getPlayer().getName(); //limpio String 
		
		eventQuit.setQuitMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha salido del servidor.");	

	}
}
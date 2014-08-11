package es.bewom.bewomBit.events;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;

public class eventsWelcomePlayer {

	public static void connectPlayerEventsWelcomePlayer (PlayerJoinEvent eventConnect){

		String playerName = eventConnect.getPlayer().getName(); //limpio String 

		eventConnect.setJoinMessage(ChatColor.GRAY + playerName + ChatColor.GRAY + " ha entrado en el servidor.");	

	}
}
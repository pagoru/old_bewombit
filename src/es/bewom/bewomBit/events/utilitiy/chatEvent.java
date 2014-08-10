package es.bewom.bewomBit.events.utilitiy;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import es.bewom.bewomBit.events.eventsChatAntiSpam;
import es.bewom.bewomBit.events.eventsCongelar;

public class chatEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent eventChat) {

		eventsChatAntiSpam.onPlayerChatEventsAntiSpam(eventChat);
		eventsCongelar.onPlayerChatEventsCongelar(eventChat);
		
	}
}

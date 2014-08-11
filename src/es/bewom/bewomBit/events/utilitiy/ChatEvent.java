package es.bewom.bewomBit.events.utilitiy;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import es.bewom.bewomBit.events.EventsChatAntiSpam;
import es.bewom.bewomBit.events.EventsCongelar;

public class ChatEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent eventChat) {

		EventsChatAntiSpam.onPlayerChatEventsAntiSpam(eventChat);
		EventsCongelar.onPlayerChatEventsCongelar(eventChat);
		
	}
}

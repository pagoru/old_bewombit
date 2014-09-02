package es.bewom.bewomBit.utility.events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import es.bewom.bewomBit.groups.chat.EventsChat;
import es.bewom.bewomBit.groups.congelar.EventsCongelar;

public class ChatEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent eventChat) throws FileNotFoundException, IOException, InvalidConfigurationException {

		EventsChat.onPlayerChatEventsAntiSpam(eventChat);
		EventsCongelar.onPlayerChatEventsCongelar(eventChat);
		
	}
}

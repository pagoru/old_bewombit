package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import es.bewom.bewomBit.events.eventsCongelar;

public class preprocessCommandEvent implements Listener  {
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPreprocessCommandEvent(PlayerCommandPreprocessEvent eventPreprocessCommand) throws SQLException, IOException {
		
		eventsCongelar.onPreprocessCommandEvent(eventPreprocessCommand);
		
	}
}

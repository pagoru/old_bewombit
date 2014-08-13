package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import es.bewom.bewomBit.events.EventsCongelar;

public class PreprocessCommandEvent implements Listener  {
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPreprocessCommandEvent(PlayerCommandPreprocessEvent eventPreprocessCommand) throws SQLException, IOException, InvalidConfigurationException {
		
		EventsCongelar.onPreprocessCommandEvent(eventPreprocessCommand);
		
	}
}

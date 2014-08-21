package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import es.bewom.bewomBit.events.EventsWelcomePlayer;

public class QuitEvent implements Listener  {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent eventQuit) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
		EventsWelcomePlayer.connectPlayerEventsGoodbyePlayer(eventQuit);
		
	}
	
}

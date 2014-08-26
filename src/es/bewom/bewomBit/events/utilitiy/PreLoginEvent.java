package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import es.bewom.bewomBit.events.EventsBaneos;
import es.bewom.bewomBit.events.EventsWhitelist;

public class PreLoginEvent implements Listener {
	
	@EventHandler
	public void onPreJoin(AsyncPlayerPreLoginEvent eventPreConnect) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
		EventsBaneos.onPreJoin(eventPreConnect);
		EventsWhitelist.onPreJoin(eventPreConnect);
		
	}
	
}

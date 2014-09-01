package es.bewom.bewomBit.utility.events;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import es.bewom.bewomBit.groups.ban.EventsBan;
import es.bewom.bewomBit.groups.whitelist.EventsWhitelist;

public class PreLoginEvent implements Listener {
	
	@EventHandler
	public void onPreJoin(AsyncPlayerPreLoginEvent eventPreConnect) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
		EventsBan.onPreJoin(eventPreConnect);
		EventsWhitelist.onPreJoin(eventPreConnect);
		
	}
	
}

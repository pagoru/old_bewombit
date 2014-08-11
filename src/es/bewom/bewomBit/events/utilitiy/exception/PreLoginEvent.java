package es.bewom.bewomBit.events.utilitiy.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import es.bewom.bewomBit.events.EventsPermissions;

public class PreLoginEvent implements Listener  {
	
	@EventHandler
	public void onPreLogin(AsyncPlayerPreLoginEvent eventPreLogin) throws SQLException, IOException {
		
		EventsPermissions.onPreLogin(eventPreLogin);
		
	}
	
}
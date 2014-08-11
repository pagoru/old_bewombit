package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.bewom.bewomBit.events.EventsPerfiles;
import es.bewom.bewomBit.events.EventsPermissions;
import es.bewom.bewomBit.events.EventsWelcomePlayer;

public class JoinEvent implements Listener  {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException {
		
		EventsPermissions.onJoin(eventConnect);
		EventsPerfiles.connectPlayerEventsPerfiles(eventConnect);	
		EventsWelcomePlayer.connectPlayerEventsWelcomePlayer(eventConnect);
		
	}
	
}

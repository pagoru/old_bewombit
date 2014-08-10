package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.bewom.bewomBit.events.eventsPerfiles;
import es.bewom.bewomBit.events.eventsPermissions;
import es.bewom.bewomBit.events.eventsWelcomePlayer;

public class joinEvent implements Listener  {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException {
		
		eventsPermissions.onJoin(eventConnect);
		eventsPerfiles.connectPlayerEventsPerfiles(eventConnect);	
		eventsWelcomePlayer.connectPlayerEventsWelcomePlayer(eventConnect);
		
	}
	
}

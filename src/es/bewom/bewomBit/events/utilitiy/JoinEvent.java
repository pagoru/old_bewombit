package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.bewom.bewomBit.events.EventsPerfiles;
import es.bewom.bewomBit.events.EventsPermissions;
import es.bewom.bewomBit.events.EventsSpawn;
import es.bewom.bewomBit.events.EventsWelcomePlayer;

public class JoinEvent implements Listener  {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
		EventsPerfiles.connectPlayerEventsPerfiles(eventConnect);	
		EventsPermissions.onJoin(eventConnect);
		EventsWelcomePlayer.connectPlayerEventsWelcomePlayer(eventConnect);
		EventsSpawn.onJoin(eventConnect);
		
	}
	
}

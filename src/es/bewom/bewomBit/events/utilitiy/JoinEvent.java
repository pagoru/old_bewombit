package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.bewom.bewomBit.events.EventsAmigos;
import es.bewom.bewomBit.events.EventsGuardar;
import es.bewom.bewomBit.events.EventsPerfiles;
import es.bewom.bewomBit.events.EventsPermissions;
import es.bewom.bewomBit.events.EventsSpawn;
import es.bewom.bewomBit.events.EventsWelcomePlayer;

public class JoinEvent implements Listener  {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
		EventsWelcomePlayer.connectPlayerEventsWelcomePlayer(eventConnect);
		EventsPermissions.onJoin(eventConnect);
		EventsSpawn.onJoin(eventConnect);
		EventsPerfiles.connectPlayerEventsPerfiles(eventConnect);	
		EventsGuardar.onJoin(eventConnect);
		EventsAmigos.onJoin(eventConnect);
		
	}
	
}

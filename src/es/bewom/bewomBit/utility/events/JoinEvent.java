package es.bewom.bewomBit.utility.events;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.bewom.bewomBit.groups.amigos.EventsAmigos;
import es.bewom.bewomBit.groups.perfiles.EventsPerfiles;
import es.bewom.bewomBit.groups.perfiles.EventsWelcomePlayer;
import es.bewom.bewomBit.groups.permisos.EventsPermissions;
import es.bewom.bewomBit.groups.spawn.EventsSpawn;

public class JoinEvent implements Listener  {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
		EventsWelcomePlayer.connectPlayerEventsWelcomePlayer(eventConnect);
		EventsPermissions.onJoin(eventConnect);
		EventsSpawn.onJoin(eventConnect);
		EventsPerfiles.connectPlayerEventsPerfiles(eventConnect);	
		EventsAmigos.onJoin(eventConnect);
		
	}
	
}

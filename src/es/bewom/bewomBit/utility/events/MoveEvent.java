package es.bewom.bewomBit.utility.events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.groups.congelar.EventsCongelar;
import es.bewom.bewomBit.groups.god.EventsInmortal;
import es.bewom.bewomBit.groups.speed.EventsSaltarImpulso;
import es.bewom.bewomBit.groups.teleport.EventsTeleport;

public class MoveEvent implements Listener{

	public static BewomBit plugin;

	public MoveEvent (BewomBit instance){
		plugin = instance;
	}

	@EventHandler
	public void onPlayerMove (PlayerMoveEvent eventMove) throws FileNotFoundException, IOException, InvalidConfigurationException, ClassNotFoundException, SQLException, ParseException{
		
		EventsCongelar.movePlayerEventsCongelar (eventMove);
		EventsSaltarImpulso.movePlayerEventsSaltarImpulso(eventMove);
		EventsInmortal.onPlayerMove(eventMove);
		EventsTeleport.onPlayerMove(eventMove);
		
	}
}

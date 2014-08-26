package es.bewom.bewomBit.events.utilitiy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.events.EventsCongelar;
import es.bewom.bewomBit.events.EventsInmortal;
import es.bewom.bewomBit.events.EventsSaltarImpulso;
import es.bewom.bewomBit.events.EventsTeleport;

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

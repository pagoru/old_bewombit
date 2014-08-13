package es.bewom.bewomBit.events.utilitiy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.events.EventsCongelar;
import es.bewom.bewomBit.events.EventsSaltarImpulso;

public class MoveEvent implements Listener{

	public static BewomBit plugin;

	public MoveEvent (BewomBit instance){
		plugin = instance;
	}

	@EventHandler
	public void onPlayerMove (PlayerMoveEvent eventMove){
		
		EventsCongelar.movePlayerEventsCongelar (eventMove);
		EventsSaltarImpulso.movePlayerEventsSaltarImpulso(eventMove);
		
	}
}
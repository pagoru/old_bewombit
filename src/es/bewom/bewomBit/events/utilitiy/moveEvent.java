package es.bewom.bewomBit.events.utilitiy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import es.bewom.bewomBit.bewomBit;
import es.bewom.bewomBit.events.eventsCongelar;
import es.bewom.bewomBit.events.eventsSaltarImpulso;

public class moveEvent implements Listener{

	public static bewomBit plugin;

	public moveEvent (bewomBit instance){
		plugin = instance;
	}

	@EventHandler
	public void onPlayerMove (PlayerMoveEvent eventMove){
		
		eventsCongelar.movePlayerEventsCongelar (eventMove);
		eventsSaltarImpulso.movePlayerEventsSaltarImpulso(eventMove);
		
	}
}

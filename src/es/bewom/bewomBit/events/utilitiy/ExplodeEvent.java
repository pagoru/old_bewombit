package es.bewom.bewomBit.events.utilitiy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import es.bewom.bewomBit.events.EventsProteccionExplosiones;

public class ExplodeEvent implements Listener {
	
	@EventHandler
	public void onExplode(EntityExplodeEvent eventExplode) throws InterruptedException{
		
		EventsProteccionExplosiones.onExplode(eventExplode);
		
	}

}

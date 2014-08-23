package es.bewom.bewomBit.events;

import org.bukkit.event.entity.EntityExplodeEvent;

public class EventsProteccionExplosiones {
	
	public static void onExplode(EntityExplodeEvent eventExplode) throws InterruptedException{
		
		eventExplode.setCancelled(true);
		
	}
	
}

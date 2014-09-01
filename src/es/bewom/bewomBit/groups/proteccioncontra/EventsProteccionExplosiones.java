package es.bewom.bewomBit.groups.proteccioncontra;

import org.bukkit.event.entity.EntityExplodeEvent;

public class EventsProteccionExplosiones {
	
	public static void onExplode(EntityExplodeEvent eventExplode) throws InterruptedException{
		
		eventExplode.setCancelled(true);
		
	}
	
}

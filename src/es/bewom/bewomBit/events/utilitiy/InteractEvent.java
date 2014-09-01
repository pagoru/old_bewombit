package es.bewom.bewomBit.events.utilitiy;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import es.bewom.bewomBit.events.EventsCongelar;
import es.bewom.bewomBit.events.EventsLogBlock;
import es.bewom.bewomBit.events.EventsP;
import es.bewom.bewomBit.events.EventsProteccionContra;
import es.bewom.bewomBit.events.EventsTeleport;

public class InteractEvent implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent eventInteract) throws Exception{
		
		EventsProteccionContra.playerInteractEventsP(eventInteract);
		EventsP.playerInteractEventsP(eventInteract);
		EventsCongelar.onPlayerInteract(eventInteract);
		EventsTeleport.playerInteractEventsP(eventInteract);
		EventsLogBlock.onPlayerInteract(eventInteract);
		
	}

}

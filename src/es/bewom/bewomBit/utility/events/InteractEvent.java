package es.bewom.bewomBit.utility.events;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import es.bewom.bewomBit.groups.congelar.EventsCongelar;
import es.bewom.bewomBit.groups.loglbock.EventsLogBlock;
import es.bewom.bewomBit.groups.p.EventsP;
import es.bewom.bewomBit.groups.proteccioncontra.EventsProteccionContra;
import es.bewom.bewomBit.groups.teleport.EventsTeleport;

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

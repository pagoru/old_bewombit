package es.bewom.bewomBit.events.utilitiy;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import es.bewom.bewomBit.events.EventsLogBlock;
import es.bewom.bewomBit.events.EventsP;
import es.bewom.bewomBit.events.EventsRxray;
import es.bewom.bewomBit.events.EventsSpawner;

public class PlaceBlockEvent implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void OnPlace(BlockPlaceEvent eventPlace) throws Exception {
		
		EventsP.blockPlacePlayerEventsP(eventPlace);
		EventsSpawner.OnPlace(eventPlace);
		EventsRxray.OnPlace(eventPlace);
		EventsLogBlock.OnPlace(eventPlace);
		
	}

}

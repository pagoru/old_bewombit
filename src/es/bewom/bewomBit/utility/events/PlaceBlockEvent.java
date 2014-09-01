package es.bewom.bewomBit.utility.events;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import es.bewom.bewomBit.groups.loglbock.EventsLogBlock;
import es.bewom.bewomBit.groups.p.EventsP;
import es.bewom.bewomBit.groups.spawner.EventsSpawner;
import es.bewom.bewomBit.groups.xray.EventsRxray;

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

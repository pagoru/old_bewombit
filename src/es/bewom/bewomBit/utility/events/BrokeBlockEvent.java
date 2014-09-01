package es.bewom.bewomBit.utility.events;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import es.bewom.bewomBit.groups.loglbock.EventsLogBlock;
import es.bewom.bewomBit.groups.p.EventsP;
import es.bewom.bewomBit.groups.proteccioncontra.EventsProteccionContra;
import es.bewom.bewomBit.groups.spawner.EventsSpawner;
import es.bewom.bewomBit.groups.xray.EventsRxray;

public class BrokeBlockEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void OnBreak(BlockBreakEvent eventBroke) throws Exception {
		
		EventsProteccionContra.OnBreak(eventBroke);
		EventsP.brokeBlockPlayerEventsP (eventBroke);
		EventsSpawner.brokeBlockPlayerEventsP(eventBroke);
		EventsRxray.OnBreak(eventBroke);
		EventsLogBlock.OnBreak(eventBroke);
		
	}

}
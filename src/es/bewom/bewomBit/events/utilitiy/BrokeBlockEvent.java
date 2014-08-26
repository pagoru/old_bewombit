package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import es.bewom.bewomBit.events.EventsP;
import es.bewom.bewomBit.events.EventsProteccionContra;
import es.bewom.bewomBit.events.EventsRxray;
import es.bewom.bewomBit.events.EventsSpawner;

public class BrokeBlockEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void OnBreak(BlockBreakEvent eventBroke) throws SQLException, IOException, InvalidConfigurationException {
		
		EventsProteccionContra.OnBreak(eventBroke);
		EventsP.brokeBlockPlayerEventsP (eventBroke);
		EventsSpawner.brokeBlockPlayerEventsP(eventBroke);
		EventsRxray.OnBreak(eventBroke);
		
	}

}
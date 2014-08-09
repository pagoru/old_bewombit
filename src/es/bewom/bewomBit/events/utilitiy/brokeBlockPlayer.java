package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import es.bewom.bewomBit.events.eventsCongelar;
import es.bewom.bewomBit.events.eventsP;

public class brokeBlockPlayer implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void OnBreak(BlockBreakEvent eventPlace) throws SQLException, IOException {
		
		eventsP.brokeBlockPlayerEventsP (eventPlace);
		eventsCongelar.brokeBlockPlayerEventsCongelar(eventPlace);
		
	}

}
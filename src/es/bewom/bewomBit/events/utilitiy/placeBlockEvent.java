package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import es.bewom.bewomBit.events.eventsCongelar;
import es.bewom.bewomBit.events.eventsP;

public class placeBlockEvent implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void OnPlace(BlockPlaceEvent eventPlace) throws SQLException, IOException {
		
		eventsCongelar.blockPlacePlayerEventsCongelar(eventPlace);
		eventsP.blockPlacePlayerEventsP(eventPlace);
		
	}

}

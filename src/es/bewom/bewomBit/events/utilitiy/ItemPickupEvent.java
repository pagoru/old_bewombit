package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

import es.bewom.bewomBit.events.EventsCofreMuerto;

public class ItemPickupEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void OnPickUp(InventoryPickupItemEvent eventPickUp) throws SQLException, IOException {
		
		
	}

}
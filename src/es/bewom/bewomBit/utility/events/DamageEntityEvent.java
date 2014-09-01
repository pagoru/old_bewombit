package es.bewom.bewomBit.utility.events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import es.bewom.bewomBit.groups.god.EventsInmortal;

public class DamageEntityEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void onDamageEntity(EntityDamageEvent eventDamage) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		EventsInmortal.onDamageEntity(eventDamage);
		
	}
	
}

package es.bewom.bewomBit.events.utilitiy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import es.bewom.bewomBit.events.EventsCongelar;
import es.bewom.bewomBit.events.EventsP;

public class InteractEvent implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent eventInteract) throws FileNotFoundException, IOException, InvalidConfigurationException{
	
		EventsP.playerInteractEventsP(eventInteract);
		EventsCongelar.onPlayerInteract(eventInteract);
	
	}

}

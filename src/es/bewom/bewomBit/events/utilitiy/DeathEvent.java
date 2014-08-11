package es.bewom.bewomBit.events.utilitiy;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import es.bewom.bewomBit.events.EventsP;

public class DeathEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent eventDeath){
		
		EventsP.deathPlayerEventsP (eventDeath);
		
	}    
}

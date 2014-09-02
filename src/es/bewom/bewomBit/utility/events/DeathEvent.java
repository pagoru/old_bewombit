package es.bewom.bewomBit.utility.events;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import es.bewom.bewomBit.groups.morir.EventsAlMorir;

public class DeathEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent eventDeath) throws InterruptedException{
		
		EventsAlMorir.deathCofreMuerto(eventDeath);
		
	}    
}
package es.bewom.bewomBit.events;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsTeleport {
	
	@SuppressWarnings("unused")
	public static void playerInteractEventsP(PlayerInteractEvent eventInteract) throws FileNotFoundException, IOException, InvalidConfigurationException{

		String playerUUID = eventInteract.getPlayer().getUniqueId().toString();
		String playerName = eventInteract.getPlayer().getName();
		Player craftPlayer = (Player) eventInteract.getPlayer();
		
	}
	
}

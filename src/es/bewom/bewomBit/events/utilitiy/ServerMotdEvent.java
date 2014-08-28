package es.bewom.bewomBit.events.utilitiy;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import es.bewom.bewomBit.events.EventsServerMOTD;

public class ServerMotdEvent implements Listener {

	@EventHandler
	public void LagMOTD(ServerListPingEvent pingEvent) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		EventsServerMOTD.serverMOTDEvents (pingEvent);
		
	}
}
package es.bewom.bewomBit.events.utilitiy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import es.bewom.bewomBit.events.eventsServerMOTD;

public class serverMotdEvent implements Listener {

	@EventHandler
	public void LagMOTD(ServerListPingEvent pingEvent){
		eventsServerMOTD.serverMOTDEvents (pingEvent);
	}
}
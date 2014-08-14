package es.bewom.bewomBit.events.utilitiy;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import es.bewom.bewomBit.events.EventsSignChange;

public class ChangeSignEvent implements Listener{

	@EventHandler
	public void onSignChangeEvent(SignChangeEvent eventSign){
		
		EventsSignChange.onSignChangeEvent(eventSign);
		
	}
}


package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener  {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent eventQuit) throws SQLException, IOException {
		
		
	}
	
}
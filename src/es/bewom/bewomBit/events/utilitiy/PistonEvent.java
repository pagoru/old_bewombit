package es.bewom.bewomBit.events.utilitiy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class PistonEvent implements Listener {

	static Logger log = Logger.getLogger("Minecraft");

	@EventHandler
	public void EventPiston(BlockPistonExtendEvent eventPiston) throws SQLException, IOException{
				
	}

}
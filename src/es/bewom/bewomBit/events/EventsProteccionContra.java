package es.bewom.bewomBit.events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsProteccionContra {
	
	public static void playerInteractEventsP(PlayerInteractEvent eventInteract) throws FileNotFoundException, IOException, InvalidConfigurationException{

		Player craftPlayer = (Player) eventInteract.getPlayer();

		if (eventInteract.getAction() == Action.RIGHT_CLICK_BLOCK){
	
			if(craftPlayer.getItemInHand().getType().equals(Material.HOPPER_MINECART) 
					|| craftPlayer.getItemInHand().getType().equals(Material.DISPENSER)){
	
				eventInteract.setCancelled(true);
	
			}
		}
		
		
	}
	
	public static void OnBreak(BlockBreakEvent eventBroke) throws SQLException, IOException, InvalidConfigurationException {
	
		//---> Protección Hopper contra cofre y cofre trampa
		
		if(eventBroke.getBlock().getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR 
				&& eventBroke.getBlock().getLocation().add(0, 2, 0).getBlock().getType() == Material.WOODEN_DOOR){
			
			eventBroke.setCancelled(true);
			
		}
		
	}
}

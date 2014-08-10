package es.bewom.bewomBit.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class eventsSpawner {
	
	@SuppressWarnings({ "unused"})
	public static void brokeBlockPlayerEventsP(BlockBreakEvent eventBroke){

		String playerUUID = eventBroke.getPlayer().getUniqueId().toString();
		String playerName = eventBroke.getPlayer().getName();
		Player craftPlayer = (Player) eventBroke.getPlayer();
		
		if(eventBroke.getBlock().getType().equals(Material.MOB_SPAWNER)){
			
			Material spawner = eventBroke.getBlock().getType();
			int spawnerData = eventBroke.getBlock().getType().getData().getModifiers();
			
			ItemStack item = new ItemStack(spawner, spawnerData);
			eventBroke.getPlayer().getWorld().dropItem(eventBroke.getBlock().getLocation(), item);
			
		}
	
		
	}
}

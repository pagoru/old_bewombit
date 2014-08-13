package es.bewom.bewomBit.events;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventsSpawner {

	static Logger log = Logger.getLogger("Minecraft");

	public static void brokeBlockPlayerEventsP(BlockBreakEvent eventBroke){

		if(eventBroke.getBlock().getType().equals(Material.MOB_SPAWNER)){

			Material spawnerState = Material.MOB_SPAWNER;

			Location block = eventBroke.getBlock().getLocation();
			CreatureSpawner spawner = ((CreatureSpawner) block.getBlock().getState());

			String spawnerName = spawner.getCreatureTypeName().toString();			

			ItemStack spawnerItem = new ItemStack(spawnerState);

			ItemMeta meta = (ItemMeta) spawnerItem.getItemMeta();
			meta.setDisplayName(ChatColor.AQUA + spawnerName);
			spawnerItem.setItemMeta(meta);

			eventBroke.getPlayer().getWorld().dropItem(eventBroke.getBlock().getLocation(), spawnerItem);			
		}		
	}

	public static void OnPlace(BlockPlaceEvent eventPlace) throws SQLException, IOException, InterruptedException {

		Player craftPlayer = (Player) eventPlace.getPlayer();

		ItemStack itemInHand = craftPlayer.getItemInHand();
		ItemMeta meta = (ItemMeta) itemInHand.getItemMeta();

		if(eventPlace.getBlock().getType().equals(Material.MOB_SPAWNER)){

			Location block = eventPlace.getBlock().getLocation();
			
			if (meta.getDisplayName() != null) {
				
				if (meta.getDisplayName().equals(ChatColor.AQUA + "Blaze")){				
					clasificarSpawner (block, "Blaze");			
				}
				else if (meta.getDisplayName().equals(ChatColor.AQUA + "Zombie")){				
					clasificarSpawner (block, "Zombie");			
				}
				else if (meta.getDisplayName().equals(ChatColor.AQUA + "Skeleton")){				
					clasificarSpawner (block, "Skeleton");			
				}
				else if (meta.getDisplayName().equals(ChatColor.AQUA + "Spider")){				
					clasificarSpawner (block, "Spider");			
				}
				else if (meta.getDisplayName().equals(ChatColor.AQUA + "CaveSpider")){				
					clasificarSpawner (block, "CaveSpider");			
				}
				else {				
					eventPlace.setCancelled(true);				
				}			
				
			} else {	
				
				eventPlace.setCancelled(true);				
			}
			
		}		
	}

	public static void clasificarSpawner (Location block, String mob){

		CreatureSpawner spawner = ((CreatureSpawner) block.getBlock().getState());
		spawner.setCreatureTypeByName(mob);
		spawner.setDelay(0);
		spawner.update();
	}
}
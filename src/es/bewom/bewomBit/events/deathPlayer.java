package es.bewom.bewomBit.events;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class deathPlayer implements Listener {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent eventDeath){
		
	    Player playerCraft = eventDeath.getEntity();
	    
	    String playerName = playerCraft.getName();
    	
    	int X = playerCraft.getLocation().getBlockX();
    	int Y = playerCraft.getLocation().getBlockY();
    	int Z = playerCraft.getLocation().getBlockZ();
    	
    	Bukkit.getWorld("world").getBlockAt(X, Y, Z).setType(Material.CHEST);
        Location loc = new Location(Bukkit.getWorld("world"), X, Y + 1, Z);
        loc.getBlock().setType(Material.SKULL);
        BlockState state = loc.getBlock().getState();
        
        Skull s = (Skull) state;
        s.setSkullType(SkullType.PLAYER);
        s.setOwner(playerName);
        loc.getBlock().getChunk().load();
        s.update(true);
        
        log.info("muerto!" + playerName);
    	
	}
	
	
//    
}

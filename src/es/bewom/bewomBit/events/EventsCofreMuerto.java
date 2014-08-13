package es.bewom.bewomBit.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventsCofreMuerto {
	
	@EventHandler
	public static void deathCofreMuerto (PlayerDeathEvent eventDeath) throws InterruptedException{

		Player playerCraft = eventDeath.getEntity();
		String playerName = playerCraft.getName();

		int X = playerCraft.getLocation().getBlockX();
		int Y = playerCraft.getLocation().getBlockY();
		int Z = playerCraft.getLocation().getBlockZ();

		Bukkit.getWorld("world").getBlockAt(X, Y, Z).setType(Material.STONE);
				
		Location loc = new Location(Bukkit.getWorld("world"), X, Y+1, Z);
		loc.getBlock().setType(Material.SKULL);
				
		BlockState state = loc.getBlock().getState();
		
		org.bukkit.material.Skull s = new org.bukkit.material.Skull(Material.SKULL);
		s.setFacingDirection(BlockFace.WEST_SOUTH_WEST);
		
		Skull sa = (Skull) state.getBlock().getState();
		sa.setSkullType(SkullType.PLAYER);
		sa.setOwner(playerName);
		sa.setData(s);
		
		loc.getBlock().getChunk().load();
		
		sa.update(true);
		
		Bukkit.getWorld("world").getBlockAt(X, Y, Z).setType(Material.CHEST);
		
	}
		
}

package es.bewom.bewomBit.groups.morir;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EventsAlMorir {
		
	public static void deathCofreMuerto (PlayerDeathEvent eventDeath) throws InterruptedException{
		
		Player player = eventDeath.getEntity().getPlayer();
		
		ItemStack bones = new ItemStack(Material.BONE, 3, (short) 3);
		ItemStack rotten = new ItemStack(Material.ROTTEN_FLESH, 2, (short) 3);
		
		Bukkit.getServer().getWorld("world").dropItem(player.getLocation(), bones);
		Bukkit.getServer().getWorld("world").dropItem(player.getLocation(), rotten);
		
//		player.sendMessage(player.getDisplayName());
//		
//		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
//		SkullMeta meta = (SkullMeta) i.getItemMeta();
//		meta.setOwner(player.getDisplayName());
//		i.setItemMeta(meta);
//		
//		
//		Zombie zomb = (Zombie) player.getWorld().spawnCreature(player.getLocation(), EntityType.ZOMBIE);
//		zomb.getEquipment().setHelmet(i);
//		zomb.setCustomName(player.getDisplayName());
//		zomb.setBaby(false);
//		zomb.setVillager(false);
//
//		Player playerCraft = eventDeath.getEntity();
//		String playerName = playerCraft.getName();
//
//		int X = playerCraft.getLocation().getBlockX();
//		int Y = playerCraft.getLocation().getBlockY();
//		int Z = playerCraft.getLocation().getBlockZ();
//
//		Bukkit.getWorld("world").getBlockAt(X, Y, Z).setType(Material.STONE);
//				
//		Location loc = new Location(Bukkit.getWorld("world"), X, Y+1, Z);
//		loc.getBlock().setType(Material.SKULL);
//				
//		BlockState state = loc.getBlock().getState();
//		
//		org.bukkit.material.Skull s = new org.bukkit.material.Skull(Material.SKULL);
//		s.setFacingDirection(BlockFace.WEST_SOUTH_WEST);
//		
//		Skull sa = (Skull) state.getBlock().getState();
//		sa.setSkullType(SkullType.PLAYER);
//		sa.setOwner(playerName);
//		sa.setData(s);
//		
//		loc.getBlock().getChunk().load();
//		
//		sa.update(true);
//		
//		Bukkit.getWorld("world").getBlockAt(X, Y, Z).setType(Material.CHEST);
		
	}
		
}

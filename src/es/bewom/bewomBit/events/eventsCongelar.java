package es.bewom.bewomBit.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class eventsCongelar {
	public static void brokeBlockPlayerEventsCongelar (BlockBreakEvent eventPlace){

		Player craftPlayer = (Player) eventPlace.getPlayer();
		boolean playerIsCongelado = false;
		boolean isCongelado = false;

		if (!craftPlayer.hasPermission("bewom.admin") || !craftPlayer.hasPermission("bewom.mod")){
			if (playerIsCongelado || isCongelado){

				eventPlace.setCancelled(true);
				craftPlayer.sendMessage(ChatColor.RED + "Has sido congelado temporalmente.");
			}
		}
	}
}

package es.bewom.bewomBit.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import es.bewom.bewomBit.bewomBit;

public class movePlayer implements Listener{

	public static bewomBit plugin;

	public movePlayer (bewomBit instance){
		plugin = instance;
	}

	@EventHandler
	public void onPlayerMove (PlayerMoveEvent event){
		if (bewomBit.isCongelar()){
			Player player = event.getPlayer();
			if (!player.hasPermission("bewom.admin") || !player.hasPermission("bewom.mod")){
				player.teleport(player);
				player.sendMessage(ChatColor.RED + "Todos los usuarios han sido congelados temporalmente.");
			}
		}
	}
}

package es.bewom.bewomBit.events;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import es.bewom.bewomBit.bewomBit;

public class eventsPermissions {
	
	private static final eventsPermissions instance = new eventsPermissions();
	
	public static final eventsPermissions getPlugin() {
		
		return instance;
		}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException {
		
		Player player = eventConnect.getPlayer();
		
		player.setBanned(true);
		
		PermissionAttachment attachment = player.addAttachment(bewomBit.main);
		
		attachment.setPermission("bewom.vip", true);
		
	}
	
}

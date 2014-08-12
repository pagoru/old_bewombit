package es.bewom.bewomBit.events;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import es.bewom.bewomBit.BewomBit;

public class EventsPermissions {

	private static final EventsPermissions instance = new EventsPermissions();

	public static final EventsPermissions getPlugin() {		
		return instance;
	}

	@EventHandler
	public static void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException {
		
		Player player = eventConnect.getPlayer();
		
		PermissionAttachment attachment = player.addAttachment(BewomBit.main);
		
		attachment.setPermission("bewom.vip", true);
		
	}
	
	@EventHandler
	public static void onPreLogin(AsyncPlayerPreLoginEvent eventPreLogin) throws SQLException, IOException {
		
//		String hola = ChatColor.DARK_AQUA + "Has sido baneado" + "asdddbdddddddddddddd asdddbdddddddddddddd asdddbdddddddddddddd asdddbdddddddddddddd ";
//		
//		Result disable = AsyncPlayerPreLoginEvent.Result.KICK_OTHER;
//		
//		eventPreLogin.disallow(disable, hola);
		
	}
	
}

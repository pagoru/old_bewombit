package es.bewom.bewomBit.events;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class EventsPermissions {
	
	static Logger log = Logger.getLogger("Minecraft");
	private static final EventsPermissions instance = new EventsPermissions();

	public static final EventsPermissions getPlugin() {		
		return instance;
	}

	@EventHandler
	public static void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException, ClassNotFoundException {
		
		Player player = eventConnect.getPlayer();
		
		PermissionAttachment attachment = player.addAttachment(BewomBit.main);
		
		attachment.setPermission("bewom.vip", true);
		
		
		MySQL connection = new MySQL(BewomBit.main, "localhost", "3306", "bewomBit", "root", "");
		connection.openConnection();
		Statement statement = connection.getConnection().createStatement();
		ResultSet aaa = statement.executeQuery("SELECT * FROM `permissions` WHERE `playerName` = '" + player.getName() + "';");
		
		
		if(aaa.next()){
			
			player.sendMessage(aaa.getString("group"));
		
		}
		
		statement.close();
		connection.closeConnection();

		
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

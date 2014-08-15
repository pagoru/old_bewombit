package es.bewom.bewomBit.events;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import es.bewom.bewomBit.BewomBit;

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
		
		
//	    final String DB_NAME = "jdbc:mysql://188.165.234.183:3306/zpermissions";
//	    final String USER = "root";
//	    final String PASS = "aef771bc0d3f5118ba5dcc1893bd0bd4";
//	    Connection conn;
//	    Statement s;
		
		// ---> MySQL <--- //
		
//		Class.forName("com.mysql.jdbc.Driver");
//		
//		Connection connection = DriverManager.getConnection("jdbc:mysql://bewom.es.mysql", "bewom_es", "n58PLKf7");
//
//		
//		try
//		{
//		    if(connection != null)
//		        connection.close();
//		} catch (SQLException e) {
//		    e.printStackTrace();
//		}
	    
		
//		"SELECT * FROM `memberships` WHERE member = '" + player.getName() + "';"
		
//		player.sendMessage(rs.toString());
		
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

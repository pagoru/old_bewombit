package es.bewom.bewomBit.events;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public static void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException, ClassNotFoundException, ParseException {
		
		Player craftPlayer = eventConnect.getPlayer();
		String playerUUID = craftPlayer.getUniqueId().toString();
		String playerName = craftPlayer.getName();
		String playerIP = craftPlayer.getAddress().getAddress().toString().substring(1);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date firstDate = new Date();
		
		PermissionAttachment attachment = craftPlayer.addAttachment(BewomBit.main);
		
		
		MySQL connection = new MySQL(BewomBit.main, "localhost", "3306", "bewomBit", "root", "");
		connection.openConnection();
		Statement statement = connection.getConnection().createStatement();
		ResultSet query = statement.executeQuery("SELECT * FROM `permissions` WHERE `playerName` = '" + craftPlayer.getName() + "';");
		
		
		
		if(query.next()){
			
			String groupExp = query.getString("group_expiration");
			
			craftPlayer.sendMessage(groupExp + " <> " + firstDate);
			
			if(groupExp != null) {
				
				Date group_expiration = dateFormat.parse(groupExp);
				
				craftPlayer.sendMessage(group_expiration + " <||> " + firstDate);
				
				if(group_expiration.compareTo(firstDate) < 0){
					
					statement.executeUpdate("UPDATE `permissions` SET `group`= 0, `group_expiration`= null WHERE `playerName` = '" + playerName + "'");
					
				} else {
					
					int group = query.getInt("group"); 
					
					if(group == 0){
						
						attachment.setPermission("bewom.default", true);
						
					} else if(group == 1){
						
						attachment.setPermission("bewom.admin", true);
						
					} else if(group == 2){
						
						attachment.setPermission("bewom.mod", true);
						
					} else if(group == 3){
						
						attachment.setPermission("bewom.vip", true);
						
					} 
				}
				
			} else {
				
				int group = query.getInt("group");
				
				if(group == 0){
					
					attachment.setPermission("bewom.default", true);
					
				} else if(group == 1){
					
					attachment.setPermission("bewom.admin", true);
					
				} else if(group == 2){
					
					attachment.setPermission("bewom.mod", true);
					
				} else if(group == 3){
					
					attachment.setPermission("bewom.vip", true);
					
				} 
			}
			
		} else {
				
			statement.executeUpdate("INSERT INTO permissions (`UUID`, `playerName`, `group`, `first_login`, `last_login`, `IP`) VALUES ('" + playerUUID + "', '" + playerName + "', '" + "0" + "', '" + dateFormat.format(firstDate).toString() + "', '" + dateFormat.format(firstDate).toString() + "', '" + playerIP + "');");
			
		}
		
		statement.executeUpdate("UPDATE `permissions` SET `UUID`= '" + playerUUID + "', `last_login`= '" + dateFormat.format(firstDate).toString() + "', `IP`= '" + playerIP + "' WHERE `playerName` = '" + playerName + "'");
		
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

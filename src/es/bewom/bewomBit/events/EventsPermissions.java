package es.bewom.bewomBit.events;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
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
			String lastLogin = query.getString("last_login");
			
			Date lastLoginParse = dateFormat.parse(lastLogin);
			
			long diff = firstDate.getTime() - lastLoginParse.getTime();
			long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
			long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60; 
			long hours = TimeUnit.MILLISECONDS.toHours(diff) % 24; 
			long days = TimeUnit.MILLISECONDS.toDays(diff); 
			
			//última hora de connexion//
			
			String segundos = "";
			String minutos = "";
			String horas = "";
			String dias = "";
			
			if(seconds != 1){
				segundos = seconds + " segundos";	
			} else  {
				segundos = seconds + " segundo";	
			}
			
			if(minutes != 1){
				minutos = minutes + " minutos";	
			} else {
				minutos = minutes + " minuto" ;	
			}
			
			if(hours != 1){
				horas = hours + " horas";	
			} else {
				horas = hours + " hora" ;	
			}
			
			if(days != 1){
				dias = days + " dias";	
			} else {
				dias = days + " dia" ;	
			}
			
			if(days == 0 && hours == 0 && minutes == 0){
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Tu última conexión fue hace " + segundos + "." );	
				
			} else if(days == 0 && hours == 0){
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Tu última conexión fue hace " + minutos + " y " + segundos + "." );	
				
			} else if(days == 0){
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Tu última conexión fue hace " + horas + ", " + minutos + " y " + segundos + "." );
				
			} else {
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Tu última conexión fue hace " + dias + ", " + horas + ", " + minutos + " y " + segundos + "." );
				
			}
			
			//grupos y permisos//
			
			if(groupExp != null) {
				
				Date group_expiration = dateFormat.parse(groupExp);	
				
				if(group_expiration.compareTo(firstDate) < 0){
					
					statement.executeUpdate("UPDATE `permissions` SET `group`= 0, `group_expiration`= null WHERE `playerName` = '" + playerName + "'");
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Se te ha terminado el VIP, puedes volver a donar en http://bewom.es/vip ! :)" );
					
				} else {
					
					int group = query.getInt("group");
					
					
					//permisos
					
					if(group == 0){
						
						attachment.setPermission("bewom.default", true);
						
					} else if(group == 1){
						
						attachment.setPermission("bewom.admin", true);
						
					} else if(group == 2){
						
						attachment.setPermission("bewom.mod", true);
						
					} else if(group == 3){
						
						attachment.setPermission("bewom.vip", true);
						
						//horas restantes VIP
						
						Date groupExpParse = dateFormat.parse(groupExp);
						
						long diff1 = groupExpParse.getTime() - firstDate.getTime();
						long seconds1 = TimeUnit.MILLISECONDS.toSeconds(diff1) % 60;
						long minutes1 = TimeUnit.MILLISECONDS.toMinutes(diff1) % 60; 
						long hours1 = TimeUnit.MILLISECONDS.toHours(diff1) % 24; 
						long days1 = TimeUnit.MILLISECONDS.toDays(diff1); 
						
						String segundos1 = "";
						String minutos1 = "";
						String horas1 = "";
						String dias1 = "";
						
						boolean sec1 = false;
						boolean min1 = false;
						boolean hor1 = false;
						boolean day1 = false;
						
						if(seconds1 != 1){
							segundos1 = seconds1 + " segundos";	
						} else  {
							segundos1 = seconds1 + " segundo";	
							sec1 = true;
						}
						
						if(minutes1 != 1){
							minutos1 = minutes1 + " minutos";	
						} else {
							minutos1 = minutes1 + " minuto" ;
							min1 = true;
						}
						
						if(hours1 != 1){
							horas1 = hours1 + " horas";	
						} else {
							horas1 = hours1 + " hora" ;	
							hor1 = true;
						}
						
						if(days1 != 1){
							dias1 = days1 + " dias";	
						} else {
							dias1 = days1 + " dia" ;
							day1 = true;
						}
						
						if(days1 == 0 && hours1 == 0 && minutes1 == 0){
							
							if(sec1 == true){
								
								craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Te queda " + segundos1 + " como usuario VIP." );	
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Te quedan " + segundos1 + " como usuario VIP." );
								
							}
														
						} else if(days1 == 0 && hours1 == 0){
							
							if(min1 == true){
								
								craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Te queda " + minutos1 + " y " + segundos1 + " como usuario VIP." );	
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Te quedan " + minutos1 + " y " + segundos1 + " como usuario VIP." );	
								
							}
														
						} else if(days1 == 0){
							
							if(hor1 == true){
								
								craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Te queda " + horas1 + ", " + minutos1 + " y " + segundos1 + " como usuario VIP." );
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Te quedan " + horas1 + ", " + minutos1 + " y " + segundos1 + " como usuario VIP." );
								
							}
							
						} else {
							
							if(day1 == true){
								
								craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Te queda " + dias1 + ", " + horas1 + ", " + minutos1 + " y " + segundos1 + " como usuario VIP." );
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Te quedan " + dias1 + ", " + horas1 + ", " + minutos1 + " y " + segundos1 + " como usuario VIP." );
								
							}
							
						}
	
						
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

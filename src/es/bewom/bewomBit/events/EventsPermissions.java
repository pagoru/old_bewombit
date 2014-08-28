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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class EventsPermissions {
	
	static Logger log = Logger.getLogger("Minecraft");
	
	private static int task1;
	private static int task2;
	
	private static final EventsPermissions instance = new EventsPermissions();
	
	public static final EventsPermissions getPlugin() {		
		return instance;
	}

	@EventHandler
	public static void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException, ClassNotFoundException, ParseException {
		
		final Player craftPlayer = eventConnect.getPlayer();
		String playerUUID = craftPlayer.getUniqueId().toString();
		String playerName = craftPlayer.getName();
		String playerIP = craftPlayer.getAddress().getAddress().toString().substring(1);
		int serverPort = Bukkit.getServer().getPort();
		
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date firstDate = new Date();
		
		final PermissionAttachment attachment = craftPlayer.addAttachment(BewomBit.main);
		
		
		MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
		connection.openConnection();
		Statement statement = connection.getConnection().createStatement();
		ResultSet query = statement.executeQuery("SELECT * FROM `permissions` WHERE `playerName` = '" + craftPlayer.getName() + "';");
		
		if(query.next()){
			
			String groupExp = query.getString("group_expiration");
			String lastLogin = query.getString("last_login");
			String id = query.getString("id");
			
			Date lastLoginParse = dateFormat.parse(lastLogin);
			
			long diff = firstDate.getTime() - lastLoginParse.getTime();
			long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
			long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60; 
			long hours = TimeUnit.MILLISECONDS.toHours(diff) % 24; 
			long days = TimeUnit.MILLISECONDS.toDays(diff); 
			
			//última hora de connexion//
			
			craftPlayer.sendMessage(ChatColor.BOLD + "Bienvenido de nuevo usuario #" + id + " " + playerName + "! " + ChatColor.DARK_RED  + "❤");
			
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
						
						//Scheduler
												
						task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(BewomBit.main, new Runnable() {
				            
							@SuppressWarnings("deprecation")
							@Override
				            public void run() {
								try {
									
									log.info("Refrescando permisos de " + craftPlayer.getName());
									
									MySQL connection1 = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
									
									connection1.openConnection();
									
									Statement statement1 = connection1.getConnection().createStatement();
									ResultSet query1 = statement1.executeQuery("SELECT * FROM `permissions` WHERE `playerName` = '" + craftPlayer.getName() + "';");
									
									if(query1.next()){
										
										String groupExp1 = query1.getString("group_expiration");
										Date firstDate1 = new Date();
										Date group_expiration1;
										
										
										if(groupExp1 != null) {
											
											group_expiration1 = dateFormat.parse(groupExp1);
											
											if(group_expiration1.compareTo(firstDate1) < 0){
												
												statement1.executeUpdate("UPDATE `permissions` SET `group`= 0, `group_expiration`= null WHERE `playerName` = '" + craftPlayer.getName() + "'");
												craftPlayer.sendMessage(ChatColor.DARK_AQUA + "¡Se te ha terminado el VIP! :(");
												craftPlayer.sendMessage(ChatColor.DARK_AQUA + "¡Pero puedes volver a donar! :)");
												craftPlayer.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "http://bewom.es/vip");
												
												attachment.unsetPermission("bewom.vip");
												attachment.setPermission("bewom.default", true);
												
												// elimina scoreboard del jugador
												
												ScoreboardManager manager = Bukkit.getScoreboardManager();
												EventsPerfiles.board = manager.getMainScoreboard();
												
												Team teamUser = EventsPerfiles.board.getTeam(craftPlayer.getName());
												teamUser.unregister();
												
												Bukkit.getServer().getScheduler().cancelTask(task1);
												
											}
										}
									}
									
									statement1.close();
									connection1.closeConnection();
									
									if(Bukkit.getServer().getPlayer(craftPlayer.getName()) == null){
										
										Bukkit.getServer().getScheduler().cancelTask(task1);
										
									}
									
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								} catch (SQLException e) {
									e.printStackTrace();
								} catch (ParseException e) {
									e.printStackTrace();
								}	
							}
							
						}, 0, 6000);
	
						
					} 
				}
				
			} else {
				
				int group = query.getInt("group");
				
				if(group == 0){
					
					attachment.setPermission("bewom.default", true);
					
					//Scheduler
					
					task2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(BewomBit.main, new Runnable() {
			            
						@SuppressWarnings("deprecation")
						@Override
			            public void run() {
							try {
								
								log.info("Refrescando permisos de " + craftPlayer.getName());
								
								MySQL connection2 = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
								
								connection2.openConnection();
								
								Statement statement2 = connection2.getConnection().createStatement();
								ResultSet query2 = statement2.executeQuery("SELECT * FROM `permissions` WHERE `playerName` = '" + craftPlayer.getName() + "';");
								
								if(query2.next()){
									
									String group2 = query2.getString("group");									
									
									if(group2.equals("3")) {
										
										craftPlayer.sendMessage(ChatColor.DARK_AQUA + "¡Ya eres VIP! :)");
										craftPlayer.sendMessage(ChatColor.DARK_AQUA + "¡Disfruta de tus nuevas ventajas! :D");
										
										attachment.setPermission("bewom.vip", true);
										
										// crea scoreboard del jugador
										
										ScoreboardManager manager = Bukkit.getScoreboardManager();
										EventsPerfiles.board = manager.getMainScoreboard();
										
										Team teamUser = EventsPerfiles.board.getTeam(craftPlayer.getName());
										
										teamUser = EventsPerfiles.board.registerNewTeam(craftPlayer.getName());
										teamUser.setPrefix(ChatColor.DARK_AQUA + "" );
										teamUser.setDisplayName(craftPlayer.getName());
										teamUser.addPlayer(craftPlayer);
										
										Bukkit.getServer().getScheduler().cancelTask(task2);

									}
								}
								
								statement2.close();
								connection2.closeConnection();
								
								if(Bukkit.getServer().getPlayer(craftPlayer.getName()) == null){
									
									Bukkit.getServer().getScheduler().cancelTask(task2);
									
								}
								
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							} 
						}
						
					}, 0, 6000);
					
				} else if(group == 1){
					
					attachment.setPermission("bewom.admin", true);
					
				} else if(group == 2){
					
					attachment.setPermission("bewom.mod", true);
					
				} else if(group == 3){
					
					attachment.setPermission("bewom.vip", true);
					
				} 
			}
			
		} else {
				
			craftPlayer.sendMessage(ChatColor.BOLD + "Bienvenido " + playerName + "! ¿Eres nuevo, no? " + ChatColor.DARK_RED  + "❤");
			
			statement.executeUpdate("INSERT INTO permissions (`UUID`, `playerName`, `group`, `first_login`, `last_login`, `IP`,`first_server`,`last_server`) "
					+ "VALUES ('" + playerUUID + "', '" + playerName + "', '" + "0" + "', '" + dateFormat.format(firstDate).toString() + "',"
					+ " '" + dateFormat.format(firstDate).toString() + "', '" + playerIP + "', '" + serverPort + "', '" + serverPort + "');");
			
		}
		
		statement.executeUpdate("UPDATE `permissions` SET `UUID`= '" + playerUUID + "', `last_login`= '" + dateFormat.format(firstDate).toString() + "',"
				+ " `IP`= '" + playerIP + "',`last_server`='" + serverPort + "' WHERE `playerName` = '" + playerName + "'");
		
		statement.close();
		connection.closeConnection();
		
		
	}	
	
}

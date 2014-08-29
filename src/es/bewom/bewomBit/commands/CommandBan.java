package es.bewom.bewomBit.commands;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.DefaultMessages;
import es.bewom.bewomBit.utility.UUIDFetcher;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class CommandBan {
	
	public static boolean commandban (CommandSender sender, Command cmd, String commandLabel, String [] args) throws Exception{

		if (commandLabel.equalsIgnoreCase("ban")){
			
			final Player craftPlayer = ((OfflinePlayer) sender).getPlayer();
			String playerUUID = craftPlayer.getUniqueId().toString();
			String playerName = craftPlayer.getName();
			String playerIP = craftPlayer.getAddress().getAddress().toString().substring(1);
			
			int X = craftPlayer.getLocation().getBlockX();
			int Y = craftPlayer.getLocation().getBlockY(); 
			int Z = craftPlayer.getLocation().getBlockZ();
			
			String world = craftPlayer.getWorld().getName();
			
			int server = Bukkit.getPort();
			
			final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date firstDate = new Date();
			
			if (args.length != 1 && args.length != 0){
			
				if(args[1].contains("permanente")){
					
					if (args.length == 2){
						
						String bannedPlayer = args[0];
						UUID bannedPlayerUUID = UUIDFetcher.getUUIDOf(bannedPlayer);
						
						craftPlayer.sendMessage(bannedPlayerUUID.toString());
						
						bannedPlayer = Bukkit.getServer().getOfflinePlayer(bannedPlayerUUID).getName();
						
						String bannedPlayerIP = null;
						
						if(Bukkit.getServer().getPlayer(bannedPlayerUUID) != null){
							
							bannedPlayerIP = Bukkit.getServer().getPlayer(bannedPlayerUUID).getAddress().getAddress().toString().substring(1);
							
						}
						
						//consulta bans
						
						MySQL connectionC = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
						connectionC.openConnection();
						Statement statementC = connectionC.getConnection().createStatement();
						ResultSet queryC = statementC.executeQuery("SELECT * FROM `ban_list` WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
						
						if(queryC.next()){
							
							String motivoC = queryC.getString("motivo");
							String ban_tiempoC = queryC.getString("ban_tiempo");
							String baneador_playerName = queryC.getString("baneador_playerName");
							
							if(ban_tiempoC == null){
								
								craftPlayer.sendMessage(ChatColor.GRAY + bannedPlayer + " ya estaba baneado por " + motivoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY + baneador_playerName + " lo baneo de forma permanente.");
								craftPlayer.sendMessage(ChatColor.GRAY +"El baneo ha sido reemplazado por el tuyo.");
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.GRAY +bannedPlayer + " ya estaba baneado por " + motivoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY +baneador_playerName + " lo baneo de forma temporal hasta " + ban_tiempoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY +"El baneo ha sido reemplazado por el tuyo.");
								
							}
							
							statementC.executeUpdate("UPDATE `ban_list` SET `activo` = false WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
							
						}
						
						statementC.close();
						connectionC.closeConnection();
						
						// ban actual
						
						MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
						connection.openConnection();
						
						Statement statement = connection.getConnection().createStatement();
							
						statement.executeUpdate("INSERT INTO ban_list "
								+ "(`activo`, `tiempo`, `banned_UUID`, `banned_playerName`, `banned_IP`, `motivo`, `baneador_UUID`, " // `ban_tiempo`, 
								+ " `baneador_playerName`, `baneador_IP`, `X`, `Y`, `Z`, `world`, `server`) VALUES "
								+ "(true, '" + dateFormat.format(firstDate) + "', '" + bannedPlayerUUID + "', '" + bannedPlayer + "', '" + bannedPlayerIP + "', "
								+ "'incumplir las normas', '" + playerUUID + "', '" + playerName + "', '" + playerIP + "', '" + X + "', '" + Y + "', '" + Z + "', "
								+ "'" + world + "', '" + server + "' );");
	
						statement.close();
						connection.closeConnection();
						
						Bukkit.getServer().broadcastMessage(DefaultMessages.kickBan + "El jugador " + bannedPlayer + " ha sido baneado por incumplir las normas.");
						
						Player bannedCraftPlayer = Bukkit.getServer().getPlayer(bannedPlayerUUID);
						if(bannedCraftPlayer != null){
							bannedCraftPlayer.kickPlayer(DefaultMessages.kickBanPlayer + "Has sido baneado de forma permanente por incumplir las normas.");
						}
						
					} else if (args.length >= 3){
					
						String bannedPlayer = args[0];
						UUID bannedPlayerUUID = UUIDFetcher.getUUIDOf(bannedPlayer);
						bannedPlayer = Bukkit.getServer().getOfflinePlayer(bannedPlayerUUID).getName();
						
						String bannedPlayerIP = null;
						
						if(Bukkit.getServer().getPlayer(bannedPlayerUUID) != null){
							
							bannedPlayerIP = Bukkit.getServer().getPlayer(bannedPlayerUUID).getAddress().getAddress().toString().substring(1);
							
						}
						
						String motivo = "";
						for (int i = 2; i < args.length; i++) {
							if(args.length-1 > i){
								motivo += args[i] + " ";
							} else {
								motivo += args[i] + "";
							}
						}
						
						//consulta bans
						
						MySQL connectionC = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
						connectionC.openConnection();
						Statement statementC = connectionC.getConnection().createStatement();
						ResultSet queryC = statementC.executeQuery("SELECT * FROM `ban_list` WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
						
						if(queryC.next()){
							
							String motivoC = queryC.getString("motivo");
							String ban_tiempoC = queryC.getString("ban_tiempo");
							String baneador_playerName = queryC.getString("baneador_playerName");
							
							if(ban_tiempoC == null){
								
								craftPlayer.sendMessage(ChatColor.GRAY + bannedPlayer + " ya estaba baneado por " + motivoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY + baneador_playerName + " lo baneo de forma permanente.");
								craftPlayer.sendMessage(ChatColor.GRAY +"El baneo ha sido reemplazado por el tuyo.");
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.GRAY +bannedPlayer + " ya estaba baneado por " + motivoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY +baneador_playerName + " lo baneo de forma temporal hasta " + ban_tiempoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY +"El baneo ha sido reemplazado por el tuyo.");
								
							}
							
							statementC.executeUpdate("UPDATE `ban_list` SET `activo` = false WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
							
						}
						
						statementC.close();
						connectionC.closeConnection();
						
						// ban actual
						
						MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
						connection.openConnection();
						
						Statement statement = connection.getConnection().createStatement();
							
						statement.executeUpdate("INSERT INTO ban_list "
								+ "(`activo`, `tiempo`, `banned_UUID`, `banned_playerName`, `banned_IP`, `motivo`, `baneador_UUID`, " // `ban_tiempo`, 
								+ " `baneador_playerName`, `baneador_IP`, `X`, `Y`, `Z`, `world`, `server`) VALUES "
								+ "(true, '" + dateFormat.format(firstDate) + "', '" + bannedPlayerUUID + "', '" + bannedPlayer + "', '" + bannedPlayerIP + "', "
								+ "'" + motivo + "', '" + playerUUID + "', '" + playerName + "', '" + playerIP + "', '" + X + "', '" + Y + "', '" + Z + "', "
								+ "'" + world + "', '" + server + "' );");
	
						statement.close();
						connection.closeConnection();
						
						Bukkit.getServer().broadcastMessage(DefaultMessages.kickBan + "El jugador " + bannedPlayer + " ha sido baneado por " + motivo + ".");
						
						Player bannedCraftPlayer = Bukkit.getServer().getPlayer(bannedPlayerUUID);
						if(bannedCraftPlayer != null){
							
							bannedCraftPlayer.kickPlayer(DefaultMessages.kickBanPlayer + "Has sido baneado por " + motivo + " de forma permanente.");
							
						}
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED  + "Usa el comando bien /ban [nick] [permanente/temporal} [motivo/numero} {tiempo} {motivo}.");
						
					}
					
				} else if(args[1].contains("temporal")){
					
					if (args.length == 4){
					
						long args2 = Long.parseLong(args[2]);
						long segundosArgs2 = 0;
						
						String bannedPlayer = args[0];
						UUID bannedPlayerUUID = UUIDFetcher.getUUIDOf(bannedPlayer);
						bannedPlayer = Bukkit.getServer().getOfflinePlayer(bannedPlayerUUID).getName();
						
						String bannedPlayerIP = null;
						
						long firsDateTime = firstDate.getTime();
						
						
						if(Bukkit.getServer().getPlayer(bannedPlayerUUID) != null){
							
							bannedPlayerIP = Bukkit.getServer().getPlayer(bannedPlayerUUID).getAddress().getAddress().toString().substring(1);
							
						}		
						
						if(args[3].contains("s")){
							
							segundosArgs2 = args2*1000;
							
						} else if(args[3].contains("m")){
							
							segundosArgs2 = args2*60*1000;
							
						} else if(args[3].contains("h")){
							
							segundosArgs2 = args2*60*60*1000;
							
						} else if(args[3].contains("d")){
							
							segundosArgs2 = args2*24*60*60*1000;
							
						}
						
						//consulta bans
						
						MySQL connectionC = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
						connectionC.openConnection();
						Statement statementC = connectionC.getConnection().createStatement();
						ResultSet queryC = statementC.executeQuery("SELECT * FROM `ban_list` WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
						
						if(queryC.next()){
							
							String motivoC = queryC.getString("motivo");
							String ban_tiempoC = queryC.getString("ban_tiempo");
							String baneador_playerName = queryC.getString("baneador_playerName");
							
							if(ban_tiempoC == null){
								
								craftPlayer.sendMessage(ChatColor.GRAY + bannedPlayer + " ya estaba baneado por " + motivoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY + baneador_playerName + " lo baneo de forma permanente.");
								craftPlayer.sendMessage(ChatColor.GRAY +"El baneo ha sido reemplazado por el tuyo.");
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.GRAY +bannedPlayer + " ya estaba baneado por " + motivoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY +baneador_playerName + " lo baneo de forma temporal hasta " + ban_tiempoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY +"El baneo ha sido reemplazado por el tuyo.");
								
							}
							
							statementC.executeUpdate("UPDATE `ban_list` SET `activo` = false WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
							
						}
						
						statementC.close();
						connectionC.closeConnection();
						
						// ban actual
						
						long dateTimeSec = firsDateTime + segundosArgs2;					
						Date dateTime = new Date(dateTimeSec);
						
						MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
						connection.openConnection();
						
						Statement statement = connection.getConnection().createStatement();
							
						statement.executeUpdate("INSERT INTO ban_list "
								+ "(`activo`, `tiempo`, `banned_UUID`, `banned_playerName`, `banned_IP`, `motivo`, `baneador_UUID`, " // `ban_tiempo`, 
								+ " `baneador_playerName`, `baneador_IP`, `X`, `Y`, `Z`, `world`, `server`, `ban_tiempo`) VALUES "
								+ "(true, '" + dateFormat.format(firstDate) + "', '" + bannedPlayerUUID + "', '" + bannedPlayer + "', '" + bannedPlayerIP + "', "
								+ "'incumplir las normas', '" + playerUUID + "', '" + playerName + "', '" + playerIP + "', '" + X + "', '" + Y + "', '" + Z + "', "
								+ "'" + world + "', '" + server + "', '" + dateFormat.format(dateTime) + "' );");
	
						statement.close();
						connection.closeConnection();
						
						Bukkit.getServer().broadcastMessage(DefaultMessages.kickBan + "El jugador " + bannedPlayer + " ha sido baneado por incumplir las normas.");
						
						Player bannedCraftPlayer = Bukkit.getServer().getPlayer(bannedPlayerUUID);
						if(bannedCraftPlayer != null){
							bannedCraftPlayer.kickPlayer(DefaultMessages.kickBanPlayer + "Has sido baneado de forma temporal por incumplir las normas.");
						}
						
					} else if (args.length >= 5){
										
						String motivo = "";
						for (int i = 4; i < args.length; i++) {
							if(args.length-1 > i){
								motivo += args[i] + " ";
							} else {
								motivo += args[i] + "";
							}
						}
						
						
						long args2 = Long.parseLong(args[2]);
						long segundosArgs2 = 0;
						
						String bannedPlayer = args[0];
						UUID bannedPlayerUUID = UUIDFetcher.getUUIDOf(bannedPlayer);
						bannedPlayer = Bukkit.getServer().getOfflinePlayer(bannedPlayerUUID).getName();
						
						String bannedPlayerIP = null;
						
						long firsDateTime = firstDate.getTime();
						
						
						if(Bukkit.getServer().getPlayer(bannedPlayerUUID) != null){
							
							bannedPlayerIP = Bukkit.getServer().getPlayer(bannedPlayerUUID).getAddress().getAddress().toString().substring(1);
							
						}		
						
						if(args[3].contains("s")){
							
							segundosArgs2 = args2*1000;
							
						} else if(args[3].contains("m")){
							
							segundosArgs2 = args2*60*1000;
							
						} else if(args[3].contains("h")){
							
							segundosArgs2 = args2*60*60*1000;
							
						} else if(args[3].contains("d")){
							
							segundosArgs2 = args2*24*60*60*1000;
							
						}
						
						//consulta bans
						
						MySQL connectionC = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
						connectionC.openConnection();
						Statement statementC = connectionC.getConnection().createStatement();
						ResultSet queryC = statementC.executeQuery("SELECT * FROM `ban_list` WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
						
						if(queryC.next()){
							
							String motivoC = queryC.getString("motivo");
							String ban_tiempoC = queryC.getString("ban_tiempo");
							String baneador_playerName = queryC.getString("baneador_playerName");
							
							if(ban_tiempoC == null){
								
								craftPlayer.sendMessage(ChatColor.GRAY + bannedPlayer + " ya estaba baneado por " + motivoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY + baneador_playerName + " lo baneo de forma permanente.");
								craftPlayer.sendMessage(ChatColor.GRAY +"El baneo ha sido reemplazado por el tuyo.");
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.GRAY +bannedPlayer + " ya estaba baneado por " + motivoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY +baneador_playerName + " lo baneo de forma temporal hasta " + ban_tiempoC + ".");
								craftPlayer.sendMessage(ChatColor.GRAY +"El baneo ha sido reemplazado por el tuyo.");
								
							}
							
							statementC.executeUpdate("UPDATE `ban_list` SET `activo` = false WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
							craftPlayer.kickPlayer(DefaultMessages.kickBanPlayer + "Has sido baneado de forma temporal.");
						}
						
						statementC.close();
						connectionC.closeConnection();
						
						// ban actual
						
						long dateTimeSec = firsDateTime + segundosArgs2;					
						Date dateTime = new Date(dateTimeSec);
						
						MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
						connection.openConnection();
						
						Statement statement = connection.getConnection().createStatement();
							
						statement.executeUpdate("INSERT INTO ban_list "
								+ "(`activo`, `tiempo`, `banned_UUID`, `banned_playerName`, `banned_IP`, `motivo`, `baneador_UUID`, " // `ban_tiempo`, 
								+ " `baneador_playerName`, `baneador_IP`, `X`, `Y`, `Z`, `world`, `server`, `ban_tiempo`) VALUES "
								+ "(true, '" + dateFormat.format(firstDate) + "', '" + bannedPlayerUUID + "', '" + bannedPlayer + "', '" + bannedPlayerIP + "', "
								+ "'" + motivo + "', '" + playerUUID + "', '" + playerName + "', '" + playerIP + "', '" + X + "', '" + Y + "', '" + Z + "', "
								+ "'" + world + "', '" + server + "', '" + dateFormat.format(dateTime) + "' );");
	
						statement.close();
						connection.closeConnection();
						
						Bukkit.getServer().broadcastMessage(DefaultMessages.kickBan + "El jugador " + bannedPlayer + " ha sido baneado por " + motivo + ".");
						
						Player bannedCraftPlayer = Bukkit.getServer().getPlayer(bannedPlayerUUID);
						if(bannedCraftPlayer != null){
							bannedCraftPlayer.kickPlayer(DefaultMessages.kickBanPlayer + "Has sido baneado por " + motivo + " de forma temporal.");
						}
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED  + "Usa el comando bien /ban [nick] [permanente/temporal} [motivo/numero} {tiempo} {motivo}.");
						
					}
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.RED  + "Usa el comando bien /ban [nick] [permanente/temporal} [motivo/numero} {tiempo} {motivo}.");
					
				}
				
			} else {
				
				craftPlayer.sendMessage(ChatColor.RED  + "Usa el comando bien /ban [nick] [permanente/temporal} [motivo/numero} {tiempo} {motivo}.");
				
			}
			
			return true;
			
		}
		return false;
	}
}

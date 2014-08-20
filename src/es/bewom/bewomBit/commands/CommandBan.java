package es.bewom.bewomBit.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class CommandBan {
	
	@SuppressWarnings({ "deprecation" })
	public static boolean commandban (CommandSender sender, Command cmd, String commandLabel, String [] args) throws FileNotFoundException, IOException, InvalidConfigurationException, SQLException, ClassNotFoundException{

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
			
			if(args[1].contains("permanente")){
				
				if (args.length == 2){
					
					String bannedPlayer = args[0];
					UUID bannedPlayerUUID = Bukkit.getServer().getOfflinePlayer(bannedPlayer).getUniqueId();
					
					String bannedPlayerIP = null;
					
					if(Bukkit.getServer().getPlayer(bannedPlayer) != null){
						
						bannedPlayerIP = Bukkit.getServer().getPlayer(bannedPlayer).getAddress().getAddress().toString().substring(1);
						
					}
					
					
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
					
				} else if (args.length >= 3){
				
					String bannedPlayer = args[0];
					UUID bannedPlayerUUID = Bukkit.getServer().getOfflinePlayer(bannedPlayer).getUniqueId();
					
					String bannedPlayerIP = null;
					
					if(Bukkit.getServer().getPlayer(bannedPlayer) != null){
						
						bannedPlayerIP = Bukkit.getServer().getPlayer(bannedPlayer).getAddress().getAddress().toString().substring(1);
						
					}
					
					String motivo = "";
					for (int i = 2; i < args.length; i++) {
						motivo += args[i] + " ";
					}
					
					
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
					
				} 
				
			} else if(args[1].contains("temporal")){
				
				if (args.length == 4){
				
					long args2 = Long.parseLong(args[2]);
					long segundosArgs2 = 0;
					
					String bannedPlayer = args[0];
					UUID bannedPlayerUUID = Bukkit.getServer().getOfflinePlayer(bannedPlayer).getUniqueId();
					
					String bannedPlayerIP = null;
					
					long firsDateTime = firstDate.getTime();
					
					
					if(Bukkit.getServer().getPlayer(bannedPlayer) != null){
						
						bannedPlayerIP = Bukkit.getServer().getPlayer(bannedPlayer).getAddress().getAddress().toString().substring(1);
						
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
					
				} else if (args.length >= 5){
									
					String motivo = "";
					for (int i = 4; i < args.length; i++) {
						motivo += args[i] + " ";
					}
					
					
					long args2 = Long.parseLong(args[2]);
					long segundosArgs2 = 0;
					
					String bannedPlayer = args[0];
					UUID bannedPlayerUUID = Bukkit.getServer().getOfflinePlayer(bannedPlayer).getUniqueId();
					
					String bannedPlayerIP = null;
					
					long firsDateTime = firstDate.getTime();
					
					
					if(Bukkit.getServer().getPlayer(bannedPlayer) != null){
						
						bannedPlayerIP = Bukkit.getServer().getPlayer(bannedPlayer).getAddress().getAddress().toString().substring(1);
						
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
					
				} 
				
			}
		
			return true;
			
		}
		return false;
	}
}

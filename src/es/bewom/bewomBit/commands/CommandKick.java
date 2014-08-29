package es.bewom.bewomBit.commands;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.commands.utility.CommandUtilities;
import es.bewom.bewomBit.utility.DefaultMessages;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class CommandKick {

	@SuppressWarnings("deprecation")
	public static boolean commandkick(CommandSender sender, Command cmd, String label, String[] args) throws Exception{

		if (label.equalsIgnoreCase("kick")){

			Player craftPlayerArgs;
			
			final Player craftPlayer = ((OfflinePlayer) sender).getPlayer();
			String playerUUID = craftPlayer.getUniqueId().toString();
			String playerName = craftPlayer.getName();
			String playerIP = craftPlayer.getAddress().getAddress().toString().substring(1);
			
			int X = craftPlayer.getLocation().getBlockX();
			int Y = craftPlayer.getLocation().getBlockY(); 
			int Z = craftPlayer.getLocation().getBlockZ();
			
			String world = craftPlayer.getWorld().getName();
			
			String bannedPlayer = args[0];
			UUID bannedPlayerUUID = Bukkit.getServer().getOfflinePlayer(Bukkit.getServer().getOfflinePlayer(bannedPlayer).getUniqueId()).getUniqueId();
			bannedPlayer = Bukkit.getServer().getOfflinePlayer(bannedPlayerUUID).getName();
			
			String bannedPlayerIP = null;
			
			if(Bukkit.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(bannedPlayer).getUniqueId()) != null){
				
				bannedPlayerIP = Bukkit.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(bannedPlayer).getUniqueId()).getAddress().getAddress().toString().substring(1);
				
			}
			
			
			int server = Bukkit.getPort();
			
			final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date firstDate = new Date();

			if (args.length == 1) {

				if (CommandUtilities.comprobarJugador(sender, args [0])){

					craftPlayerArgs = Bukkit.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId());

					craftPlayerArgs.kickPlayer (DefaultMessages.kickBanPlayer + "Has sido kickeado.");
					Bukkit.getServer().broadcastMessage(DefaultMessages.kickBan + "El jugador " + craftPlayerArgs.getName() + " ha sido kickeado.");
					
					MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
					connection.openConnection();
					
					Statement statement = connection.getConnection().createStatement();
						
					statement.executeUpdate("INSERT INTO kick_list "
							+ "(`tiempo`, `kicked_UUID`, `kicked_playerName`, `kicked_IP`, `motivo`, `kicker_UUID`, `kicker_playerName`, " // `ban_tiempo`, 
							+ " `kicker_IP`, `X`, `Y`, `Z`, `world`, `server`) VALUES "
							+ "('" + dateFormat.format(firstDate) + "', '" + bannedPlayerUUID + "', '" + bannedPlayer + "', '" + bannedPlayerIP + "', "
							+ "'incumplir las normas', '" + playerUUID + "', '" + playerName + "', '" + playerIP + "', '" + X + "', '" + Y + "', '" + Z + "', "
							+ "'" + world + "', '" + server + "');");

					statement.close();
					connection.closeConnection();

				}
				else {	
					CommandUtilities.jugadorDesconectado(sender);
				}
			}

			else if (args.length > 1){

				if (CommandUtilities.comprobarJugador(sender, args [0])){

					craftPlayerArgs = Bukkit.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId()); 
					String texto = "";

					for (int i = 1; i < args.length; i++) {
						if(args.length-1 > i){
							texto += args[i] + " ";
						} else {
							texto += args[i] + "";
						}
					}
					craftPlayerArgs.kickPlayer(DefaultMessages.kickBanPlayer + "Has sido kickeado por " + texto + ".");
					Bukkit.getServer().broadcastMessage(DefaultMessages.kickBan + "El jugador " + craftPlayerArgs.getName() + " ha sido kickeado por " + texto + ".");
					
					MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
					connection.openConnection();
					
					Statement statement = connection.getConnection().createStatement();
						
					statement.executeUpdate("INSERT INTO kick_list "
							+ "(`tiempo`, `kicked_UUID`, `kicked_playerName`, `kicked_IP`, `motivo`, `kicker_UUID`, `kicker_playerName`, " // `ban_tiempo`, 
							+ " `kicker_IP`, `X`, `Y`, `Z`, `world`, `server`) VALUES "
							+ "('" + dateFormat.format(firstDate) + "', '" + bannedPlayerUUID + "', '" + bannedPlayer + "', '" + bannedPlayerIP + "', "
							+ "'" + texto + "', '" + playerUUID + "', '" + playerName + "', '" + playerIP + "', '" + X + "', '" + Y + "', '" + Z + "', "
							+ "'" + world + "', '" + server + "', '" + dateFormat.format(firstDate) + "' );");

					statement.close();
					connection.closeConnection();
				}
				else{
					CommandUtilities.jugadorDesconectado(sender);
				}
			}
			else {
				CommandUtilities.formaCorrecta(sender, "/kick <player> [motivo]");
			}
			return true;
		} 
		return false;
	}
}
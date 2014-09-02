package es.bewom.bewomBit.groups.see;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.MySQL.MySQL;
import es.bewom.bewomBit.utility.commands.CommandUtilities;

public class CommandSeen {

	@SuppressWarnings("deprecation")
	public static boolean commandseen(CommandSender sender, Command cmd, String label, String[] args) throws Exception{

		if (label.equalsIgnoreCase("seen")){

			if (args.length == 1) {

				Player craftPlayer = (Player) sender;
				
				if(Bukkit.getServer().getOfflinePlayer(args[0]) != null){
					
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(args[0]);
					
					MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
					connection.openConnection();
					Statement statement = connection.getConnection().createStatement();
					ResultSet query = statement.executeQuery("SELECT * FROM `permissions` WHERE `UUID` = '" + craftPlayerArgs.getUniqueId() + "';");
					
					if(query.next()){
						
						if(!Bukkit.getServer().getOnlinePlayers().contains(craftPlayerArgs)){
							
							Timestamp lastLogin = query.getTimestamp("last_login");
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
							SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm", new Locale("es", "ES"));
							
							craftPlayer.sendMessage(ChatColor.GRAY + "El jugador " + craftPlayerArgs.getName() + " esta desconectado desde el " + dateFormat.format(lastLogin) + " a las " + hourFormat.format(lastLogin) + ".");
							
						} else {
							
							craftPlayer.sendMessage(ChatColor.GREEN + "El jugador " + craftPlayerArgs.getName() + " esta en linea.");
							
						}						
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + "El jugador no ha entrado nunca en el servidor.");
						
					}
					
					connection.closeConnection();
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.RED + "El jugador no existe.");
					
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(args[0]);

					long craftPlayerArgsLast = (int) craftPlayerArgs.getLastPlayed();

					Date date = new Date(craftPlayerArgsLast); // *1000 is to convert seconds to milliseconds
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));

					craftPlayer.sendMessage(ChatColor.GRAY + "El jugador " + args[0] + " esta desconectado desde el " + dateFormat.format(date));
					
				}

			}
			else {
				CommandUtilities.formaCorrecta(sender, "/seen <player>");

			}
			return true;
		}
		return false;
	}
}
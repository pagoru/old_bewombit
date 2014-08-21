package es.bewom.bewomBit.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class CommandUnban {
	
	public static boolean commandunban (CommandSender sender, Command cmd, String commandLabel, String [] args) throws FileNotFoundException, IOException, InvalidConfigurationException, SQLException, ClassNotFoundException, ParseException{

		if (commandLabel.equalsIgnoreCase("unban")){
			
			final Player craftPlayer = ((OfflinePlayer) sender).getPlayer();
			
			String bannedPlayer = args[0];
			
			MySQL connectionC = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
			connectionC.openConnection();
			Statement statementC = connectionC.getConnection().createStatement();
			ResultSet queryC = statementC.executeQuery("SELECT * FROM `ban_list` WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
			
			if(queryC.next()){
				
				statementC.executeUpdate("UPDATE `ban_list` SET `activo` = false WHERE `banned_playerName` = '" + bannedPlayer + "' AND `activo` = true;");
				
				craftPlayer.sendMessage(ChatColor.RED + "Has desbaneado a " + bannedPlayer + ".");
				
			} else {
				
				craftPlayer.sendMessage(ChatColor.RED + "El usuario " + bannedPlayer + " no esta baneado.");
				
			}
			
			statementC.close();
			connectionC.closeConnection();
			
			return true;
			
		}
		return false;
	}
}

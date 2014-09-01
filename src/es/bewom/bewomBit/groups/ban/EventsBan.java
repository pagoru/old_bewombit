package es.bewom.bewomBit.groups.ban;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.DefaultMessages;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class EventsBan {
	
	@EventHandler
	public static void onPreJoin(AsyncPlayerPreLoginEvent eventPreConnect) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
		String prePlayerUUID = eventPreConnect.getUniqueId().toString();
//		String prePlayerIP = eventPreConnect.getAddress().getAddress().toString().substring(1);
		String prePlayerName = eventPreConnect.getName();
		
		MySQL connectionC = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
		connectionC.openConnection();
		Statement statementC = connectionC.getConnection().createStatement();
		ResultSet queryC = statementC.executeQuery("SELECT * FROM `ban_list` WHERE (`banned_UUID` = '" + prePlayerUUID + "' OR `banned_playerName` = '" + prePlayerName + "') AND `activo` = true;");
		
		if(queryC.next()){
			
			String motivoC = queryC.getString("motivo");
			String ban_tiempoC = queryC.getString("ban_tiempo");
			
			if(ban_tiempoC == null){
				
				if(!motivoC.equals(null)){
					eventPreConnect.disallow(Result.KICK_BANNED , DefaultMessages.kickBanPlayer + "Has sido baneado de forma permanente por " + motivoC + ". [Reclamaciones en bewom.es]");
				} else {
					eventPreConnect.disallow(Result.KICK_BANNED , DefaultMessages.kickBanPlayer + "Has sido baneado de forma permanente. [Reclamaciones en bewom.es]");
				}
				
			} else {
				
				if(!motivoC.equals(null)){
					eventPreConnect.disallow(Result.KICK_BANNED , DefaultMessages.kickBanPlayer + "Has sido baneado de forma temporal hasta " + ban_tiempoC + ". [Reclamaciones en bewom.es]");
				} else {
					eventPreConnect.disallow(Result.KICK_BANNED , DefaultMessages.kickBanPlayer + "Has sido baneado de forma temporal por " + motivoC + " hasta " + ban_tiempoC + ". [Reclamaciones en bewom.es]");
				}				
			}			
		}
		
		statementC.close();
		connectionC.closeConnection();
		
	}	
}
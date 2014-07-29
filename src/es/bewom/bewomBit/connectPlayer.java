package es.bewom.bewomBit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class connectPlayer implements Listener {
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws SQLException {
		
		event.setJoinMessage("Hallo ");
		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/zpermissions","root","gorupa_19Abril1994");
//		
//		java.sql.Statement s = conexion.createStatement();
//		
//		ResultSet rs = s.executeQuery("SELECT + FROM memberships ORDER BY expiration");
//		
//		while (rs.next()) {
//		}
//		conexion.close();
//		
//		Player playerName = event.getPlayer();
//		
//		String permAdmin = "bewom.admin";
//		String permMod = "bewom.mod";
//		String permVip = "bewom.vip";
//		
//		bewomBit.teamAdmin.removePlayer(playerName);
//		bewomBit.teamMod.removePlayer(playerName);
//		bewomBit.teamVip.removePlayer(playerName);
//		
//		if (playerName.hasPermission(permAdmin)) {
//			Bukkit.getLogger().info("[bewom.es] "+ playerName +" like a admin.");
//			bewomBit.teamAdmin.addPlayer(playerName);
//		} else if (playerName.hasPermission(permMod)) {
//			Bukkit.getLogger().info("[bewom.es] "+ playerName +" like a mod.");
//			bewomBit.teamMod.addPlayer(playerName);
//		} else if (playerName.hasPermission(permVip)) {
//			Bukkit.getLogger().info("[bewom.es] "+ playerName +" like a vip.");
//			bewomBit.teamVip.addPlayer(playerName);
//		}
	}

}

package es.bewom.bewomBit.events;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class EventsLogBlock {
	
	@SuppressWarnings("deprecation")
	public static void OnBreak(BlockBreakEvent eventBroke) throws Exception {
		
		MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
		connection.openConnection();
		Statement statement = connection.getConnection().createStatement();
		
		//info
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date firstDate = new Date();
		
		final Player craftPlayer = eventBroke.getPlayer();
		final String playerUUID = craftPlayer.getUniqueId().toString();
		String playerName = craftPlayer.getName();
		String hand = craftPlayer.getItemInHand().getType().name();
		int handNum = craftPlayer.getItemInHand().getAmount();
		
		String blockAnt = eventBroke.getBlock().getType().name();
		int dataAnt = eventBroke.getBlock().getData();
		
		int X = eventBroke.getBlock().getX();
		int Y = eventBroke.getBlock().getY();
		int Z = eventBroke.getBlock().getZ();
		String world = eventBroke.getBlock().getWorld().getName();
		int server = Bukkit.getServer().getPort();
			
		statement.executeUpdate("INSERT INTO log_block (`date`, `playerName`, `UUID`, `bloque_anterior`, `data_anterior`,"
				+ "`bloque_posterior`, `data_posterior`, `X`, `Y`, `Z`, `world`, `server`, `activo`, `hand`, `handNum`)"
				+ " VALUES ('" + dateFormat.format(firstDate) + "', '" + playerName + "', '" + playerUUID + "',"
				+ "'" + blockAnt + "', '" + dataAnt + "', '" + Material.AIR + "', '0', '" + X + "', '" + Y + "', '" + Z + "',"
				+ " '" + world + "', '" + server + "', true, '" + hand + "', '" + handNum + "');");
		
	}

}

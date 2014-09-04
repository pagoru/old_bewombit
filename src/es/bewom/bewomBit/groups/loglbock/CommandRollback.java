package es.bewom.bewomBit.groups.loglbock;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class CommandRollback {
	
	@SuppressWarnings("deprecation")
	public static boolean commandrollback(CommandSender sender, Command cmd, String label, String[] args) throws Exception{

		if (label.equalsIgnoreCase("rollback")){
			
			Player craftPlayer = (Player) sender;
			
			if(args.length == 1){
				
				MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
				connection.openConnection();
				Statement statement = connection.getConnection().createStatement();
				
				ResultSet query = statement.executeQuery("SELECT * FROM `log_block` WHERE `playerName` = '" + args[0] + "'");

				ArrayList<String> listBlockBA = new ArrayList<String>();
				ArrayList<String> listBlockDA = new ArrayList<String>();
				ArrayList<String> listBlockA_X = new ArrayList<String>();
				ArrayList<String> listBlockA_Y = new ArrayList<String>();
				ArrayList<String> listBlockA_Z = new ArrayList<String>();
				ArrayList<String> listBlockWorld = new ArrayList<String>();
							
				if(query != null){
					
					while (query.next()) {
					    listBlockBA.add(query.getString("bloque_anterior"));
					    listBlockDA.add(query.getString("data_anterior"));
					    listBlockA_X.add(query.getString("X"));
					    listBlockA_Y.add(query.getString("Y"));
					    listBlockA_Z.add(query.getString("Z"));
					    listBlockWorld.add(query.getString("world"));
					}
					
					int i = 0;
					
					for(String location : listBlockBA){
						
						int X = Integer.parseInt(listBlockA_X.get(i));
						int Y = Integer.parseInt(listBlockA_Y.get(i));
						int Z = Integer.parseInt(listBlockA_Z.get(i));
						
						byte b = Byte.valueOf(listBlockDA.get(i));
						
						Bukkit.getServer().getWorld(listBlockWorld.get(i)).getBlockAt(X, Y, Z).setType(Material.getMaterial(location));
						Bukkit.getServer().getWorld(listBlockWorld.get(i)).getBlockAt(X, Y, Z).setData(b);
						
						craftPlayer.sendMessage(X + " " + Y + " " + Y + " - " + b + " - " + " " + location + "  >> " + listBlockWorld.get(i));
						
						i = i + 1;
						
					}
					
				}	
				
				
				return true;
			}
		}
		return false;
	}
}

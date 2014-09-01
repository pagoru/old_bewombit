package es.bewom.bewomBit.events;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import es.bewom.bewomBit.BewomBit;
import es.bewom.bewomBit.utility.MySQL.MySQL;

public class EventsLogBlock {
	
	@SuppressWarnings({ "deprecation", "unused"})
	public static void OnBreak(BlockBreakEvent eventBroke) throws Exception {
		
		MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
		connection.openConnection();
		Statement statement = connection.getConnection().createStatement();
		
		//info
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final DateFormat dateFormatSimple = new SimpleDateFormat("dd|MM|yy HH:mm");
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
			
		if(hand == Material.BEDROCK.name()){
			
			eventBroke.setCancelled(true);
			
			ResultSet query = statement.executeQuery("SELECT * FROM `log_block` WHERE `X` = '" + X + "' AND `Y` = '" + Y + "'AND `Z` = '" + Z + "'");
			
			ArrayList<String> listBlockDate = new ArrayList<String>();
			ArrayList<String> listBlockplayerName = new ArrayList<String>();
			ArrayList<String> listBlockBA = new ArrayList<String>();
			ArrayList<String> listBlockDA = new ArrayList<String>();
			ArrayList<String> listBlockBP = new ArrayList<String>();
			ArrayList<String> listBlockDP = new ArrayList<String>();
						
			if(query != null){
				
				while (query.next()) {
				    listBlockDate.add(query.getString("date"));
				    listBlockplayerName.add(query.getString("playerName"));
				    listBlockBA.add(query.getString("bloque_anterior"));
				    listBlockDA.add(query.getString("data_anterior"));
				    listBlockBP.add(query.getString("bloque_posterior"));
				    listBlockDP.add(query.getString("data_posterior"));
				}
				
				if(!listBlockDate.isEmpty()){
					
					int i = 0;
					boolean bool = true;
					for(String date : listBlockDate){
						
						String bloqueES = "bloque no encontrado.";
						
						if (listBlockBP.get(i).equals(Material.STONE.name())){
							bloqueES = "piedra";
						} else if (listBlockBP.get(i).equals(Material.GRASS.name())){
							bloqueES = "cesped";
						} else if (listBlockBP.get(i).equals(Material.DIRT.name()) && listBlockDP.get(i).equals("0")){
							bloqueES = "tierra";
						} else if (listBlockBP.get(i).equals(Material.DIRT.name()) && listBlockDP.get(i).equals("1")){
							bloqueES = "tierra coarse";
						} else if (listBlockBP.get(i).equals(Material.DIRT.name()) && listBlockDP.get(i).equals("2")){
							bloqueES = "podzol";
						} else if (listBlockBP.get(i).equals(Material.COBBLESTONE.name())){
							bloqueES = "cobblestone";
						} else if (listBlockBP.get(i).equals(Material.WOOD.name()) && listBlockDP.get(i).equals("0")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.WOOD.name()) && listBlockDP.get(i).equals("1")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.WOOD.name()) && listBlockDP.get(i).equals("2")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.WOOD.name()) && listBlockDP.get(i).equals("3")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.WOOD.name()) && listBlockDP.get(i).equals("4")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.WOOD.name()) && listBlockDP.get(i).equals("5")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SAPLING.name()) && listBlockDP.get(i).equals("0")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SAPLING.name()) && listBlockDP.get(i).equals("1")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SAPLING.name()) && listBlockDP.get(i).equals("2")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SAPLING.name()) && listBlockDP.get(i).equals("3")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SAPLING.name()) && listBlockDP.get(i).equals("4")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SAPLING.name()) && listBlockDP.get(i).equals("5")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.BEDROCK.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.WATER.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LAVA.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SAND.name()) && listBlockDP.get(i).equals("0")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SAND.name()) && listBlockDP.get(i).equals("1")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.GRAVEL.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.GOLD_ORE.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.IRON_ORE.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.COAL_ORE.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LOG.name()) && (listBlockDP.get(i).equals("0") || listBlockDP.get(i).equals("4") || listBlockDP.get(i).equals("8"))){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LOG.name()) && (listBlockDP.get(i).equals("1") || listBlockDP.get(i).equals("5") || listBlockDP.get(i).equals("9"))){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LOG.name()) && (listBlockDP.get(i).equals("2") || listBlockDP.get(i).equals("6") || listBlockDP.get(i).equals("10"))){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LOG.name()) && (listBlockDP.get(i).equals("3") || listBlockDP.get(i).equals("7") || listBlockDP.get(i).equals("11"))){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LOG_2.name()) && (listBlockDP.get(i).equals("0") || listBlockDP.get(i).equals("4") || listBlockDP.get(i).equals("8"))){
							bloqueES = "";	
						} else if (listBlockBP.get(i).equals(Material.LOG_2.name()) && (listBlockDP.get(i).equals("1") || listBlockDP.get(i).equals("5") || listBlockDP.get(i).equals("9"))){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LEAVES.name()) && listBlockDP.get(i).equals("0")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LEAVES.name()) && listBlockDP.get(i).equals("1")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LEAVES.name()) && listBlockDP.get(i).equals("2")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LEAVES.name()) && listBlockDP.get(i).equals("3")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SPONGE.name()) && listBlockDP.get(i).equals("0")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.GLASS.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LAPIS_ORE.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LAPIS_BLOCK.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.DISPENSER.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SANDSTONE.name()) && listBlockDP.get(i).equals("0")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SANDSTONE.name()) && listBlockDP.get(i).equals("1")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.SANDSTONE.name()) && listBlockDP.get(i).equals("2")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.NOTE_BLOCK.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.BED_BLOCK.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.POWERED_RAIL.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.DETECTOR_RAIL.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.PISTON_STICKY_BASE.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.WEB.name())){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.DEAD_BUSH.name()) && listBlockDP.get(i).equals("0")){
							bloqueES = "";
						} else if (listBlockBP.get(i).equals(Material.LONG_GRASS.name())){
							bloqueES = "";
						}
						
						
						String color = "";
						
						if(bool){
							color = ChatColor.GOLD + "";
							bool = false;
						} else {
							color = ChatColor.YELLOW + "";
							bool = true;
						}
						
						if(listBlockBA.get(i).equals(Material.AIR.name())){
							
							craftPlayer.sendMessage(color + dateFormatSimple.format(dateFormat.parse(date)) + " > " + listBlockplayerName.get(i).toLowerCase() 
									+ " » ha creado " + listBlockBP.get(i).toLowerCase() + "^" + listBlockDP.get(i));
							
						} else if(listBlockBP.get(i).equals(Material.AIR.name())){
							
							craftPlayer.sendMessage(color + dateFormatSimple.format(dateFormat.parse(date)) + " > " + listBlockplayerName.get(i).toLowerCase() 
									+ " » ha destruido " + listBlockBA.get(i).toLowerCase() + "^" + listBlockDA.get(i));
							
						}
						
						i = i + 1;
					}
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + dateFormatSimple.format(firstDate) + " > Informe sobre el bloque " + X + "|" + Y + "|" + Z + ".");
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + dateFormatSimple.format(firstDate) + " > No hay informes disponibles!");
					
				}
				
				
				
			} 
			
		} else {
			
			statement.executeUpdate("INSERT INTO log_block (`date`, `playerName`, `UUID`, `bloque_anterior`, `data_anterior`,"
					+ "`bloque_posterior`, `data_posterior`, `X`, `Y`, `Z`, `world`, `server`, `activo`, `hand`, `handNum`)"
					+ " VALUES ('" + dateFormat.format(firstDate) + "', '" + playerName + "', '" + playerUUID + "',"
					+ "'" + blockAnt + "', '" + dataAnt + "', '" + Material.AIR + "', '0', '" + X + "', '" + Y + "', '" + Z + "',"
					+ " '" + world + "', '" + server + "', true, '" + hand + "', '" + handNum + "');");
			
		}
		
		connection.closeConnection();
		
	}
	
	@SuppressWarnings("deprecation")
	public static void OnPlace(BlockPlaceEvent eventPlace) throws Exception {
		
		MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
		connection.openConnection();
		Statement statement = connection.getConnection().createStatement();
		
		//info
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final DateFormat dateFormatSimple = new SimpleDateFormat("dd|MM|yy HH:mm");
		Date firstDate = new Date();
		
		final Player craftPlayer = eventPlace.getPlayer();
		final String playerUUID = craftPlayer.getUniqueId().toString();
		String playerName = craftPlayer.getName();
		String hand = craftPlayer.getItemInHand().getType().name();
		int handNum = craftPlayer.getItemInHand().getAmount();
		
		String blockPos = eventPlace.getBlock().getType().name();
		int dataPos = eventPlace.getBlock().getData();
		
		int X = eventPlace.getBlock().getX();
		int Y = eventPlace.getBlock().getY();
		int Z = eventPlace.getBlock().getZ();
		String world = eventPlace.getBlock().getWorld().getName();
		int server = Bukkit.getServer().getPort();
		
		if(hand == Material.BEDROCK.name()){
			
			eventPlace.setCancelled(true);
			
			ResultSet query = statement.executeQuery("SELECT * FROM `log_block` WHERE `X` = '" + X + "' AND `Y` = '" + Y + "'AND `Z` = '" + Z + "'");
			
			ArrayList<String> listBlockDate = new ArrayList<String>();
			ArrayList<String> listBlockplayerName = new ArrayList<String>();
			ArrayList<String> listBlockBA = new ArrayList<String>();
			ArrayList<String> listBlockDA = new ArrayList<String>();
			ArrayList<String> listBlockBP = new ArrayList<String>();
			ArrayList<String> listBlockDP = new ArrayList<String>();
						
			if(query != null){
				
				while (query.next()) {
				    listBlockDate.add(query.getString("date"));
				    listBlockplayerName.add(query.getString("playerName"));
				    listBlockBA.add(query.getString("bloque_anterior"));
				    listBlockDA.add(query.getString("data_anterior"));
				    listBlockBP.add(query.getString("bloque_posterior"));
				    listBlockDP.add(query.getString("data_posterior"));
				}
				
				if(!listBlockDate.isEmpty()){
					
					int i = 0;
					boolean bool = true;
					for(String date : listBlockDate){
						
						String color = "";
						
						if(bool){
							color = ChatColor.GOLD + "";
							bool = false;
						} else {
							color = ChatColor.YELLOW + "";
							bool = true;
						}
						
						if(listBlockBA.get(i).equals(Material.AIR.name())){
							
							craftPlayer.sendMessage(color + dateFormatSimple.format(dateFormat.parse(date)) + " > " + listBlockplayerName.get(i).toLowerCase() 
									+ " » ha creado " + listBlockBP.get(i).toLowerCase() + "^" + listBlockDP.get(i));
							
						} else if(listBlockBP.get(i).equals(Material.AIR.name())){
							
							craftPlayer.sendMessage(color + dateFormatSimple.format(dateFormat.parse(date)) + " > " + listBlockplayerName.get(i).toLowerCase() 
									+ " » ha destruido " + listBlockBA.get(i).toLowerCase() + "^" + listBlockDA.get(i));
							
						}
						
						i = i + 1;
					}
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + dateFormatSimple.format(firstDate) + " > Informe sobre el bloque " + X + "|" + Y + "|" + Z + ".");
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + dateFormatSimple.format(firstDate) + " > No hay informes disponibles!");
					
				}
				
				
				
			} 
			
		} else {
			
		statement.executeUpdate("INSERT INTO log_block (`date`, `playerName`, `UUID`, `bloque_anterior`, `data_anterior`,"
				+ "`bloque_posterior`, `data_posterior`, `X`, `Y`, `Z`, `world`, `server`, `activo`, `hand`, `handNum`)"
				+ " VALUES ('" + dateFormat.format(firstDate) + "', '" + playerName + "', '" + playerUUID + "', '" + Material.AIR+ "', '0',"
				+ " '" + blockPos + "', '" + dataPos + "', '" + X + "', '" + Y + "', '" + Z + "', '" + world + "', '" + server + "', true, '" + hand + "',"
				+ " '" + handNum + "');");
		
		connection.closeConnection();
		
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void onPlayerInteract(PlayerInteractEvent eventInteract) throws Exception {
		
		MySQL connection = new MySQL(BewomBit.main, BewomBit.SQLUrl, BewomBit.SQLPort, BewomBit.SQLbd, BewomBit.SQLUser, BewomBit.SQLPass);
		connection.openConnection();
		Statement statement = connection.getConnection().createStatement();
		
		//info
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date firstDate = new Date();
		
		final Player craftPlayer = eventInteract.getPlayer();
		final String playerUUID = craftPlayer.getUniqueId().toString();
		String playerName = craftPlayer.getName();
		String hand = craftPlayer.getItemInHand().getType().name();
		int handNum = craftPlayer.getItemInHand().getAmount();
		
		int X = craftPlayer.getTargetBlock(null, 5).getX();
		int Y = craftPlayer.getTargetBlock(null, 5).getY();
		int Z = craftPlayer.getTargetBlock(null, 5).getZ();
		String world = craftPlayer.getWorld().getName();
		int server = Bukkit.getServer().getPort();
		
		if(eventInteract.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			craftPlayer.sendMessage(eventInteract.getClickedBlock().getType().name());
			
			String interactBlock = eventInteract.getClickedBlock().getType().name();
			
			/*
			 * || interactBlock == Material.IRON_DOOR.name()
			 * || interactBlock == Material.IRON_PLATE.name()
			 * || interactBlock == Material.WOOD_PLATE.name()
			 * || interactBlock == Material.GOLD_PLATE.name()
			 * || interactBlock == Material.STONE_PLATE.name()
			 * || interactBlock == Material.TRIPWIRE.name()
			 * 
			 */
			
			if(interactBlock == Material.WOOD_BUTTON.name()
					|| interactBlock == Material.WOOD_DOOR.name() 
					|| interactBlock == Material.STONE_BUTTON.name()
					|| interactBlock == Material.WOOD_BUTTON.name()
					|| interactBlock == Material.CHEST.name()
					|| interactBlock == Material.ENDER_CHEST.name()
					|| interactBlock == Material.BEACON.name()
					|| interactBlock == Material.FURNACE.name()
					|| interactBlock == Material.WORKBENCH.name()
					|| interactBlock == Material.JUKEBOX.name()
					|| interactBlock == Material.ENCHANTMENT_TABLE.name()
					|| interactBlock == Material.ANVIL.name()
					|| interactBlock == Material.LEVER.name()
					|| interactBlock == Material.DROPPER.name()
					|| interactBlock == Material.DISPENSER.name()){
				
				statement.executeUpdate("INSERT INTO `log_interact`(`date`, `playerName`, `UUID`, `hand`, `handNum`,"
						+ " `bloque`, `X`, `Y`, `Z`, `world`, `server`) VALUES ('" + dateFormat.format(firstDate) + "', '" + playerName + "',"
						+ " '" + playerUUID + "', '" + hand + "', '" + handNum + "', '" + interactBlock + "',"
						+ " '" + X + "', '" + Y + "', '" + Z + "', '" + world + "', '" + server + "');");

			}
			
		}
		
		connection.closeConnection();
		
	}

}

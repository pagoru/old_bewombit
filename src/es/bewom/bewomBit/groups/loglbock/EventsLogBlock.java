package es.bewom.bewomBit.groups.loglbock;

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
	
	@SuppressWarnings({ "deprecation"})
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
						
						int idBloque = Integer.parseInt(listBlockDP.get(i));
						
						String bloqueESa = comprobarBloque (listBlockBA.get(i), idBloque);
						String bloqueESp = comprobarBloque (listBlockBP.get(i), idBloque);
						
						String color = "";
						
						if(bool){
							color = ChatColor.GOLD + "";
							bool = false;
						} else {
							color = ChatColor.YELLOW + "";
							bool = true;
						}
						
						if(listBlockBA.get(i).equals(Material.AIR.name())){
							
							craftPlayer.sendMessage(color + dateFormatSimple.format(dateFormat.parse(date)) + " " + listBlockplayerName.get(i).toLowerCase() 
									+ " » creado " + bloqueESp);
							
						} else if(listBlockBP.get(i).equals(Material.AIR.name())){
							
							craftPlayer.sendMessage(color + dateFormatSimple.format(dateFormat.parse(date)) + " " + listBlockplayerName.get(i).toLowerCase() 
									+ " » destruido " + bloqueESa);
							
						}
						
						i = i + 1;
					}
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + dateFormatSimple.format(firstDate) + " Informe sobre " + X + "|" + Y + "|" + Z + ".");
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + dateFormatSimple.format(firstDate) + " No hay informes disponibles!");
					
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
						
						int idBloque = Integer.parseInt(listBlockDP.get(i));
						
						String bloqueESa = comprobarBloque (listBlockBA.get(i), idBloque);
						String bloqueESp = comprobarBloque (listBlockBP.get(i), idBloque);
						
						String color = "";
						
						if(bool){
							color = ChatColor.GOLD + "";
							bool = false;
						} else {
							color = ChatColor.YELLOW + "";
							bool = true;
						}
						
						if(listBlockBA.get(i).equals(Material.AIR.name())){
							
							craftPlayer.sendMessage(color + dateFormatSimple.format(dateFormat.parse(date)) + " " + listBlockplayerName.get(i).toLowerCase() 
									+ " » creado " + bloqueESp);
							
						} else if(listBlockBP.get(i).equals(Material.AIR.name())){
							
							craftPlayer.sendMessage(color + dateFormatSimple.format(dateFormat.parse(date)) + " " + listBlockplayerName.get(i).toLowerCase() 
									+ " » destruido " + bloqueESa);
							
						}
						
						i = i + 1;
					}
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + dateFormatSimple.format(firstDate) + " Informe sobre " + X + "|" + Y + "|" + Z + ".");
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + dateFormatSimple.format(firstDate) + " No hay informes disponibles!");
					
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
	
	public static String comprobarBloque (String material, int id){
		
		String bloqueES = "*" + material + "^" + id + "*";		

		if (material.equals(Material.STONE.name())){
			bloqueES = "stone";
		} else if (material.equals(Material.GRASS.name())){
			bloqueES = "grass block";
		} else if (material.equals(Material.DIRT.name())){
			if(id == 0){
				bloqueES = "dirt";
			} else if(id == 1){
				bloqueES = "coarse dirt";
			} else if(id == 2){
				bloqueES = "podzol";
			}
		} else if (material.equals(Material.COBBLESTONE.name())){
			bloqueES = "cobblestone";
		} else if (material.equals(Material.WOOD.name())){
			if( id == 0){
				bloqueES = "oak wood plank";
			} else if (id == 1){
				bloqueES = "spruce wood plank";
			} else if (id == 2){
				bloqueES = "birch wood plank";
			} else if (id == 3){
				bloqueES = "junlge wood plank";
			} else if (id == 4){
				bloqueES = "acacia wood plank";
			} else if (id == 5){
				bloqueES = "dark oak wood plank";
			}
		} else if (material.equals(Material.SAPLING.name())){
			if(id == 0){
				bloqueES = "oak sapling";
			} else if (id == 1){
				bloqueES = "spruce sapling";
			} else if (id == 2){
				bloqueES = "birch sapling";
			} else if (id == 3){
				bloqueES = "jungle sapling";
			} else if (id == 4){
				bloqueES = "acacia oak";
			} else if (id == 5){
				bloqueES = "dark oak sapling";
			}
		} else if (material.equals(Material.BEDROCK.name())){
			bloqueES = "bedrock";
		} else if (material.equals(Material.WATER.name())){
			bloqueES = "water";
		} else if (material.equals(Material.LAVA.name())){
			bloqueES = "lava";
		} else if (material.equals(Material.SAND.name())){
			if(id == 0){
				bloqueES = "sand";
			} else if (id == 1){
				bloqueES = "red sand";
			}
		} else if (material.equals(Material.GRAVEL.name())){
			bloqueES = "gravel";
		} else if (material.equals(Material.GOLD_ORE.name())){
			bloqueES = "gold ore";
		} else if (material.equals(Material.IRON_ORE.name())){
			bloqueES = "iron ore";
		} else if (material.equals(Material.COAL_ORE.name())){
			bloqueES = "coal ore";
		} else if (material.equals(Material.LOG.name())){
			if(id == 0 || id == 4 || id == 8){
				bloqueES = "oak wood";
			} else if (id == 1 || id == 5 || id == 9){
				bloqueES = "spruce wood";
			} else if (id == 2 || id == 6 || id == 10){
				bloqueES = "birch wood";
			} else if (id == 3 || id == 7 || id == 11){
				bloqueES = "jungle wood";
			}
		} else if (material.equals(Material.LEAVES.name()) && id == 0){
			if(id == 0){
				bloqueES = "oak leaves";
			} else if (id == 1){
				bloqueES = "spruce leaves";
			} else if (id == 2){
				bloqueES = "birch leaves";
			} else if (id == 3){
				bloqueES = "jungle leaves";
			}
		} else if (material.equals(Material.SPONGE.name())){
			bloqueES = "sponge";
		} else if (material.equals(Material.GLASS.name())){
			bloqueES = "glass";
		} else if (material.equals(Material.LAPIS_ORE.name())){
			bloqueES = "lapis lazuli ore";
		} else if (material.equals(Material.LAPIS_BLOCK.name())){
			bloqueES = "lapis lazuli block";
		} else if (material.equals(Material.DISPENSER.name())){
			bloqueES = "dispenser";
		} else if (material.equals(Material.SANDSTONE.name())){
			if(id == 0){
				bloqueES = "sandstone";
			} else if (id == 1){
				bloqueES = "chiseled sandstone";
			} else if (id == 2){
				bloqueES = "smooth sandstone";
			} 
		}  else if (material.equals(Material.NOTE_BLOCK.name())){
			bloqueES = "note block";
		} else if (material.equals(Material.BED_BLOCK.name())){
			bloqueES = "bed";
		} else if (material.equals(Material.POWERED_RAIL.name())){
			bloqueES = "powered rail";
		} else if (material.equals(Material.DETECTOR_RAIL.name())){
			bloqueES = "detector rail";
		} else if (material.equals(Material.PISTON_STICKY_BASE.name())){
			bloqueES = "sticky piston";
		} else if (material.equals(Material.WEB.name())){
			bloqueES = "cobweb";
		} else if (material.equals(Material.DEAD_BUSH.name())){
			bloqueES = "dead shrub";
		} else if (material.equals(Material.LONG_GRASS.name())){
			if(id == 0){
				bloqueES = "grass";
			} else if (id == 1){
				bloqueES = "fern";
			} else if (id == 2){
				bloqueES = "dead shrub";
			} else if (id == 3){
				bloqueES = "piston";
			}
		} else if (material.equals(Material.PISTON_BASE.name())){
			bloqueES = "piston head";
		} else if (material.equals(Material.WOOL.name())){
			if(id == 0){
				bloqueES = "white wool";
			} else if (id == 1){
				bloqueES = "orange wool";
			} else if (id == 2){
				bloqueES = "magenta wool";
			} else if (id == 3){
				bloqueES = "light blue wool";
			} else if (id == 4){
				bloqueES = "yellow wool";
			} else if (id == 5){
				bloqueES = "lime wool";
			} else if (id == 6){
				bloqueES = "pink wool";
			} else if (id == 7){
				bloqueES = "gray wool";
			} else if (id == 8){
				bloqueES = "light gray wool";
			} else if (id == 9){
				bloqueES = "cyan wool";
			} else if (id == 10){
				bloqueES = "purple wool";
			} else if (id == 11){
				bloqueES = "blue wool";
			} else if (id == 12){
				bloqueES = "brown wool";
			} else if (id == 13){
				bloqueES = "green wool";
			} else if (id == 14){
				bloqueES = "red wool";
			} else if (id == 15){
				bloqueES = "black wool";
			}
		} else if (material.equals(Material.YELLOW_FLOWER.name())){
			bloqueES = "dandelion";
		} else if (material.equals(Material.RED_ROSE.name())){
			if(id == 0){
				bloqueES = "poppy";
			} else if (id == 1){
				bloqueES = "blue orchid";
			} else if (id == 2){
				bloqueES = "allium";
			} else if (id == 3){
				bloqueES = "azure bluet";
			} else if (id == 4){
				bloqueES = "red tulip";
			} else if (id == 5){
				bloqueES = "orange tulip";
			} else if (id == 6){
				bloqueES = "white tulip";
			} else if (id == 7){
				bloqueES = "pink tulip";
			} else if (id == 8){
				bloqueES = "oxeye daisy";
			}
		} else if (material.equals(Material.BROWN_MUSHROOM.name())){
			bloqueES = "brown mushroom";
		} else if (material.equals(Material.RED_MUSHROOM.name())){
			bloqueES = "red mushroom";
		} else if (material.equals(Material.GOLD_BLOCK.name())){
			bloqueES = "gold block";
		} else if (material.equals(Material.IRON_BLOCK.name())){
			bloqueES = "iron block";
		} else if (material.equals(Material.DOUBLE_STEP.name())){
			if(id == 0){
				bloqueES = "double stone slab";
			} else if (id == 1){
				bloqueES = "double sandstone slab";
			} else if (id == 2){
				bloqueES = "double wooden slab";
			} else if (id == 3){
				bloqueES = "double cobblestone slab";
			} else if (id == 4){
				bloqueES = "double brick slab";
			} else if (id == 5){
				bloqueES = "double stone brick slab";
			} else if (id == 6){
				bloqueES = "double nether brick slab";
			} else if (id == 7){
				bloqueES = "double quartz slab";
			}
		} else if (material.equals(Material.STEP.name())){
			if(id == 0){
				bloqueES = "stone slab";
			} else if (id == 1){
				bloqueES = "sandstone slab";
			} else if (id == 2){
				bloqueES = "wooden slab";
			} else if (id == 3){
				bloqueES = "cobblestone slab";
			} else if (id == 4){
				bloqueES = "brick slab";
			} else if (id == 5){
				bloqueES = "stone brick slab";
			} else if (id == 6){
				bloqueES = "nether birck slab";
			} else if (id == 7){
				bloqueES = "quartz slab";
			}
		} else if (material.equals(Material.BRICK.name())){
			bloqueES = "bricks";
		} else if (material.equals(Material.TNT.name())){
			bloqueES = "tnt";
		} else if (material.equals(Material.BOOKSHELF.name())){
			bloqueES = "bookshelf";
		} else if (material.equals(Material.MOSSY_COBBLESTONE.name())){
			bloqueES = "moss stone";
		} else if (material.equals(Material.OBSIDIAN.name())){
			bloqueES = "obsidian";
		} else if (material.equals(Material.TORCH.name())){
			bloqueES = "torch";
		} else if (material.equals(Material.FIRE.name())){
			bloqueES = "fire";
		} else if (material.equals(Material.MOB_SPAWNER.name())){
			bloqueES = "monster spawner";
		} else if (material.equals(Material.WOOD_STAIRS.name())){
			bloqueES = "oak wood stairs";
		} else if (material.equals(Material.CHEST.name())){
			bloqueES = "chest";
		} else if (material.equals(Material.REDSTONE_WIRE.name())){
			bloqueES = "redstone wire";
		} else if (material.equals(Material.DIAMOND_ORE.name())){
			bloqueES = "diamond ore";
		} else if (material.equals(Material.DIAMOND_BLOCK.name())){
			bloqueES = "diamond block";
		} else if (material.equals(Material.WORKBENCH.name())){
			bloqueES = "crafting table";
		} else if (material.equals(Material.CROPS.name())){
			bloqueES = "wheat crops";
		} else if (material.equals(Material.SOIL.name())){
			bloqueES = "farmland";
		} else if (material.equals(Material.FURNACE.name())){
			bloqueES = "furnace";
		} else if (material.equals(Material.SIGN_POST.name())){
			bloqueES = "sign";
		} else if (material.equals(Material.WOOD_DOOR.name())){
			bloqueES = "oak wooden door";
		} else if (material.equals(Material.LADDER.name())){
			bloqueES = "ladder";
		} else if (material.equals(Material.RAILS.name())){
			bloqueES = "rail";
		} else if (material.equals(Material.COBBLESTONE_STAIRS.name())){
			bloqueES = "cobblestone stairs";
		} else if (material.equals(Material.WALL_SIGN.name())){
			bloqueES = "wall sign";
		} else if (material.equals(Material.LEVER.name())){
			bloqueES = "lever";
		} else if (material.equals(Material.STONE_PLATE.name())){
			bloqueES = "stone pressure plate";
		} else if (material.equals(Material.IRON_DOOR.name())){
			bloqueES = "iron door";
		} else if (material.equals(Material.WOOD_PLATE.name())){
			bloqueES = "wodden pressure plate";
		} else if (material.equals(Material.REDSTONE_ORE.name())){
			bloqueES = "redstone ore";
		} else if (material.equals(Material.REDSTONE_TORCH_ON.name())){
			bloqueES = "redstone torch (on)";
		} else if (material.equals(Material.REDSTONE_TORCH_OFF.name())){
			bloqueES = "redstone torch (off)";
		} else if (material.equals(Material.STONE_BUTTON.name())){
			bloqueES = "stone button";
		} else if (material.equals(Material.SNOW.name())){
			if(id == 0){
				bloqueES = "snow (1)";
			} else if (id == 1){
				bloqueES = "snow (2)";
			} else if (id == 2){
				bloqueES = "snow (3)";
			} else if (id == 3){
				bloqueES = "snow (4)";
			} else if (id == 4){
				bloqueES = "snow (5)";
			} else if (id == 5){
				bloqueES = "snow (6)";
			} else if (id == 6){
				bloqueES = "snow (7)";
			} else if (id == 7){
				bloqueES = "snow (8)";
			}
		} else if (material.equals(Material.ICE.name())){
			bloqueES = "ice";
		} else if (material.equals(Material.SNOW_BLOCK.name())){
			bloqueES = "snow block";
		} else if (material.equals(Material.CACTUS.name())){
			bloqueES = "cactus";
		} else if (material.equals(Material.CLAY.name())){
			bloqueES = "clay";
		} else if (material.equals(Material.SUGAR_CANE_BLOCK.name())){
			bloqueES = "sugar canes";
		} else if (material.equals(Material.JUKEBOX.name())){
			bloqueES = "jukebox";
		} else if (material.equals(Material.FENCE.name())){
			bloqueES = "oak fences";
		} else if (material.equals(Material.PUMPKIN.name())){
			bloqueES = "pumpkin";
		} else if (material.equals(Material.NETHERRACK.name())){
			bloqueES = "netherrack";
		} else if (material.equals(Material.SOUL_SAND.name())){
			bloqueES = "soul sand";
		} else if (material.equals(Material.GLOWSTONE.name())){
			bloqueES = "glowstone";
		} else if (material.equals(Material.PORTAL.name())){
			bloqueES = "nether portal";
		} else if (material.equals(Material.JACK_O_LANTERN.name())){
			bloqueES = "jack o'lantern";
		} else if (material.equals(Material.CAKE_BLOCK.name())){
			bloqueES = "cake";
		} else if (material.equals(Material.DIODE_BLOCK_OFF.name())){
			bloqueES = "redstone repeater (off)";
		} else if (material.equals(Material.DIODE_BLOCK_ON.name())){
			bloqueES = "redstone repeater (on)";
		} else if (material.equals(Material.STAINED_GLASS.name())){
			if(id == 0){
				bloqueES = "white stained glass";
			} else if (id == 1){
				bloqueES = "orange stained glass";
			} else if (id == 2){
				bloqueES = "magenta stained glass";
			} else if (id == 3){
				bloqueES = "light blue stained glass";
			} else if (id == 4){
				bloqueES = "yellow stained glass";
			} else if (id == 5){
				bloqueES = "lime stained glass";
			} else if (id == 6){
				bloqueES = "pink stained glass";
			} else if (id == 7){
				bloqueES = "gray stained glass";
			} else if (id == 8){
				bloqueES = "light gray stained glass";
			} else if (id == 9){
				bloqueES = "cyan stained glass";
			} else if (id == 10){
				bloqueES = "purple stained glass";
			} else if (id == 11){
				bloqueES = "blue stained glass";
			} else if (id == 12){
				bloqueES = "brown stained glass";
			} else if (id == 13){
				bloqueES = "green stained glass";
			} else if (id == 14){
				bloqueES = "red stained glass";
			} else if (id == 15){
				bloqueES = "black stained glass";
			}
		} else if (material.equals(Material.TRAP_DOOR.name())){
			bloqueES = "wooden trapdoor";
		} else if (material.equals(Material.MONSTER_EGGS.name())){
			if(id == 0){
				bloqueES = "stone monster egg";
			} else if (id == 1){
				bloqueES = "cobblestone monster egg";
			} else if (id == 2){
				bloqueES = "stone brick monster egg";
			} else if (id == 3){
				bloqueES = "mossy stone brick monster egg";
			} else if (id == 4){
				bloqueES = "cracked stone brick monster egg";
			} else if (id == 5){
				bloqueES = "chiseled stone brick monster egg";
			}
		} else if (material.equals(Material.SMOOTH_BRICK.name())){
			if(id == 0){
				bloqueES = "stone bricks";
			} else if (id == 1){
				bloqueES = "mossy stone bricks";
			} else if (id == 2){
				bloqueES = "cracked stone bricks";
			} else if (id == 3){
				bloqueES = "chiseled stone bricks";
			}
		} else if (material.equals(Material.HUGE_MUSHROOM_1.name())){
			bloqueES = "red mushroom cap";
		} else if (material.equals(Material.HUGE_MUSHROOM_2.name())){
			bloqueES = "brown mushroom cap";
		} else if (material.equals(Material.IRON_FENCE.name())){
			bloqueES = "iron bars";
		} else if (material.equals(Material.THIN_GLASS.name())){
			bloqueES = "glass pane";
		} else if (material.equals(Material.MELON_BLOCK.name())){
			bloqueES = "melon";
		} else if (material.equals(Material.PUMPKIN_STEM.name())){
			bloqueES = "pumpkin stem";
		} else if (material.equals(Material.MELON_STEM.name())){
			bloqueES = "melon stem";
		} else if (material.equals(Material.VINE.name())){
			bloqueES = "vines";
		} else if (material.equals(Material.FENCE_GATE.name())){
			bloqueES = "oak fence gate";
		} else if (material.equals(Material.BRICK_STAIRS.name())){
			bloqueES = "brick stairs";
		} else if (material.equals(Material.SMOOTH_STAIRS.name())){
			bloqueES = "stone brick stairs";
		} else if (material.equals(Material.MYCEL.name())){
			bloqueES = "mycelium";
		} else if (material.equals(Material.WATER_LILY.name())){
			bloqueES = "lily pad";
		} else if (material.equals(Material.NETHER_BRICK.name())){
			bloqueES = "nether brick";
		} else if (material.equals(Material.NETHER_FENCE.name())){
			bloqueES = "nether brick fence";
		} else if (material.equals(Material.NETHER_BRICK_STAIRS.name())){
			bloqueES = "nether brick stairs";
		} else if (material.equals(Material.NETHER_WARTS.name())){
			bloqueES = "nether wart";
		} else if (material.equals(Material.ENCHANTMENT_TABLE.name())){
			bloqueES = "enchantment table";
		} else if (material.equals(Material.BREWING_STAND.name())){
			bloqueES = "brewing stand";
		} else if (material.equals(Material.CAULDRON.name())){
			bloqueES = "cauldron";
		} else if (material.equals(Material.ENDER_PORTAL.name())){
			bloqueES = "end portal";
		} else if (material.equals(Material.ENDER_PORTAL_FRAME.name())){
			bloqueES = "end portal frame";
		} else if (material.equals(Material.ENDER_STONE.name())){
			bloqueES = "end stone";
		} else if (material.equals(Material.DRAGON_EGG.name())){
			bloqueES = "dragon egg";
		} else if (material.equals(Material.REDSTONE_LAMP_ON.name())){
			bloqueES = "redstone lamp (on)";
		} else if (material.equals(Material.REDSTONE_LAMP_OFF.name())){
			bloqueES = "redstone lamp (off)";
		} else if (material.equals(Material.WOOD_DOUBLE_STEP.name())){
			if(id == 0){
				bloqueES = "double oak wood slab";
			} else if (id == 1){
				bloqueES = "double spruce wood slab";
			} else if (id == 2){
				bloqueES = "double birch wood slab";
			} else if (id == 3){
				bloqueES = "double jungle wood slab";
			} else if (id == 4){
				bloqueES = "double acacia wood slab";
			} else if (id == 5){
				bloqueES = "double dark oak wood slab";
			}
		} else if (material.equals(Material.WOOD_STEP.name())){
			if(id == 0){
				bloqueES = "oak wood slab";
			} else if (id == 1){
				bloqueES = "spruce wood slab";
			} else if (id == 2){
				bloqueES = "birch wood slab";
			} else if (id == 3){
				bloqueES = "jungle wood slab";
			} else if (id == 4){
				bloqueES = "acacia wood slab";
			} else if (id == 5){
				bloqueES = "dark oak wood slab";
			}
		} else if (material.equals(Material.COCOA.name())){
			bloqueES = "cocoa";
		} else if (material.equals(Material.SANDSTONE_STAIRS.name())){
			bloqueES = "sanstone stairs";
		} else if (material.equals(Material.EMERALD_ORE.name())){
			bloqueES = "emerald ore";
		} else if (material.equals(Material.ENDER_CHEST.name())){
			bloqueES = "ender chest";
		} else if (material.equals(Material.TRIPWIRE_HOOK.name())){
			bloqueES = "tripwire hook";
		} else if (material.equals(Material.TRIPWIRE.name())){
			bloqueES = "tripwire";
		} else if (material.equals(Material.EMERALD_BLOCK.name())){
			bloqueES = "emerald block";
		} else if (material.equals(Material.SPRUCE_WOOD_STAIRS.name())){
			bloqueES = "spruce wood stairs";
		} else if (material.equals(Material.BIRCH_WOOD_STAIRS.name())){
			bloqueES = "birch wood stairs";
		} else if (material.equals(Material.JUNGLE_WOOD_STAIRS.name())){
			bloqueES = "jungle wood stairs";
		} else if (material.equals(Material.COMMAND.name())){
			bloqueES = "command block";
		} else if (material.equals(Material.BEACON.name())){
			bloqueES = "beacon";
		} else if (material.equals(Material.COBBLE_WALL.name())){
			if(id == 0){
				bloqueES = "cobblestone wall";
			} else if (id == 1){
				bloqueES = "mossy cobblestone wall";
			}
		} else if (material.equals(Material.FLOWER_POT.name())){
			bloqueES = "flower pot";
		} else if (material.equals(Material.CARROT.name())){
			bloqueES = "carrots";
		} else if (material.equals(Material.POTATO.name())){
			bloqueES = "potatoes";
		} else if (material.equals(Material.WOOD_BUTTON.name())){
			bloqueES = "wooden button";
		} else if (material.equals(Material.SKULL.name())){
			bloqueES = "mob head";
		} else if (material.equals(Material.ANVIL.name())){
			bloqueES = "anvil";
		} else if (material.equals(Material.TRAPPED_CHEST.name())){
			bloqueES = "trapped chest";
		} else if (material.equals(Material.GOLD_PLATE.name())){
			bloqueES = "gold pressure plate";
		} else if (material.equals(Material.IRON_PLATE.name())){
			bloqueES = "iron pressure plate";
		} else if (material.equals(Material.REDSTONE_COMPARATOR_ON.name())){
			bloqueES = "redstone comparator (on)";
		} else if (material.equals(Material.REDSTONE_COMPARATOR_OFF.name())){
			bloqueES = "redstone comparator (off)";
		} else if (material.equals(Material.DAYLIGHT_DETECTOR.name())){
			bloqueES = "daylight sensor";
		} else if (material.equals(Material.REDSTONE_BLOCK.name())){
			bloqueES = "redstone block";
		} else if (material.equals(Material.QUARTZ_ORE.name())){
			bloqueES = "nether quartz ore";
		} else if (material.equals(Material.HOPPER.name())){
			bloqueES = "hopper";
		} else if (material.equals(Material.QUARTZ_BLOCK.name())){
			if(id == 0){
				bloqueES = "quartz block";
			} else if (id == 1){
				bloqueES = "chiseled quartz block";
			} else if (id == 2){
				bloqueES = "pillar quartz block";
			}
		} else if (material.equals(Material.QUARTZ_STAIRS.name())){
			bloqueES = "quartz stairs";
		} else if (material.equals(Material.ACTIVATOR_RAIL.name())){
			bloqueES = "activator rail";
		} else if (material.equals(Material.DROPPER.name())){
			bloqueES = "dropper";
		} else if (material.equals(Material.STAINED_CLAY.name())){
			if(id == 0){
				bloqueES = "white stained clay";
			} else if (id == 1){
				bloqueES = "orange stained clay";
			} else if (id == 2){
				bloqueES = "magenta stained clay";
			} else if (id == 3){
				bloqueES = "light blue stained clay";
			} else if (id == 4){
				bloqueES = "yellow stained clay";
			} else if (id == 5){
				bloqueES = "lime stained clay";
			} else if (id == 6){
				bloqueES = "pink stained clay";
			} else if (id == 7){
				bloqueES = "gray stained clay";
			} else if (id == 8){
				bloqueES = "light gray stained clay";
			} else if (id == 9){
				bloqueES = "cyan stained clay";
			} else if (id == 10){
				bloqueES = "purple stained clay";
			} else if (id == 11){
				bloqueES = "blue stained clay";
			} else if (id == 12){
				bloqueES = "brown stained clay";
			} else if (id == 13){
				bloqueES = "green stained clay";
			} else if (id == 14){
				bloqueES = "red stained clay";
			} else if (id == 15){
				bloqueES = "black stained clay";
			}
		} else if (material.equals(Material.STAINED_GLASS_PANE.name())){
			if(id == 0){
				bloqueES = "white stained glass pane";
			} else if (id == 1){
				bloqueES = "orange stained glass pane";
			} else if (id == 2){
				bloqueES = "magenta stained glass pane";
			} else if (id == 3){
				bloqueES = "light blue stained glass pane";
			} else if (id == 4){
				bloqueES = "yellow stained glass pane";
			} else if (id == 5){
				bloqueES = "lime stained glass pane";
			} else if (id == 6){
				bloqueES = "pink stained glass pane";
			} else if (id == 7){
				bloqueES = "gray stained glass pane";
			} else if (id == 8){
				bloqueES = "light gray stained glass pane";
			} else if (id == 9){
				bloqueES = "cyan stained glass pane";
			} else if (id == 10){
				bloqueES = "purple stained glass pane";
			} else if (id == 11){
				bloqueES = "blue stained glass pane";
			} else if (id == 12){
				bloqueES = "brown stained glass pane";
			} else if (id == 13){
				bloqueES = "green stained glass pane";
			} else if (id == 14){
				bloqueES = "red stained glass pane";
			} else if (id == 15){
				bloqueES = "black stained glass pane";
			}
		} else if (material.equals(Material.LEAVES_2.name())){
			bloqueES = "acacia/dark oak leaves";
		} else if (material.equals(Material.LOG_2.name())){
			if(id == 0 || id == 4 || id == 8){
				bloqueES = "acacia wood";
			} else if(id == 1 || id == 5 || id == 9){
				bloqueES = "dark oak wood";
			}
		} else if (material.equals(Material.ACACIA_STAIRS.name())){
			bloqueES = "acacia wood stairs";
		} else if (material.equals(Material.DARK_OAK_STAIRS.name())){
			bloqueES = "dark oak wood stairs";
		} else if (material.equals(Material.HAY_BLOCK.name())){
			bloqueES = "hay bale";
		} else if (material.equals(Material.CARPET.name())){
			if(id == 0){
				bloqueES = "white carpet";
			} else if (id == 1){
				bloqueES = "orange carpet";
			} else if (id == 2){
				bloqueES = "magenta carpet";
			} else if (id == 3){
				bloqueES = "light blue carpet";
			} else if (id == 4){
				bloqueES = "yellow carpet";
			} else if (id == 5){
				bloqueES = "lime carpet";
			} else if (id == 6){
				bloqueES = "pink carpet";
			} else if (id == 7){
				bloqueES = "gray carpet";
			} else if (id == 8){
				bloqueES = "light gray carpet";
			} else if (id == 9){
				bloqueES = "cyan carpet";
			} else if (id == 10){
				bloqueES = "purple carpet";
			} else if (id == 11){
				bloqueES = "blue carpet";
			} else if (id == 12){
				bloqueES = "brown carpet";
			} else if (id == 13){
				bloqueES = "green carpet";
			} else if (id == 14){
				bloqueES = "red carpet";
			} else if (id == 15){
				bloqueES = "black carpet";
			}
		} else if (material.equals(Material.HARD_CLAY.name())){
			bloqueES = "hardened clay";
		} else if (material.equals(Material.COAL_BLOCK.name())){
			bloqueES = "block of coal";
		} else if (material.equals(Material.PACKED_ICE.name())){
			bloqueES = "packed ice";
		} else if (material.equals(Material.DOUBLE_PLANT.name())){
			if(id == 0){
				bloqueES = "sunflower";
			} else if (id == 1){
				bloqueES = "lilac";
			} else if (id == 2){
				bloqueES = "double tallgrass";
			} else if (id == 3){
				bloqueES = "large fern";
			} else if (id == 4){
				bloqueES = "rose bush";
			} else if (id == 5){
				bloqueES = "peony";
			}
		} else if (material.equals(Material.FENCE_GATE.name())){
			bloqueES = "spruce fence gate";
		} else if (material.equals(Material.FENCE.name())){
			bloqueES = "spruce fence";
		}
	
		
		return bloqueES;
	}
}

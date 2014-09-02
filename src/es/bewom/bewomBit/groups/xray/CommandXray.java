package es.bewom.bewomBit.groups.xray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandXray {

	public static boolean commandxray (CommandSender sender, Command cmd, String label, String [] args) throws FileNotFoundException, IOException, InvalidConfigurationException{

		if (label.equalsIgnoreCase("xray")){

			if (args.length == 1){

				Player craftPlayer = (Player) sender;

				DecimalFormat df = new DecimalFormat("#.##");
				DecimalFormat dfsin = new DecimalFormat("#");

				File rayData = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
				File f = new File(rayData, File.separator + "rxray.yml");
				FileConfiguration xrayData = YamlConfiguration.loadConfiguration(f);

				if(args[0].equals("top")){

					ArrayList<String> myList = new ArrayList<String>();
					boolean color = true;

					for (OfflinePlayer p : Bukkit.getOfflinePlayers()){

						String playerNameArgs = p.getName();

						xrayData.load(f);

						double iStone = xrayData.getDouble(playerNameArgs + ".Stone");
						double iCoal = xrayData.getDouble(playerNameArgs + ".Coal_Ore");
						double iIron = xrayData.getDouble(playerNameArgs + ".Iron_Ore");
						double iGold = xrayData.getDouble(playerNameArgs + ".Gold_Ore");
						double iRedstone = xrayData.getDouble(playerNameArgs + ".Redstone_Ore");
						double iLapis = xrayData.getDouble(playerNameArgs + ".Lapis_Ore");
						double iDiamond = xrayData.getDouble(playerNameArgs + ".Diamond_Ore");

						double StatsCoal = (iCoal/(iStone*0.03))*100;
						double StatsIron = (iIron/(iStone*0.015))*100;
						double StatsGold = (iGold/(iStone*0.0013))*100;
						double StatsRedstone = (iRedstone/(iStone*0.002))*100;
						double StatsLapis = (iLapis/(iStone*0.001))*100;
						double StatsDiamond = (iDiamond/(iStone*0.0015))*100;	

						if(StatsCoal > 90 || StatsIron > 90 || StatsGold > 90 || StatsRedstone > 90 || StatsLapis > 90 || StatsDiamond > 90){

							if(color == true){
								myList.add(ChatColor.BLUE + playerNameArgs + ChatColor.WHITE);	
								color = false;
							}
							else {
								myList.add(ChatColor.LIGHT_PURPLE + playerNameArgs + ChatColor.WHITE);	
								color = true;
							}
						}
						xrayData.save(f);
					}
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Sospechosos: " + ChatColor.WHITE + myList.toString());
				}
				else {

					String playerName = args[0];

					xrayData.load(f);

					double iStone = xrayData.getDouble(playerName + ".Stone");
					double iCoal = xrayData.getDouble(playerName + ".Coal_Ore");
					double iIron = xrayData.getDouble(playerName + ".Iron_Ore");
					double iGold = xrayData.getDouble(playerName + ".Gold_Ore");
					double iRedstone = xrayData.getDouble(playerName + ".Redstone_Ore");
					double iLapis = xrayData.getDouble(playerName + ".Lapis_Ore");
					double iDiamond = xrayData.getDouble(playerName + ".Diamond_Ore");
					double iEmerald = xrayData.getDouble(playerName + ".Emerald_Ore");

					double StatsCoal = (iCoal/(iStone*0.03))*100;
					double StatsIron = (iIron/(iStone*0.015))*100;
					double StatsGold = (iGold/(iStone*0.0013))*100;
					double StatsRedstone = (iRedstone/(iStone*0.002))*100;
					double StatsLapis = (iLapis/(iStone*0.001))*100;
					double StatsDiamond = (iDiamond/(iStone*0.0015))*100;				

					String coalColor = comprobarColor (StatsCoal);
					String ironColor = comprobarColor (StatsIron);
					String goldColor = comprobarColor (StatsGold);
					String redstoneColor = comprobarColor (StatsRedstone);
					String lapisColor = comprobarColor (StatsLapis);
					String diamondColor = comprobarColor (StatsDiamond);		

					craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Minerales picados de " + args[0] + ":");	
					craftPlayer.sendMessage(ChatColor.GREEN + dfsin.format(iStone) + " de piedra.");
					craftPlayer.sendMessage(coalColor + dfsin.format(iCoal) + " de carbon - " + ChatColor.BOLD + df.format(StatsCoal) + "%");
					craftPlayer.sendMessage(ironColor + dfsin.format(iIron) + " de hierro - " + ChatColor.BOLD + df.format(StatsIron) + "%");
					craftPlayer.sendMessage(goldColor + dfsin.format(iGold) + " de oro - " + ChatColor.BOLD + df.format(StatsGold) + "%");
					craftPlayer.sendMessage(redstoneColor + dfsin.format(iRedstone) + " de redstone - " + ChatColor.BOLD + df.format(StatsRedstone) + "%");
					craftPlayer.sendMessage(lapisColor + dfsin.format(iLapis) + " de lapis - " + ChatColor.BOLD + df.format(StatsLapis) + "%");
					craftPlayer.sendMessage(diamondColor + dfsin.format(iDiamond) + " de diamante - " + ChatColor.BOLD + df.format(StatsDiamond) + "%");
					craftPlayer.sendMessage(ChatColor.GREEN + dfsin.format(iEmerald) + " de esmeralda");

					xrayData.save(f);

				}

			} else if (args.length == 2){

				if(args[1].equals("limpiar")){

					Player craftPlayer = (Player) sender;
					String playerName = args[0];

					File rayData = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
					File f = new File(rayData, File.separator + "rxray.yml");
					FileConfiguration xrayData = YamlConfiguration.loadConfiguration(f);

					xrayData.load(f);

					String iPlayer = xrayData.getString(playerName);

					if(iPlayer != null){

						xrayData.set(playerName, null);
						craftPlayer.sendMessage(ChatColor.RED + "Reiniciado deteccion xray de " + playerName);

					} else {

						craftPlayer.sendMessage(ChatColor.RED + "El jugador " + playerName + " aun no ha roto ningun mineral.");

					}

					xrayData.save(f);
				}				
			}
			return true;
		}		
		return false;
	}

	public static String comprobarColor (double stats){
		String color;
		if(stats >= 50 && stats < 75){
			color = ChatColor.YELLOW + "";
		}
		else if(stats >= 75 && stats < 90){
			color = ChatColor.RED + "";
		}
		else if(stats >= 90 && stats < 110){
			color = ChatColor.DARK_RED + "";
		}
		else if(stats >= 110){
			color = ChatColor.DARK_GRAY + "";
		}
		else {
			color = ChatColor.GREEN + "";
		}
		return color;
	}
}
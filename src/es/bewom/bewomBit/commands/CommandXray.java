package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import org.bukkit.Bukkit;
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
				
				File rayData = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
				File f = new File(rayData, File.separator + "rxray.yml");
				FileConfiguration xrayData = YamlConfiguration.loadConfiguration(f);
				
				if(args[0].equals("top")){
					
					
					
				} else {
					
					String playerName = args[0];
					
					xrayData.load(f);
					
					double iStone = xrayData.getDouble(playerName + ".Stone");
					double  iCoal = xrayData.getDouble(playerName + ".Coal_Ore");
					double  iIron = xrayData.getDouble(playerName + ".Iron_Ore");
					double  iGold = xrayData.getDouble(playerName + ".Gold_Ore");
					double  iRedstone = xrayData.getDouble(playerName + ".Redstone_Ore");
					double  iLapis = xrayData.getDouble(playerName + ".Lapis_Ore");
					double  iDiamond = xrayData.getDouble(playerName + ".Diamond_Ore");
					double  iEmerald = xrayData.getDouble(playerName + ".Emerald_Ore");
					
					double StatsCoal = ((iCoal/iStone)%3333)*10000;
					double StatsIron = ((iIron/iStone)%10000)*10000;
					double StatsGold = ((iGold/iStone)%76923)*10000;
					double StatsRedstone = ((iRedstone/iStone)%50000)*10000;
					double StatsLapis = ((iLapis/iStone)%100000)*10000;
					double StatsDiamond = ((iDiamond/iStone)%66666)*10000;				
					
					craftPlayer.sendMessage("Estos son los stats del jugador " + args[0]);				
					
					craftPlayer.sendMessage("Piedra: " + dfsin.format(iStone) + " ");
					craftPlayer.sendMessage("Carbon: " + dfsin.format(iCoal) + " " + df.format(StatsCoal) + "%");
					craftPlayer.sendMessage("Hierro: " + dfsin.format(iIron) + " " + df.format(StatsIron) + "%");
					craftPlayer.sendMessage("Oro: " + dfsin.format(iGold) + " " + df.format(StatsGold) + "%");
					craftPlayer.sendMessage("Redstone: " + dfsin.format(iRedstone) + " " + df.format(StatsRedstone) + "%");
					craftPlayer.sendMessage("Lapis: " + dfsin.format(iLapis) + " " + df.format(StatsLapis) + "%");
					craftPlayer.sendMessage("Diamante: " + dfsin.format(iDiamond) + " " + df.format(StatsDiamond) + "%");
					craftPlayer.sendMessage("Esmeralda: " + dfsin.format(iEmerald));
					
					xrayData.save(f);
					
				}
			
			}
			return true;
		}
		
		return false;
	}
}

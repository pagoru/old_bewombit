package es.bewom.bewomBit.groups.whitelist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CommandAutoWhitelist {
	
public static List<String> commandautowhitelist(CommandSender sender, Command cmd, String alias, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(cmd.getName().equalsIgnoreCase("whitelist")){
			
			File protecciondata1 = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
			File protecciondata = new File(protecciondata1, File.separator + "whitelist.yml");
			FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);
			
			proteccionData.load(protecciondata);
			
			if (args.length == 1){
				
				List<String> pList =  Arrays.asList("on","off","añadir","eliminar");  
				
				return pList;
			} else if(args.length == 2){
				
				if(args[0].equals("eliminar")){
					
					List<String> pListP = proteccionData.getStringList("miembros");
				
					return pListP;
				}
				
			}
			
			proteccionData.save(protecciondata);
			
		}
		return null;
	}
	
}

package es.bewom.bewomBit.commands.autocomplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandAutoAmigos {
	
public static List<String> commandautogm(CommandSender sender, Command cmd, String alias, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(cmd.getName().equalsIgnoreCase("amigos")){
			
			List<String> pList = null;
			
			final Player craftPlayer = ((OfflinePlayer) sender).getPlayer();
			String playerName = craftPlayer.getName();
			
			File amigosdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File f = new File(amigosdata, File.separator + "amigos.yml");
			FileConfiguration amigosData = YamlConfiguration.loadConfiguration(f);
			
			amigosData.load(f);
			
			if (args.length == 1){
					
				pList =  Arrays.asList("añadir","eliminar","aceptar","rechazar","solicitudes");  

			} else if (args.length == 2){
				
				if(args[0].equals("eliminar")){
					
					pList = amigosData.getStringList(playerName + ".amigos");
					
				} else if(args[0].equals("aceptar") || args[0].equals("rechazar")){
					
					pList = amigosData.getStringList(playerName + ".solicitudes");
					
				}
				
			}
			
			amigosData.save(f);
			
			return pList;
		}
		return null;
	}
	
}

package es.bewom.bewomBit.events;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import es.bewom.bewomBit.utility.DefaultMessages;

public class EventsWhitelist {

	public static void onPreJoin(AsyncPlayerPreLoginEvent eventPreConnect) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
//		String prePlayerIP = eventPreConnect.getAddress().getAddress().toString().substring(1);
		String prePlayerName = eventPreConnect.getName();
		
		File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File protecciondata = new File(protecciondata1, File.separator + "whitelist.yml");
		File protecciondataConfig = new File(protecciondata1, File.separator + "config.yml");
		FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);
		FileConfiguration proteccionData1 = YamlConfiguration.loadConfiguration(protecciondataConfig);
		
		proteccionData.load(protecciondata);
		proteccionData1.load(protecciondataConfig);
		
		boolean white = proteccionData1.getBoolean("Whitelist");
		
		if(white == true){
			
			List<String> pLista = proteccionData.getStringList("miembros");
			
			if(!pLista.contains(prePlayerName)){
				
				eventPreConnect.disallow(Result.KICK_BANNED , DefaultMessages.whitelistPlayer + "No estas en la whitelist del servidor!");
				
			}
			
		}
		
		
		proteccionData1.save(protecciondataConfig);
		proteccionData.save(protecciondata);
		
	}
	
}

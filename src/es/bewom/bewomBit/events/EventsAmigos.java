package es.bewom.bewomBit.events;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventsAmigos {
	
	public static void onJoin(PlayerJoinEvent eventConnect) throws SQLException, IOException, InvalidConfigurationException, ClassNotFoundException, ParseException {
		
		final Player craftPlayer = eventConnect.getPlayer();
		String playerName = craftPlayer.getName();
		
		File amigosdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File f = new File(amigosdata, File.separator + "amigos.yml");
		FileConfiguration amigosData = YamlConfiguration.loadConfiguration(f);
		
		amigosData.load(f);
		
		List<String> pLista = amigosData.getStringList(playerName + ".solicitudes");
		
		if(!pLista.isEmpty()){
			
			int numSol= 0;
			
			for (int i = 0; i < pLista.size(); i++) {
				numSol = numSol + 1;
			}
			
			
			if(numSol == 1){
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Tienes 1 solicitud de amistad pendiente" + ChatColor.GREEN + "" + ChatColor.ITALIC + " /amigos solicitudes.");	
				
			} else {
				
				craftPlayer.sendMessage(ChatColor.GRAY + "Tienes " + numSol + " solicitudes de amistad pendientes" + ChatColor.GREEN + "" + ChatColor.ITALIC + " /amigos solicitudes.");	
				
			}	
			
		}
		
		amigosData.save(f);
		
		
	}

}

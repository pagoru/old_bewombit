package es.bewom.bewomBit.groups.MOTD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.server.ServerListPingEvent;

public class EventsServerMOTD {
	public static void serverMOTDEvents (ServerListPingEvent pingEvent) throws FileNotFoundException, IOException, InvalidConfigurationException{
		
		//53 por linea
		
		pingEvent.setMaxPlayers(0);
		
		int port = Bukkit.getServer().getPort();
				
		try {
			pingEvent.setServerIcon(Bukkit.loadServerIcon(ImageIO.read(new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(),
					File.separator + "bewom.png"))));
	    } catch (Exception e1) {
	            e1.printStackTrace();
	    }
		
		File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File protecciondataConfig = new File(protecciondata1, File.separator + "config.yml");
		FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondataConfig);
		
		proteccionData.load(protecciondataConfig);
		
		boolean white = proteccionData.getBoolean("Whitelist");
		
		if(white){
				
			String motd = ChatColor.WHITE + "" + ChatColor.BOLD + "             «« " + ChatColor.DARK_RED + ChatColor.BOLD + " bewom.es " + ChatColor.WHITE + "" + ChatColor.BOLD + " »»";
			String motd1 = ChatColor.DARK_RED + "                                               mantenimiento";
				pingEvent.setMotd(motd + motd1);
			
		} else {
			
			if(port == 1700){
				
				String motd = ChatColor.WHITE + "" + ChatColor.BOLD + "             «« " + ChatColor.DARK_AQUA + ChatColor.BOLD + " bewom.es/surv " + ChatColor.WHITE + "" + ChatColor.BOLD + " »»";
				String motd1 = ChatColor.WHITE + "                                        bona, magna, eget";
				pingEvent.setMotd(motd + motd1);
				
			} else if(port == 1600){
				
				String motd = ChatColor.WHITE + "" + ChatColor.BOLD + "             «« " + ChatColor.DARK_AQUA + ChatColor.BOLD + " bewom.es/pixel " + ChatColor.WHITE + "" + ChatColor.BOLD + " »»";
				String motd1 = ChatColor.WHITE + "                                        bona, magna, eget";
				pingEvent.setMotd(motd + motd1);
				
			} else {
				
				String motd = ChatColor.WHITE + "" + ChatColor.BOLD + "             «« " + ChatColor.DARK_AQUA + ChatColor.BOLD + " bewom.es " + ChatColor.WHITE + "" + ChatColor.BOLD + " »»";
				String motd1 = ChatColor.WHITE + "                                             bona, magna, eget";
				pingEvent.setMotd(motd + motd1);
				
			}
			
		}
		
		proteccionData.save(protecciondataConfig);
		
	}
}

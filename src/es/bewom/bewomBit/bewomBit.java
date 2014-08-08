package es.bewom.bewomBit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import es.bewom.bewomBit.events.brokeBlockPlayer;
import es.bewom.bewomBit.events.chatPlayer;
import es.bewom.bewomBit.events.connectPlayer;
import es.bewom.bewomBit.events.deathPlayer;
import es.bewom.bewomBit.events.interactPlayer;
import es.bewom.bewomBit.events.movePlayer;
import es.bewom.bewomBit.events.placeBlockPlayer;
import es.bewom.bewomBit.events.serverMOTD;
public class bewomBit extends JavaPlugin implements Listener, CommandExecutor {
	
	Logger log = Logger.getLogger("Minecraft");

	public void onEnable(){
		
		log.info(ChatColor.AQUA + "Bit habilitado");
		getServer().getPluginManager().registerEvents(new movePlayer (this), this);
		getServer().getPluginManager().registerEvents(new connectPlayer(), this);
		getServer().getPluginManager().registerEvents(new chatPlayer(), this);
		getServer().getPluginManager().registerEvents(new serverMOTD(), this);
		getServer().getPluginManager().registerEvents(new placeBlockPlayer(), this);
		getServer().getPluginManager().registerEvents(new interactPlayer(), this);
		getServer().getPluginManager().registerEvents(new deathPlayer(), this);
		getServer().getPluginManager().registerEvents(new brokeBlockPlayer(), this);
		
		
		
		getFile();
		
		// ---> Comandos <--- //
		
		getCommand("say").setExecutor(new commandPlayer());
		getCommand("fly").setExecutor(new commandPlayer());
		getCommand("tphere").setExecutor(new commandPlayer());
		getCommand("clear").setExecutor(new commandPlayer());
		getCommand("hat").setExecutor(new commandPlayer());
		getCommand("inv").setExecutor(new commandPlayer());
		getCommand("end").setExecutor(new commandPlayer());
		getCommand("ender").setExecutor(new commandPlayer());
		getCommand("enderchest").setExecutor(new commandPlayer());
		getCommand("kill").setExecutor(new commandPlayer());
		getCommand("suicide").setExecutor(new commandPlayer());
		getCommand("heal").setExecutor(new commandPlayer());
		getCommand("seen").setExecutor(new commandPlayer());
		getCommand("kick").setExecutor(new commandPlayer());
		getCommand("mp").setExecutor(new commandPlayer());
		getCommand("msg").setExecutor(new commandPlayer());
		getCommand("me").setExecutor(new commandPlayer());
		getCommand("tell").setExecutor(new commandPlayer());
		getCommand("congelar").setExecutor(new commandPlayer());
		getCommand("cofre").setExecutor(new commandPlayer());
		getCommand("gm").setExecutor(new commandPlayer());
				
		// ---> config inicial <--- //
		
		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "config.yml");
		File dataChest = new File(data1, File.separator + "cofres.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
		
		try {
			data1.mkdir();
			data.createNewFile();
			dataChest.createNewFile();
		} catch (IOException e) {
		  
			e.printStackTrace();
		}
		
		try {
			try {
				try {
					
					Data.load(data);
					
					Data.set("Congelado", false);
					
					Data.save(data);
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
					e.printStackTrace();
			}
			
		} catch (InvalidConfigurationException e) {
				e.printStackTrace();
		}
	}

	public void onDisable(){
		
		log.info(ChatColor.AQUA + "Bit deshabilitado");	
	}
	
//  Util quiza mas adelante	
	
	public bewomBit(){
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}

}

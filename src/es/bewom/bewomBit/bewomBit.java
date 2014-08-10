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

import es.bewom.bewomBit.commands.utility.autoCompleteTab;
import es.bewom.bewomBit.commands.utility.commandPlayer;
import es.bewom.bewomBit.events.utilitiy.brokeBlockEvent;
import es.bewom.bewomBit.events.utilitiy.chatEvent;
import es.bewom.bewomBit.events.utilitiy.connectEvent;
import es.bewom.bewomBit.events.utilitiy.deathEvent;
import es.bewom.bewomBit.events.utilitiy.interactEvent;
import es.bewom.bewomBit.events.utilitiy.moveEvent;
import es.bewom.bewomBit.events.utilitiy.placeBlockEvent;
import es.bewom.bewomBit.events.utilitiy.preprocessCommandEvent;
import es.bewom.bewomBit.events.utilitiy.serverMotdEvent;
public class bewomBit extends JavaPlugin implements Listener, CommandExecutor {
	
	Logger log = Logger.getLogger("Minecraft");

	public void onEnable(){
		
		getServer().getPluginManager().registerEvents(new moveEvent (this), this);
		getServer().getPluginManager().registerEvents(new connectEvent(), this);
		getServer().getPluginManager().registerEvents(new chatEvent(), this);
		getServer().getPluginManager().registerEvents(new serverMotdEvent(), this);
		getServer().getPluginManager().registerEvents(new placeBlockEvent(), this);
		getServer().getPluginManager().registerEvents(new interactEvent(), this);
		getServer().getPluginManager().registerEvents(new deathEvent(), this);
		getServer().getPluginManager().registerEvents(new brokeBlockEvent(), this);
		getServer().getPluginManager().registerEvents(new preprocessCommandEvent(), this);
		
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
		getCommand("p").setExecutor(new commandPlayer());
		getCommand("gm").setExecutor(new commandPlayer());
		getCommand("v").setExecutor(new commandPlayer());
		getCommand("tpa").setExecutor(new commandPlayer());
		getCommand("tpahere").setExecutor(new commandPlayer());
		
		// ---> Comandos auto-completar <--- //
		
		getCommand("p").setTabCompleter(new autoCompleteTab());
		getCommand("gm").setTabCompleter(new autoCompleteTab());
		getCommand("v").setTabCompleter(new autoCompleteTab());
		getCommand("tpa").setTabCompleter(new autoCompleteTab());
		getCommand("tpahere").setTabCompleter(new autoCompleteTab());
				
		// ---> config inicial <--- //
		
		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "config.yml");
		File dataProteccion = new File(data1, File.separator + "proteccion.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
		
		try {
			data1.mkdir();
			data.createNewFile();
			dataProteccion.createNewFile();
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

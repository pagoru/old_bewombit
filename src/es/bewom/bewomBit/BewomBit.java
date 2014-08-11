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

import es.bewom.bewomBit.commands.utility.AutoCompleteTab;
import es.bewom.bewomBit.commands.utility.CommandPlayer;
import es.bewom.bewomBit.events.utilitiy.BrokeBlockEvent;
import es.bewom.bewomBit.events.utilitiy.ChatEvent;
import es.bewom.bewomBit.events.utilitiy.JoinEvent;
import es.bewom.bewomBit.events.utilitiy.DeathEvent;
import es.bewom.bewomBit.events.utilitiy.InteractEvent;
import es.bewom.bewomBit.events.utilitiy.MoveEvent;
import es.bewom.bewomBit.events.utilitiy.PlaceBlockEvent;
import es.bewom.bewomBit.events.utilitiy.PreprocessCommandEvent;
import es.bewom.bewomBit.events.utilitiy.QuitEvent;
import es.bewom.bewomBit.events.utilitiy.ServerMotdEvent;
public class BewomBit extends JavaPlugin implements Listener, CommandExecutor {
	
	Logger log = Logger.getLogger("Minecraft");
	
	public static BewomBit main;

	public void onEnable(){
		
		main = this;
		
		getServer().getPluginManager().registerEvents(new MoveEvent(this), this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		getServer().getPluginManager().registerEvents(new ChatEvent(), this);
		getServer().getPluginManager().registerEvents(new ServerMotdEvent(), this);
		getServer().getPluginManager().registerEvents(new PlaceBlockEvent(), this);
		getServer().getPluginManager().registerEvents(new InteractEvent(), this);
		getServer().getPluginManager().registerEvents(new DeathEvent(), this);
		getServer().getPluginManager().registerEvents(new BrokeBlockEvent(), this);
		getServer().getPluginManager().registerEvents(new PreprocessCommandEvent(), this);
		getServer().getPluginManager().registerEvents(new QuitEvent(), this);
		
		// ---> Comandos <--- //
		
		getCommand("say").setExecutor(new CommandPlayer());
		getCommand("fly").setExecutor(new CommandPlayer());
		getCommand("tphere").setExecutor(new CommandPlayer());
		getCommand("clear").setExecutor(new CommandPlayer());
		getCommand("hat").setExecutor(new CommandPlayer());
		getCommand("inv").setExecutor(new CommandPlayer());
		getCommand("end").setExecutor(new CommandPlayer());
		getCommand("ender").setExecutor(new CommandPlayer());
		getCommand("enderchest").setExecutor(new CommandPlayer());
		getCommand("kill").setExecutor(new CommandPlayer());
		getCommand("suicide").setExecutor(new CommandPlayer());
		getCommand("heal").setExecutor(new CommandPlayer());
		getCommand("seen").setExecutor(new CommandPlayer());
		getCommand("kick").setExecutor(new CommandPlayer());
		getCommand("mp").setExecutor(new CommandPlayer());
		getCommand("msg").setExecutor(new CommandPlayer());
		getCommand("me").setExecutor(new CommandPlayer());
		getCommand("tell").setExecutor(new CommandPlayer());
		getCommand("congelar").setExecutor(new CommandPlayer());
		getCommand("p").setExecutor(new CommandPlayer());
		getCommand("gm").setExecutor(new CommandPlayer());
		getCommand("v").setExecutor(new CommandPlayer());
		getCommand("tpa").setExecutor(new CommandPlayer());
		getCommand("tpahere").setExecutor(new CommandPlayer());
		getCommand("spawner").setExecutor(new CommandPlayer());
		
		// ---> Comandos auto-completar <--- //
		
		getCommand("p").setTabCompleter(new AutoCompleteTab());
		getCommand("gm").setTabCompleter(new AutoCompleteTab());
		getCommand("v").setTabCompleter(new AutoCompleteTab());
		getCommand("tpa").setTabCompleter(new AutoCompleteTab());
		getCommand("tpahere").setTabCompleter(new AutoCompleteTab());
		getCommand("spawner").setTabCompleter(new AutoCompleteTab());
				
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
	
	public BewomBit(){
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
}

package es.bewom.bewomBit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
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
import es.bewom.bewomBit.events.utilitiy.DamageEntityEvent;
import es.bewom.bewomBit.events.utilitiy.ExplodeEvent;
import es.bewom.bewomBit.events.utilitiy.JoinEvent;
import es.bewom.bewomBit.events.utilitiy.DeathEvent;
import es.bewom.bewomBit.events.utilitiy.InteractEvent;
import es.bewom.bewomBit.events.utilitiy.MoveEvent;
import es.bewom.bewomBit.events.utilitiy.PlaceBlockEvent;
import es.bewom.bewomBit.events.utilitiy.PreLoginEvent;
import es.bewom.bewomBit.events.utilitiy.PreprocessCommandEvent;
import es.bewom.bewomBit.events.utilitiy.QuitEvent;
import es.bewom.bewomBit.events.utilitiy.ServerMotdEvent;
import es.bewom.bewomBit.utility.Lag;
public class BewomBit extends JavaPlugin implements Listener, CommandExecutor {
	
	Logger log = Logger.getLogger("Minecraft");
	
	public static BewomBit main;
	
	@SuppressWarnings("unused")
	private static int task1;
	
	public static String SQLUrl = "localhost";
	public static String SQLPort = "3306";
	public static String SQLbd = "bewomBit";
	public static String SQLUser = "bewomBit";
	public static String SQLPass = "9fff69cdd1a245760e2309d4b1147028";

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
		getServer().getPluginManager().registerEvents(new DamageEntityEvent(), this);
		getServer().getPluginManager().registerEvents(new QuitEvent(), this);
		getServer().getPluginManager().registerEvents(new PreLoginEvent(), this);
		getServer().getPluginManager().registerEvents(new ExplodeEvent(), this);
		
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
		getCommand("sethome").setExecutor(new CommandPlayer());
		getCommand("home").setExecutor(new CommandPlayer());
		getCommand("delhome").setExecutor(new CommandPlayer());
		getCommand("spawn").setExecutor(new CommandPlayer());
		getCommand("god").setExecutor(new CommandPlayer());
		getCommand("lag").setExecutor(new CommandPlayer());
		getCommand("tpall").setExecutor(new CommandPlayer());
		//getCommand("speed").setExecutor(new CommandPlayer());
		getCommand("ban").setExecutor(new CommandPlayer());
		getCommand("unban").setExecutor(new CommandPlayer());
		getCommand("save").setExecutor(new CommandPlayer());
		getCommand("xray").setExecutor(new CommandPlayer());
		getCommand("amigos").setExecutor(new CommandPlayer());
		getCommand("teleport").setExecutor(new CommandPlayer());
		getCommand("whitelist").setExecutor(new CommandPlayer());
		
		// ---> Comandos auto-completar <--- //
		
		getCommand("p").setTabCompleter(new AutoCompleteTab());
		getCommand("gm").setTabCompleter(new AutoCompleteTab());
		getCommand("v").setTabCompleter(new AutoCompleteTab());
		getCommand("tpa").setTabCompleter(new AutoCompleteTab());
		getCommand("tpahere").setTabCompleter(new AutoCompleteTab());
		getCommand("spawner").setTabCompleter(new AutoCompleteTab());
		getCommand("home").setTabCompleter(new AutoCompleteTab());
		getCommand("delhome").setTabCompleter(new AutoCompleteTab());
		getCommand("spawn").setTabCompleter(new AutoCompleteTab());
		getCommand("lag").setTabCompleter(new AutoCompleteTab());
		getCommand("tpall").setTabCompleter(new AutoCompleteTab());
		getCommand("ban").setTabCompleter(new AutoCompleteTab());
		getCommand("save").setTabCompleter(new AutoCompleteTab());
		getCommand("xray").setTabCompleter(new AutoCompleteTab());
		getCommand("amigos").setTabCompleter(new AutoCompleteTab());
		getCommand("whitelist").setTabCompleter(new AutoCompleteTab());
		
		// ---> Temporizadores
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
						
		// ---> Guardado de mundos
		
		task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(BewomBit.main, new Runnable() {
	        
			@Override
	        public void run() {
				
				java.util.List<World> listWorlds = Bukkit.getServer().getWorlds();
				
				for (World w : listWorlds){
					
					w.save();
					log.info(w.getName() + " guardado.");
					
				}

			}
			
		}, 0, 18000);
		
		// ---> config inicial <--- //
		
		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "config.yml");
		File dataProteccion = new File(data1, File.separator + "proteccion.yml");
		File dataRxray = new File(data1, File.separator + "rxray.yml");
		File dataAmigos = new File(data1, File.separator + "amigos.yml");
		File dataTeleport = new File(data1, File.separator + "teleport.yml");
		File dataWhitelist = new File(data1, File.separator + "whitelist.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);
		
		try {
			
			data1.mkdir();
			data.createNewFile();
			dataProteccion.createNewFile();
			dataRxray.createNewFile();
			dataAmigos.createNewFile();
			dataTeleport.createNewFile();
			dataWhitelist.createNewFile();
			
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
package es.bewom.bewomBit;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
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
	
	private static boolean congelar = false;
	
	public static boolean isCongelar() {
		return congelar;
	}

	public static void setCongelar(boolean congelar) {
		bewomBit.congelar = congelar;
	}

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
				
		// ---> Scoreboard teams inicial <--- //
		
		
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

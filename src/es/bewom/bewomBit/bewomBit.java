package es.bewom.bewomBit;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


public class bewomBit extends JavaPlugin implements Listener, CommandExecutor {
	
	Logger log = Logger.getLogger("Minecraft");
	
	private static Scoreboard board;
	static Team teamAdmin;
	static Team teamMod;
	static Team teamVip;
	
	public void onEnable(){
		
		log.info(ChatColor.AQUA + "Bit habilitado");
		
		getServer().getPluginManager().registerEvents(new connectPlayer(), this); //class connectPlayer.java
		getServer().getPluginManager().registerEvents(new chatPlayer(), this);
		
		
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
		getCommand("cd").setExecutor(new commandPlayer());
				
		// ---> Scoreboard teams inicial <--- //
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		
		String bewomAdmin = "bewom_Admin";
		String bewomMod = "bewom_Mod";
		String bewomVip = "bewom_Vip";
		
		teamAdmin = board.getTeam(bewomAdmin);
		teamMod = board.getTeam(bewomMod);
		teamVip = board.getTeam(bewomVip);
		
		if (teamAdmin == null) {
			teamAdmin = board.registerNewTeam(bewomAdmin);
			teamAdmin.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "");
			teamAdmin.setDisplayName(bewomAdmin);
		} else {
			teamAdmin.unregister();
			
			teamAdmin = board.registerNewTeam(bewomAdmin);
			teamAdmin.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "");
			teamAdmin.setDisplayName(bewomAdmin);
		}
		
		if (teamMod == null) {
			teamMod = board.registerNewTeam(bewomMod);
			teamMod.setPrefix(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "");
			teamMod.setDisplayName(bewomMod);
		} else {
			teamMod.unregister();
			
			teamMod = board.registerNewTeam(bewomMod);
			teamMod.setPrefix(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "");
			teamMod.setDisplayName(bewomMod);
		}
		
		if (teamVip == null) {
			teamVip = board.registerNewTeam(bewomVip);
			teamVip.setPrefix(ChatColor.DARK_AQUA + "");
			teamVip.setDisplayName(bewomVip);
		} else {
			teamVip.unregister();
			
			teamVip = board.registerNewTeam(bewomVip);
			teamVip.setPrefix(ChatColor.DARK_AQUA + "");
			teamVip.setDisplayName(bewomVip);
		}
	}

	public void onDisable(){
		
		log.info(ChatColor.AQUA + "Bit deshabilitado");
		
	}
	
}

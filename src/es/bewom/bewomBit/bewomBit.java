package es.bewom.bewomBit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


public class bewomBit extends JavaPlugin {
	//SAMATAOPACO
	private static Scoreboard board;
	static Team teamAdmin;
	static Team teamMod;
	static Team teamVip;
	
	public void onEnable(){
		getLogger().info("Plugin habilitado.");
		getServer().getPluginManager().registerEvents(new connectPlayer(), this); //class connectPlayer.java
		getServer().getPluginManager().registerEvents(new playerChat(), this);
		
		//Scoreboard teams inicial
		
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
		getLogger().info("Plugin deshabilitado.");
		
	}
	
}

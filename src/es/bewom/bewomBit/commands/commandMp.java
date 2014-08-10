
package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class commandMp {
	
	static Logger log = Logger.getLogger("Minecraft");
	private static String getPlayerChat;
	
	@SuppressWarnings({ "deprecation"})
	public static boolean commandmp(CommandSender sender, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("mp") || label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("tell") || label.equalsIgnoreCase("me")){
		
			Player craftPlayer = (Player) sender;
			String playerName = sender.getName();
			String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player
			
			String admin = ChatColor.DARK_RED + "/" + ChatColor.DARK_RED + "" + ChatColor.BOLD + playerName;
			String mod = ChatColor.DARK_GREEN + "/" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + playerName;
			String vip = ChatColor.DARK_AQUA + "/" + playerName;
			String steve = "/" + playerName;
			
			String mpText = ChatColor.GRAY + "/mp";
			
			File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
			File f = new File(userdata, File.separator + playerUUID + ".yml");
			FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
			  
		  
			try {
				try {
					try {
						playerData.load(f);
						
						// Información que cargar/guardar para el jugador
						
						getPlayerChat = playerData.getString("Chat");
						
						
						if (args.length == 0){
							
							if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod") || sender.hasPermission("bewom.vip")) {
							
								if(getPlayerChat.equals("global")){
									
									craftPlayer.sendMessage(ChatColor.RED + "No puedes salir del chat general, usa /mp [nick] y/o (mensaje).");
									
								} else {
									
									playerData.set("Chat", "global");
									
									craftPlayer.sendMessage(ChatColor.GRAY + "Has salido del chat privado de " + getPlayerChat + ".");
									
								}
							
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + "Solo los Vips pueden entrar en salas privadas, usa /mp [nick] [mensaje].");
								
							}
							
						} else if (args.length == 1) {
							
							if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod") || sender.hasPermission("bewom.vip")) {
							
								if (sender.getServer().getPlayer(args[0]) != null){
									
									craftPlayer.sendMessage(ChatColor.GRAY + "Has entrado en el chat privado de " + args[0] + ".");
									
									playerData.set("Chat", args[0]);
									
								} else {
									craftPlayer.sendMessage(ChatColor.RED + "El jugador no esta conectado.");
									return true;
								}
							
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + "Solo los Vips pueden entrar en salas privadas, usa /mp [nick] [mensaje].");
								
							}
							
						} else {
							
							if (sender.getServer().getPlayer(args[0]) != null){
								
								String texto = "";
								for (int i = 1; i < args.length; i++) {
									texto += args[i] + " ";
								}
								
								if (craftPlayer.hasPermission("bewom.admin")) {	
									
									log.info("/mp/" + playerName + "/to/" + args[0] + " < " +  texto);
									
									Bukkit.getServer().getPlayer(playerName).sendMessage(admin + mpText + "/" + args[0] + " < " + texto);
									Bukkit.getServer().getPlayer(args[0]).sendMessage(admin + mpText + " < " + texto);
									
								} else if (craftPlayer.hasPermission("bewom.mod")) {
									
									log.info("/mp/" + playerName + "/to/" + args[0] + " < " +  texto);
									
									Bukkit.getServer().getPlayer(playerName).sendMessage(mod + mpText + "/" + args[0] + " < " + texto);
									Bukkit.getServer().getPlayer(args[0]).sendMessage(mod + mpText + " < " + texto);
									
								} else if (craftPlayer.hasPermission("bewom.vip")) {
									
									log.info("/mp/" + playerName + "/to/" + args[0] + " < " +  texto);
									
									Bukkit.getServer().getPlayer(playerName).sendMessage(vip + mpText + "/" + args[0] + " < " + texto);
									Bukkit.getServer().getPlayer(args[0]).sendMessage(vip + mpText + " < " + texto);
									
								} else {
									
									log.info("/mp/" + playerName + "/to/" + args[0] + " < " +  texto);
									
									Bukkit.getServer().getPlayer(playerName).sendMessage(steve + mpText + "/" + args[0] + " < " + texto);
									Bukkit.getServer().getPlayer(args[0]).sendMessage(steve + mpText + " < " + texto);
									
								}
								
							}
							
						}
						
						
						playerData.save(f);
						return true;
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
				} catch (IOException e) {
						e.printStackTrace();
				}
				
			} catch (InvalidConfigurationException e) {
					e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
}

package es.bewom.bewomBit.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class eventsChatAntiSpam {
	
	static Logger log = Logger.getLogger("Minecraft");
	private static String getPlayerChat;
	
	@SuppressWarnings("deprecation")
	public static void onPlayerChatEventsAntiSpam(AsyncPlayerChatEvent eventChat) {

		String message = eventChat.getMessage();
		Player craftPlayer = eventChat.getPlayer(); //craftPlayer Player
		String playerName = eventChat.getPlayer().getName(); //limpio String 
		String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player

		String admin = ChatColor.DARK_RED + "/" + ChatColor.DARK_RED + "" + ChatColor.BOLD + playerName;
		String mod = ChatColor.DARK_GREEN + "/" + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + playerName;
		String vip =  ChatColor.DARK_AQUA + "/" + playerName;
		String steve = "/" + playerName;

		String adminModText = ChatColor.RESET + " < " + ChatColor.WHITE  + "" + ChatColor.BOLD;
		String vipDefaultText = ChatColor.WHITE + " < ";

		String mpText = ChatColor.GRAY + "/mp";

		//mp
		
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
		File f = new File(userdata, File.separator + playerUUID + ".yml");
		FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);


		try {
			try {
				try {
					playerData.load(f);

					// Información que cargar/guardar para el jugador

					// ---> Chat <--- //

					getPlayerChat = playerData.getString("Chat");

					if(getPlayerChat.equals("global")){

						if (craftPlayer.hasPermission("bewom.admin")) {			
							eventChat.setFormat(admin + adminModText + message);

						} else if (craftPlayer.hasPermission("bewom.mod")) {
							eventChat.setFormat(mod + adminModText + message);

						} else if (craftPlayer.hasPermission("bewom.vip")) {
							eventChat.setFormat(vip + vipDefaultText + corregir(message));

						} else {
							eventChat.setFormat(steve + vipDefaultText + corregir(message));

						}

					} else {

						if (craftPlayer.hasPermission("bewom.admin")) {	

							Bukkit.getServer().getPlayer(playerName).sendMessage(admin + mpText + "/" + getPlayerChat + " < " + corregir(message));

							Bukkit.getServer().getPlayer(getPlayerChat).sendMessage(admin + mpText + " < " + corregir(message));

							log.info("/mp/" + playerName + "/to/" + getPlayerChat + " < " + corregir(message));

							eventChat.setCancelled(true);

						} else if (craftPlayer.hasPermission("bewom.mod")) {	

							Bukkit.getServer().getPlayer(playerName).sendMessage(mod + mpText + "/" + getPlayerChat + " < " + corregir(message));

							Bukkit.getServer().getPlayer(getPlayerChat).sendMessage(mod + mpText + " < " + corregir(message));

							log.info("/mp/" + playerName + "/to/" + getPlayerChat + " < " + corregir(message));

							eventChat.setCancelled(true);

						} else if (craftPlayer.hasPermission("bewom.vip")) {	

							Bukkit.getServer().getPlayer(playerName).sendMessage(vip + mpText + "/" + getPlayerChat + " < " + corregir(message));

							Bukkit.getServer().getPlayer(getPlayerChat).sendMessage(vip + mpText + " < " + corregir(message));

							log.info("/mp/" + playerName + "/to/" + getPlayerChat + " < " + corregir(message));

							eventChat.setCancelled(true);

						} else {	

							Bukkit.getServer().getPlayer(playerName).sendMessage(steve + mpText + "/" + getPlayerChat + " < " + corregir(message));

							Bukkit.getServer().getPlayer(getPlayerChat).sendMessage(steve + mpText + " < " + corregir(message));

							log.info("/mp/" + playerName + "/to/" + getPlayerChat + " < " + corregir(message));

							eventChat.setCancelled(true);

						}

					}
					
					String lastMessage = playerData.getString("LastMessage");
					
					if(lastMessage.equals(message)){
						eventChat.setCancelled(true);
					}
					
					playerData.set("LastMessage", message);
					
					playerData.save(f);

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
	private static String corregir (String message) {
		if (message.length() >= 5){
			message = message.substring(0, 1).toUpperCase() + message.substring(1);
			char m = message.charAt(message.length()-1);
			if (!(m == '.' || m == ','|| m == '?' || m == '¿'  || m == '!' || m == '¡' || m == ':' || m == ';')){
				message = message + '.';
			}
		}
		return message;
	}
	
}

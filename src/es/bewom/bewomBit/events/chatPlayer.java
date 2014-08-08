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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatPlayer implements Listener {

	static Logger log = Logger.getLogger("Minecraft");
	private static String getPlayerChat;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent eventChat) {

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
		
		boolean playerIsCongelado = false;
		
		boolean isCongelado = false;
		
		File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
		File data = new File(data1, File.separator + "config.yml");
		FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

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

							Bukkit.getServer().getPlayer(playerName).sendMessage(admin + mpText + "/" + getPlayerChat + " < " + message);

							Bukkit.getServer().getPlayer(getPlayerChat).sendMessage(admin + mpText + " < " + message);

							log.info("/mp/" + playerName + "/to/" + getPlayerChat + " < " + message);

							eventChat.setCancelled(true);

						}

					}
					
					String lastMessage = playerData.getString("LastMessage");
					
					if(lastMessage.equals(message)){
						eventChat.setCancelled(true);
					}
					
					playerData.set("LastMessage", message);
					
					playerIsCongelado = playerData.getBoolean("Congelado");
					
					playerData.save(f);
					
					
					Data.load(data);
					
					isCongelado = Data.getBoolean("Congelado");
					
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
		
		if (!craftPlayer.hasPermission("bewom.admin") || !craftPlayer.hasPermission("bewom.mod")){
			if (playerIsCongelado || isCongelado){
	
				eventChat.setCancelled(true);
				craftPlayer.sendMessage(ChatColor.RED + "Has sido congelado temporalmente.");
			}
		}
		
	}
	private String corregir (String message) {
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

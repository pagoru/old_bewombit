
package es.bewom.bewomBit.groups.chat.mp;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.utility.commands.CommandUtilities;

public class CommandMp {
	
	static Logger log = Logger.getLogger("Minecraft");
	private static String getPlayerChat;

	public static boolean commandmp(CommandSender sender, Command cmd, String label, String[] args) throws Exception{
		
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

			playerData.load(f);
			
			// Informaci�n que cargar/guardar para el jugador
			
			getPlayerChat = playerData.getString("Chat");
			
				if (args.length == 0){
	
					if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod") || sender.hasPermission("bewom.vip")) {
					
						if(getPlayerChat.equals("global")){
							
							craftPlayer.sendMessage(ChatColor.RED + "No puedes salir del chat general, usa /mp [nick] y/o (mensaje).");
						}
						else {
							
							playerData.set("Chat", "global");
							craftPlayer.sendMessage(ChatColor.GRAY + "Has salido del chat privado de " + getPlayerChat + ".");
						}
					
					}
					else {
						
						craftPlayer.sendMessage(ChatColor.RED + "Solo los Vips pueden entrar en salas privadas, usa /mp [nick] [mensaje].");
					}
					
				}
				else if (args.length == 1) {
					
					if(!args[0].equals(playerName)){
					
						if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod") || sender.hasPermission("bewom.vip")) {
						
							if (CommandUtilities.comprobarJugador(sender, args [0])){
								
								craftPlayer.sendMessage(ChatColor.GRAY + "Has entrado en el chat privado de " + args[0] + ".");
								playerData.set("Chat", args[0]);
								
							}
							else {
								CommandUtilities.jugadorDesconectado(sender);
								return true;
							}
						}
						else {
							
							craftPlayer.sendMessage(ChatColor.RED + "Solo los Vips pueden entrar en salas privadas, usa /mp [nick] [mensaje].");
						}
					
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + "No puedes enviarte a ti mismo mp's.");
						
					}
				}
				else {
					
					if(!args[0].equals(playerName)){
					
						if (CommandUtilities.comprobarJugador(sender, args [0])){
							
							String texto = "";
							for (int i = 1; i < args.length; i++) {
								texto += args[i] + " ";
							}
							
							if (craftPlayer.hasPermission("bewom.admin")) {	
								
								formatearChatMp (craftPlayer, playerName, admin, mpText, args, texto);
								
							}
							else if (craftPlayer.hasPermission("bewom.mod")) {
								
								formatearChatMp (craftPlayer, playerName, mod, mpText, args, texto);
								
							}
							else if (craftPlayer.hasPermission("bewom.vip")) {
								
								formatearChatMp (craftPlayer, playerName, vip, mpText, args, texto);
								
							}
							else {
			
								formatearChatMp (craftPlayer, playerName, steve, mpText, args, texto);
							}
						}
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + "No puedes enviarte a ti mismo mp's.");
						
					}
				}			
			
			
			
			playerData.save(f);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static void formatearChatMp (Player player, String playerName, String permiso, String mpText, String [] args, String texto){
	
		log.info("/mp/" + playerName + "/to/" + args[0] + " < " +  texto);
		
		Bukkit.getServer().getPlayer(playerName).sendMessage(permiso + mpText + "/" + args[0] + " < " + texto);
		Bukkit.getServer().getPlayer(args[0]).sendMessage(permiso + mpText + " < " + texto);
	}
}
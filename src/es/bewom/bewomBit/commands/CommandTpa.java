package es.bewom.bewomBit.commands;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandTpa implements Listener {

	@SuppressWarnings("deprecation")
	public static boolean commandtpa(CommandSender sender, Command cmd, String label, String[] args) throws Exception{
		
		if (label.equalsIgnoreCase("tpa")){
			
			Player craftPlayer = (Player) sender;
			String playerName = craftPlayer.getName();
			UUID playerUUID = craftPlayer.getUniqueId();
			
			Player craftPlayerArgs = null;
			String playerUUIDArgs = null; //UUID Player
						
			if (args.length == 1){

				if (CommandUtilities.comprobarJugador(sender, args [0])){

					craftPlayerArgs = Bukkit.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId());
					playerUUIDArgs = craftPlayerArgs.getUniqueId().toString();
					
					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUIDArgs + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
					playerData.load(f);
					
					playerData.set("Tpa", playerName);
					
					craftPlayerArgs.sendMessage(ChatColor.GRAY + "El usuario " + playerName + " quiere que vengas donde esta el.");
					craftPlayerArgs.sendMessage(ChatColor.GRAY + "Si aceptas, escribe en el chat " + ChatColor.RED + "/tpa aceptar" + ChatColor.GRAY + ".");
					craftPlayerArgs.sendMessage(ChatColor.GRAY + "Si no quieres, escribe en el chat " + ChatColor.RED + "/tpa denegar" + ChatColor.GRAY + ".");
					
					playerData.save(f);
					
				}
				else if (args[0].equals("aceptar")){

					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUID + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
					playerData.load(f);
					
					String playerNameTpa = playerData.getString("Tpa");
					
					if(playerNameTpa != null){
						if (CommandUtilities.comprobarJugador(sender, playerNameTpa)){

							Player playerCraftTpa = craftPlayer.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(playerNameTpa).getUniqueId());
							
							craftPlayer.teleport(playerCraftTpa);
							
							craftPlayer.sendMessage(ChatColor.GRAY + "Te has teletransportado con exito a " + playerNameTpa + ".");
							
							playerData.set("Tpa", null);
							
						} else {

							CommandUtilities.jugadorDesconectado(sender);

							playerData.set("Tpa", null);

						}
						
					}  else {
						
						craftPlayer.sendMessage(ChatColor.RED + "No puedes aceptar un tpa de 'nadie'.");
						
					}
					playerData.save(f);
										
				} else if (args[0].equals("denegar")){
					
					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUID + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

					playerData.load(f);
					
					String playerNameTpa = playerData.getString("Tpa");
					
					if(playerNameTpa != null){

						if (CommandUtilities.comprobarJugador(sender, playerNameTpa)){

							craftPlayer.sendMessage(ChatColor.RED + "Has denegado el tpa.");
							
							playerData.set("Tpa", null);
						}
						else {
							CommandUtilities.jugadorDesconectado(sender);
							playerData.set("Tpa", null);

						}
					}
					else {

						craftPlayer.sendMessage(ChatColor.RED + "No puedes denegar un tpa de 'nadie'.");
						
					}
					playerData.save(f);
					
				}
				else {
					CommandUtilities.formaCorrecta(sender, "/tpa <playerOnline/aceptar/denegar>");
				}
			}
			
			return true;		
		}
		
		return false;
	}

}
package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandTpaHere implements Listener {

	@SuppressWarnings({ "deprecation" })
	public static boolean commandtpahere(CommandSender sender, Command cmd, String label, String[] args) throws IOException, InvalidConfigurationException{

		if (label.equalsIgnoreCase("tpahere")){

			Player craftPlayer = (Player) sender;
			String playerName = craftPlayer.getName();
			String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player

			Player craftPlayerArgs = null;
			String playerUUIDArgs = null; //UUID Player

			if (args.length == 1){

				if (CommandUtilities.comprobarJugador(sender, args [0])){

					craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
					playerUUIDArgs = craftPlayerArgs.getUniqueId().toString();

					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUIDArgs + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);
					
					playerData.load(f);

					playerData.set("TpaHere", playerName);

					craftPlayerArgs.sendMessage(ChatColor.GRAY + "El usuario " + playerName + " quiere ir donde estas tu.");
					craftPlayerArgs.sendMessage(ChatColor.GRAY + "Si aceptas, escribe en el chat " + ChatColor.RED + "/tpahere aceptar" + ChatColor.GRAY + ".");
					craftPlayerArgs.sendMessage(ChatColor.GRAY + "Si no quieres, escribe en el chat " + ChatColor.RED + "/tpahere denegar" + ChatColor.GRAY + ".");

					playerData.save(f);

				}
				else if (args[0].equals("aceptar")){

					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUID + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

					playerData.load(f);

					String playerNameTpa = playerData.getString("TpaHere");

					if(playerNameTpa != null){
						if (CommandUtilities.comprobarJugador(sender, playerNameTpa)){

							Player playerCraftTpa = craftPlayer.getServer().getPlayer(playerNameTpa);

							playerCraftTpa.teleport(craftPlayer);

							craftPlayer.sendMessage(ChatColor.GRAY + "Se ha teletransportado con exito a " + playerNameTpa + ".");

							playerData.set("TpaHere", null);

						}
						else {

							CommandUtilities.jugadorDesconectado(sender);

							playerData.set("TpaHere", null);

						}

					}
					else {

						craftPlayer.sendMessage(ChatColor.RED + "No puedes aceptar un tpahere de 'nadie'.");

					}
					playerData.save(f);

				}
				else if (args[0].equals("denegar")){

					File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
					File f = new File(userdata, File.separator + playerUUID + ".yml");
					FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

					playerData.load(f);

					String playerNameTpa = playerData.getString("TpaHere");

					if(playerNameTpa != null){
						if (CommandUtilities.comprobarJugador(sender, playerNameTpa)){

							craftPlayer.sendMessage(ChatColor.RED + "Has denegado el tpahere.");

							playerData.set("TpaHere", null);
						}
						else {

							CommandUtilities.jugadorDesconectado(sender);

							playerData.set("TpaHere", null);
						}
					}
					else {

						craftPlayer.sendMessage(ChatColor.RED + "No puedes denegar un tpahere de 'nadie'.");
					}
					playerData.save(f);
				}
				else {
					CommandUtilities.formaCorrecta(sender, "/tpa <player/aceptar/denegar>");
				}
			}
			return true;	
		}	
		return false;
	}
}
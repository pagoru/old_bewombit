package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import es.bewom.bewomBit.commands.utility.CommandUtilities;

public class CommandCongelar {

	@SuppressWarnings({ "deprecation" })
	public static boolean commandcongelar (CommandSender sender, Command cmd, String commandLabel, String [] args){

		if (commandLabel.equalsIgnoreCase("congelar")){

			boolean isCongelado = false;
			boolean argIsCongelado = false;

			File data1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File data = new File(data1, File.separator + "config.yml");
			FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

			try {
				try {
					try {

						if (args.length == 0){
							Data.load(data);
							isCongelado = Data.getBoolean("Congelado");
							
							if(isCongelado){	
								Data.set("Congelado", false);
								sender.sendMessage(ChatColor.GRAY + "Usuarios descongelados.");
							}
							else {
								Data.set("Congelado", true);
								sender.sendMessage(ChatColor.RED + "Usuarios congelados.");
							}
							Data.save(data);
						}
						
						else if (args.length == 1){

							if (sender.getServer().getPlayer(args[0]) != null){

								UUID argUUID = sender.getServer().getOfflinePlayer(args[0]).getUniqueId();
								
								File argsdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
								File arg = new File(argsdata, File.separator + argUUID + ".yml");
								FileConfiguration argData = YamlConfiguration.loadConfiguration(arg);

								argData.load(arg);
								argIsCongelado = argData.getBoolean("Congelado");

								if(argIsCongelado){

									argData.set("Congelado", false);
									sender.sendMessage(ChatColor.GRAY + "Usuario " + args[0] + " descongelado.");

								} else {
									argData.set("Congelado", true);
									sender.sendMessage(ChatColor.RED + "Usuario " + args[0] + " congelado.");
								}
								argData.save(arg);
							} else {
								CommandUtilities.jugadorDesconectado(sender);
							}
						}
						else {
							CommandUtilities.formaCorrecta(sender, "/congelar [player]");
						}
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

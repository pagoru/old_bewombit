package es.bewom.bewomBit.groups.congelar;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import es.bewom.bewomBit.utility.commands.CommandUtilities;

public class CommandCongelar {

	@SuppressWarnings("deprecation")
	public static boolean commandcongelar (CommandSender sender, Command cmd, String commandLabel, String [] args) throws Exception{

		if (commandLabel.equalsIgnoreCase("congelar")){

			boolean isCongelado = false;
			boolean argIsCongelado = false;

			File data1 = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
			File data = new File(data1, File.separator + "config.yml");
			FileConfiguration Data = YamlConfiguration.loadConfiguration(data);

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
				
				if (sender.getServer().getPlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId()) != null){

					UUID argUUID = sender.getServer().getOfflinePlayer(Bukkit.getServer().getOfflinePlayer(args[0]).getUniqueId()).getUniqueId();
					
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

			return true;
		}
		return false;
	}
}

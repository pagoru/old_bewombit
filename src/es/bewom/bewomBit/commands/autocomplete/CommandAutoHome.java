package es.bewom.bewomBit.commands.autocomplete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandAutoHome {
	
	@SuppressWarnings({ "unused"})
	public static List<String> commandautohome(CommandSender sender, Command cmd, String alias, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(cmd.getName().equalsIgnoreCase("home")){
			
			Player craftPlayer = (Player) sender;
			String playerName = craftPlayer.getName();
			UUID playerUUID = craftPlayer.getUniqueId();
			
			File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "UserData");
			File f = new File(userdata, File.separator + playerUUID + ".yml");
			FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

			playerData.load(f);
			
			if (args.length == 1){
				
				List<String> pLista = playerData.getStringList("Homes.List");
											
				return pLista;
				
			}
			
			playerData.save(f);

		}	
		
		return null;
	}
}

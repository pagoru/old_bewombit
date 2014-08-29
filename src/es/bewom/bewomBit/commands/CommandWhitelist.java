package es.bewom.bewomBit.commands;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CommandWhitelist {

	@SuppressWarnings("deprecation")
	public static boolean commandwhitelist (CommandSender sender, Command cmd, String label, String [] args) throws Exception{

		if (label.equalsIgnoreCase("whitelist")){

			File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File protecciondata = new File(protecciondata1, File.separator + "whitelist.yml");
			File protecciondataConfig = new File(protecciondata1, File.separator + "config.yml");
			FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);
			FileConfiguration proteccionData1 = YamlConfiguration.loadConfiguration(protecciondataConfig);

			proteccionData.load(protecciondata);
			proteccionData1.load(protecciondataConfig);


			if (args.length == 1){		
				
				Boolean white = proteccionData1.getBoolean("Whitelist");
					
				if (args[0].equals("on")){		
					
					if(!white){
						
						proteccionData1.set("Whitelist", true);
						sender.sendMessage(ChatColor.GRAY + "Whitelist activada.");		
						
					} else {
						
						sender.sendMessage(ChatColor.RED + "La whitelist ya estaba activada.");	
						
					}
			
				} else if (args[0].equals("off")){
					
					if(white){
						
						proteccionData1.set("Whitelist", false);
						sender.sendMessage(ChatColor.GRAY + "Whitelist desactivada.");			
						
					} else {
						
						sender.sendMessage(ChatColor.RED + "La whitelist ya estaba desactivada.");		
						
					}
									
				}
				
			}
			else if (args.length == 2){

				String wPlayer = args[1];
				UUID wPlayerUUID = Bukkit.getServer().getOfflinePlayer(wPlayer).getUniqueId();
				wPlayer = Bukkit.getServer().getOfflinePlayer(wPlayerUUID).getName();	
				
				actualizarWhitelist (proteccionData, args [0], wPlayer, sender);		
			}			
			proteccionData1.save(protecciondataConfig);
			proteccionData.save(protecciondata);

			return true;
		}		
		return false;
	}
	
	public static void actualizarWhitelist (FileConfiguration proteccionData, String arg, String wPlayer, CommandSender sender){
		
		List<String> pLista = proteccionData.getStringList("miembros");
		if (arg.equals("añadir")){

			if(!pLista.contains(wPlayer)){
				pLista.add(wPlayer);

				proteccionData.set("miembros", null);
				proteccionData.set("miembros", pLista);

				sender.sendMessage(ChatColor.GRAY + wPlayer + " añadido a la whitelist.");
			}
			else {
				sender.sendMessage(ChatColor.GRAY + "No puedes añadir a " + wPlayer + " a la whitelist.");
			}
		}
		else if (arg.equals("eliminar")){

			if(pLista.contains(wPlayer)){
				pLista.remove(wPlayer);

				proteccionData.set("miembros", null);
				proteccionData.set("miembros", pLista);
				
				sender.sendMessage(ChatColor.GRAY + wPlayer + " eliminado de la whitelist.");
			}
			else {						
				sender.sendMessage(ChatColor.GRAY + "No puedes quitar a " + wPlayer + " de la whitelist.");	
			}					
		}	
	}
}
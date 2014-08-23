package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandAmigos {
	
	@SuppressWarnings({ "deprecation" })
	public static boolean commandban (CommandSender sender, Command cmd, String label, String [] args) throws FileNotFoundException, IOException, InvalidConfigurationException, SQLException, ClassNotFoundException, ParseException{

		if (label.equalsIgnoreCase("amigos")){
			
			final Player craftPlayer = ((OfflinePlayer) sender).getPlayer();
			String playerName = craftPlayer.getName();
			
			File amigosdata = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File f = new File(amigosdata, File.separator + "amigos.yml");
			FileConfiguration amigosData = YamlConfiguration.loadConfiguration(f);
			
			amigosData.load(f);
			
			if (args.length == 2){
				
				if(args[0].equals("añadir")){
					
					UUID craftPlayerUUIDArgs = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(craftPlayerUUIDArgs);
					
					if(craftPlayerArgs.getLastPlayed() != 0){
						
						List<String> pListaP = amigosData.getStringList(playerName + ".amigos");
						List<String> pLista1 = amigosData.getStringList(craftPlayerArgs.getName() + ".amigos");
						
						List<String> pLista = amigosData.getStringList(playerName + ".solicitudes");
						List<String> pLista2 = amigosData.getStringList(craftPlayerArgs.getName() + ".solicitudes");
						
						if(pLista.contains(craftPlayerArgs.getName())){
							
							craftPlayer.sendMessage(ChatColor.GREEN + "Ahora " + craftPlayerArgs.getName() + " es tu amigo.");
							
							pLista.remove(craftPlayerArgs.getName());
							amigosData.set(playerName + ".solicitudes", pLista);	
							
							pLista2.remove(playerName);
							amigosData.set(craftPlayerArgs.getName() + ".solicitudes", pLista2);	
							
							pListaP.add(craftPlayerArgs.getName());
							pLista1.add(playerName);
							
							amigosData.set(playerName + ".amigos", pListaP);
							amigosData.set(craftPlayerArgs.getName() + ".amigos", pLista1);
							
							if(Bukkit.getServer().getPlayer(craftPlayerUUIDArgs) != null){
								
								craftPlayerArgs.getPlayer().sendMessage(ChatColor.GREEN + "El usuario " + playerName + " a aceptado tu solicitud de amistad.");
								
							}
							
						} else {
							
							if(!pListaP.contains(craftPlayerArgs.getName()) && !pLista1.contains(playerName)){
								
								if(!pLista2.contains(playerName)){
									
									if(!craftPlayerArgs.getName().equals(playerName)){
										
										craftPlayer.sendMessage(ChatColor.GREEN + "Se ha enviado una solicitud de amistad al usuario " + craftPlayerArgs.getName() + ".");
										
										pLista2.add(playerName);
										amigosData.set(craftPlayerArgs.getName() + ".solicitudes", pLista2);
										
										if(Bukkit.getServer().getPlayer(craftPlayerUUIDArgs) != null){
											
											craftPlayerArgs.getPlayer().sendMessage(ChatColor.GREEN + "El usuario " + playerName + " quiere ser tu amigo.");
											
										}
										
									} else {
										
										craftPlayer.sendMessage(ChatColor.RED + "No puedes enviarte una solicitud de amistad a ti mismo.");
										
									}					
									
								} else {
									
									craftPlayer.sendMessage(ChatColor.RED + "No puedes enviar dos solicitudes de amistad al mismo usuario.");
									
								}
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + "No puedes añadir a alguien que ya es amigo tuyo.");
								
							}
							
						}
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + "El jugador " + args[1] + " no existe.");
						
					}
					
				} else if(args[0].equals("eliminar")){
					
					UUID craftPlayerUUIDArgs = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(craftPlayerUUIDArgs);
					
					List<String> pListaP = amigosData.getStringList(playerName + ".amigos");
					List<String> pLista = amigosData.getStringList(craftPlayerArgs.getName() + ".amigos");
					
					if(pListaP.contains(craftPlayerArgs.getName()) && pLista.contains(playerName)){
													
						craftPlayer.sendMessage(ChatColor.GREEN + "Se ha eliminado de tu lista de amigos a " + craftPlayerArgs.getName() + ".");
						
						pLista.remove(playerName);
						pListaP.remove(craftPlayerArgs.getName());
						amigosData.set(craftPlayerArgs.getName() + ".amigos", pLista);		
						amigosData.set(playerName + ".amigos", pListaP);	
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + "No puedes eliminar a " + craftPlayerArgs.getName() + ", no lo tienes en tu lista de amigos.");
						
					}
					
				} else if(args[0].equals("aceptar")){
					
					UUID craftPlayerUUIDArgs = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(craftPlayerUUIDArgs);
					
					List<String> pListaP = amigosData.getStringList(playerName + ".amigos");
					List<String> pLista1 = amigosData.getStringList(craftPlayerArgs.getName() + ".amigos");
					
					List<String> pLista = amigosData.getStringList(playerName + ".solicitudes");
					List<String> pLista2 = amigosData.getStringList(craftPlayerArgs.getName() + ".solicitudes");
					
					if(!pListaP.contains(craftPlayerArgs.getName())){
						
						if(pLista.contains(craftPlayerArgs.getName())){
							
							craftPlayer.sendMessage(ChatColor.GREEN + "Ahora " + craftPlayerArgs.getName() + " es tu amigo.");
							
							pLista.remove(craftPlayerArgs.getName());
							amigosData.set(playerName + ".solicitudes", pLista);	
							
							pLista2.remove(playerName);
							amigosData.set(craftPlayerArgs.getName() + ".solicitudes", pLista2);	
							
							pListaP.add(craftPlayerArgs.getName());
							pLista1.add(playerName);
							
							amigosData.set(playerName + ".amigos", pListaP);
							amigosData.set(craftPlayerArgs.getName() + ".amigos", pLista1);
							
							if(Bukkit.getServer().getPlayer(craftPlayerUUIDArgs) != null){
								
								craftPlayerArgs.getPlayer().sendMessage(ChatColor.GREEN + "El usuario " + playerName + " a aceptado tu solicitud de amistad.");
								
							}
							
						} else {
							
							craftPlayer.sendMessage(ChatColor.RED + craftPlayerArgs.getName() + " no te ha mandando ninguna solicitud de amistad.");
							
						}
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + craftPlayerArgs.getName() + " ya esta en tu lista de amigos.");
						
					}
					
					
					
				} else if(args[0].equals("rechazar")){
					
					UUID craftPlayerUUIDArgs = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(craftPlayerUUIDArgs);
					
					List<String> pListaP = amigosData.getStringList(playerName + ".amigos");
					
					List<String> pLista = amigosData.getStringList(playerName + ".solicitudes");
					List<String> pLista2 = amigosData.getStringList(craftPlayerArgs.getName() + ".solicitudes");
					
					if(!pListaP.contains(craftPlayerArgs.getName())){
						
						if(pLista.contains(craftPlayerArgs.getName())){
							
							craftPlayer.sendMessage(ChatColor.GREEN + "Has rechazado a " + craftPlayerArgs.getName() + " como amigo.");
							
							pLista.remove(craftPlayerArgs.getName());
							amigosData.set(playerName + ".solicitudes", pLista);	
							
							pLista2.remove(playerName);
							amigosData.set(craftPlayerArgs.getName() + ".solicitudes", pLista2);	
							
						} else {
							
							craftPlayer.sendMessage(ChatColor.RED + craftPlayerArgs.getName() + " no te ha mandando ninguna solicitud de amistad.");
							
						}
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + craftPlayerArgs.getName() + " ya esta en tu lista de amigos.");
						
					}
					
				}	
				
			}
			
			amigosData.save(f);
			return true;
			
		}
		return false;
	}
}

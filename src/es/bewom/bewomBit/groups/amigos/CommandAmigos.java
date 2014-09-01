package es.bewom.bewomBit.groups.amigos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandAmigos {
	
	@SuppressWarnings("deprecation")
	public static boolean commandamigos (CommandSender sender, Command cmd, String label, String [] args) throws Exception{

		if (label.equalsIgnoreCase("amigos")){
			
			final Player craftPlayer = ((OfflinePlayer) sender).getPlayer();
			String playerName = craftPlayer.getName();
			
			File amigosdata = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
			File f = new File(amigosdata, File.separator + "amigos.yml");
			FileConfiguration amigosData = YamlConfiguration.loadConfiguration(f);
			
			amigosData.load(f);
			
			if (args.length == 0){
				
				List<String> pList = amigosData.getStringList(playerName + ".amigos");
				
				ArrayList<String> myList = new ArrayList<String>();
				
				boolean color = true;
				int numAmigos = 0;
				
				for(String craftPlayerList : pList){
					
					if(color == true){
						myList.add(ChatColor.GREEN + craftPlayerList + ChatColor.WHITE);	
						color = false;
					}
					else {
						myList.add(ChatColor.DARK_GREEN + craftPlayerList + ChatColor.WHITE);	
						color = true;
					}
					numAmigos = numAmigos + 1;
					
				}
				
				if(numAmigos == 0){
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + "No tienes amigos! :( ");
					
				} else if(numAmigos == 1){
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Tienes 1 amigo! " + ChatColor.WHITE + myList);
					
				} else {
					
					craftPlayer.sendMessage(ChatColor.DARK_AQUA + "Tienes " + numAmigos  + " amigos! " + ChatColor.WHITE + myList);
					
				}
				
			} else if (args.length == 1){
				
				if(args[0].equals("solicitudes")){
					
					List<String> pLista = amigosData.getStringList(playerName + ".solicitudes");
					
					if(!pLista.isEmpty()){
						
						ArrayList<String> myList = new ArrayList<String>();
						
						boolean color = true;
						
						for(String craftPlayerList : pLista){
							
							if(color == true){
								myList.add(ChatColor.GREEN + craftPlayerList + ChatColor.WHITE);	
								color = false;
							}
							else {
								myList.add(ChatColor.DARK_GREEN + craftPlayerList + ChatColor.WHITE);	
								color = true;
							}
							
						}
							
						craftPlayer.sendMessage(ChatColor.GREEN + "Solicitudes: " + ChatColor.WHITE + myList);
						craftPlayer.sendMessage(ChatColor.GRAY + "Para aceptar las solicitudes " + ChatColor.GREEN + "" + ChatColor.ITALIC + "/amigos aceptar [nick]");
						craftPlayer.sendMessage(ChatColor.GRAY + "Para rechazar las solicitudes " + ChatColor.RED + "" + ChatColor.ITALIC + "/amigos rechazar [nick]");
						
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + "No tienes solicitudes de amistad pendientes.");
						
					}
					
				}
			
			} else if (args.length == 2){
				
				if(args[0].equals("añadir")){
					
					String namePlayer = args[1];
					
					UUID craftPlayerUUIDArgs = Bukkit.getServer().getOfflinePlayer(namePlayer).getUniqueId();
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(craftPlayerUUIDArgs);
					
					if(!craftPlayerArgs.getName().equals(playerName)){
					
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
											
										craftPlayer.sendMessage(ChatColor.GREEN + "Se ha enviado una solicitud de amistad al usuario " + craftPlayerArgs.getName() + ".");
										
										pLista2.add(playerName);
										amigosData.set(craftPlayerArgs.getName() + ".solicitudes", pLista2);
										
										if(Bukkit.getServer().getPlayer(craftPlayerUUIDArgs) != null){
											
											craftPlayerArgs.getPlayer().sendMessage(ChatColor.GREEN + "El usuario " + playerName + " quiere ser tu amigo.");
											craftPlayerArgs.getPlayer().sendMessage(ChatColor.GRAY + "Para aceptar la solicitud " + ChatColor.GREEN + "" + ChatColor.ITALIC + "/amigos aceptar " + playerName + "");
											craftPlayerArgs.getPlayer().sendMessage(ChatColor.GRAY + "Para rechazar la solicitud " + ChatColor.RED + "" + ChatColor.ITALIC + "/amigos rechazar " + playerName + "");
											
										}
	
									} else {
										
										craftPlayer.sendMessage(ChatColor.RED + "No puedes enviar dos solicitudes de amistad al mismo usuario.");
										
									}
									
								} else {
									
									craftPlayer.sendMessage(ChatColor.RED + "No puedes a�adir a alguien que ya es amigo tuyo.");
									
								}
								
							}
							
						} else {
							
							craftPlayer.sendMessage(ChatColor.RED + "El jugador " + craftPlayerArgs.getName() + " no existe.");
							
						}
					
					} else {
						
						craftPlayer.sendMessage(ChatColor.RED + "No puedes enviarte una solicitud de amistad a ti mismo.");
						
					}	
					
				} else if(args[0].equals("eliminar")){
					
					String namePlayer = args[1];
					UUID craftPlayerUUIDArgs = Bukkit.getServer().getOfflinePlayer(namePlayer).getUniqueId();
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
					
					String namePlayer = args[1];
					UUID craftPlayerUUIDArgs = Bukkit.getServer().getOfflinePlayer(namePlayer).getUniqueId();
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
					
					String namePlayer = args[1];
					UUID craftPlayerUUIDArgs = Bukkit.getServer().getOfflinePlayer(namePlayer).getUniqueId();
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

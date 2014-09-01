package es.bewom.bewomBit.groups.p;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandP {

	private static String getlocationBlockPlayerName;
	private static String getlocationBlockPlayerNameW;
	private static String getlocationBlockPlayerName2;
	@SuppressWarnings({"unused", "deprecation" })
	public static boolean commandp(CommandSender sender, Command cmd, String label, String[] args) throws IOException, InvalidConfigurationException{

		Player craftPlayer = (Player) sender;
		String playerName = craftPlayer.getName();

		String material = null;
		String nombreMaterial = null;
		String nombreMaterialSimple = null;

		if (label.equalsIgnoreCase("p")){

			if (craftPlayer.getTargetBlock(null, 5).getType() == Material.CHEST ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.HOPPER ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.DROPPER ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.HOPPER ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.TRAPPED_CHEST ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.JUKEBOX ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.FURNACE ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.ANVIL ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.ENCHANTMENT_TABLE ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.ENDER_CHEST ||
					craftPlayer.getTargetBlock(null, 5).getType() == Material.WOODEN_DOOR){

				if(craftPlayer.getTargetBlock(null, 5).getType() == Material.CHEST){
					material = "Chest";
					nombreMaterial = "Este cofre";
					nombreMaterialSimple = "este cofre";
				} if (craftPlayer.getTargetBlock(null, 5).getType() == Material.HOPPER){
					material = "Hopper";
					nombreMaterial = "Este hopper";
					nombreMaterialSimple = "este hopper";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.TRAPPED_CHEST){
					material = "TrappedChest";
					nombreMaterial = "Este cofre trampa";
					nombreMaterialSimple = "este cofre trampa";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.FURNACE){
					material = "Furnace";
					nombreMaterial = "Este horno";
					nombreMaterialSimple = "este horno";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.ANVIL){
					material = "Anvil";
					nombreMaterial = "Este yunque";
					nombreMaterialSimple = "este yunque";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.DROPPER){
					material = "Dropper";
					nombreMaterial = "Este dropper";
					nombreMaterialSimple = "este dropper";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.JUKEBOX){
					material = "Jukebox";
					nombreMaterial = "Esta jukebox";
					nombreMaterialSimple = "esta jukebox";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.ENCHANTMENT_TABLE){
					material = "EnchantmentTable";
					nombreMaterial = "Esta mesa de encantamientos";
					nombreMaterialSimple = "esta mesa de encantamientos";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.ENDER_CHEST){
					material = "EnderChest";
					nombreMaterial = "Este enderchest";
					nombreMaterialSimple = "este enderchest";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.WOODEN_DOOR){
					material = "WoodenDoor";
					nombreMaterial = "Esta puerta";
					nombreMaterialSimple = "esta puerta";
				}

				int locationBlockX = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockX();
				int locationBlockY = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockY();
				int locationBlockZ = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockZ();

				String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);

				File protecciondata1 = Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder();
				File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
				FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);

				proteccionData.load(protecciondata);

				if (args.length == 0) {

					craftPlayer.sendMessage(ChatColor.RED + "Usa bien el comando, /p [publico/privado/añadir/eliminar].");

				}
				else if (args.length == 1){
					
					if(args[0].equals("añadir")){
						craftPlayer.sendMessage(ChatColor.RED + "Usa bien el comando, /p añadir [jugador].");
					} else if(args[0].equals("eliminar")){
						craftPlayer.sendMessage(ChatColor.RED + "Usa bien el comando, /p eliminar [jugador].");
					}
					
					int getlocationBlockHash = 0;
					getlocationBlockPlayerName2 = null;
					int getlocationBlockX = 0;
					int getlocationBlockY = 0;
					int getlocationBlockZ = 0;
					String getlocationBlockEstado = null;

					getlocationBlockHash = proteccionData.getInt(material + "." + hash);
					getlocationBlockPlayerName2 = proteccionData.getString(material + "." + hash + ".playerName");
					getlocationBlockX = proteccionData.getInt(material + "." + hash + ".X");
					getlocationBlockY = proteccionData.getInt(material + "." + hash + ".Y");
					getlocationBlockZ = proteccionData.getInt(material + "." + hash + ".Z");
					getlocationBlockEstado = proteccionData.getString(material + "." + hash + ".estado");

					String gethash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
					
					if(craftPlayer.getTargetBlock(null, 5).getType() == Material.WOODEN_DOOR){
						
						if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){
							
							if(getlocationBlockPlayerName != null){
								
								if(!getlocationBlockPlayerName.equals("Steve")){
								
									if(gethash.equals(hash)){
										
										if (args[0].equals("privado")){
	
											privatizar (proteccionData, material, hash, craftPlayer, nombreMaterial);
											
										} else if (args[0].equals("publico")) {
	
											estatalizar (proteccionData, material, hash, craftPlayer, nombreMaterial);
											
										}
										
									} 
									
								} else {
									
									craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
									
								}
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
								
							}
														
						} else if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){
							
							String hashW = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
							String gethashW = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
							
							getlocationBlockPlayerNameW = proteccionData.getString(material + "." + hashW + ".playerName");
							
							if(getlocationBlockPlayerNameW != null){
								
								if(!getlocationBlockPlayerNameW.equals("Steve")){
							
									if(gethashW.equals(hashW)){
										
										if (args[0].equals("privado")){
		
											privatizar (proteccionData, material, hashW, craftPlayer, nombreMaterial);
											
										} else if (args[0].equals("publico")) {
		
											estatalizar (proteccionData, material, hashW, craftPlayer, nombreMaterial);
											
										}
										
									}
									
								} else {
									
									craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
									
								}
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
								
							}
							
						}
						
					} else {
						
						if(getlocationBlockPlayerName != null){
							
							if(!getlocationBlockPlayerName.equals("Steve")){

								if (gethash.equals(hash)){
		
									if (getlocationBlockPlayerName2.equals(playerName)){
		
										if (args[0].equals("privado")){
		
											privatizar (proteccionData, material, hash, craftPlayer, nombreMaterial);
											
										}
										else if (args[0].equals("publico")) {
		
											estatalizar (proteccionData, material, hash, craftPlayer, nombreMaterial);
											
										}
									}
									else {
		
										craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName2 + ".");
		
									}
								}
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
								
							}
						
						} else {
							
							craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
							
						}
						
					}
					
				}
				else if (args.length == 2){
					
					UUID craftPlayerUUIDArgs = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(craftPlayerUUIDArgs);
					
					getlocationBlockPlayerName = proteccionData.getString(material + "." + hash + ".playerName");
					
					List <String> pLista = proteccionData.getStringList(material + "." + hash + ".miembros");
					
					if(craftPlayer.getTargetBlock(null, 5).getType() == Material.WOODEN_DOOR){
					
						if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){
							
							if(getlocationBlockPlayerName != null){
								
								if(!getlocationBlockPlayerName.equals("Steve")){
							
									if (getlocationBlockPlayerName.equals(playerName)){
									
										if (args[0].equals("añadir")){
											
											if (craftPlayerArgs.getName() != null){
												
												if(!pLista.contains(args[1])){
													
													if(!args[1].equals(playerName)){
												
														añadir(proteccionData, material, hash, craftPlayer, nombreMaterial, craftPlayerArgs.getName(), nombreMaterialSimple);
												
													} else {
														
														craftPlayer.sendMessage(ChatColor.RED + "No puedes añadirte a ti mismo en " + nombreMaterialSimple + ".");
							
													}
													
												} else {
													
													craftPlayer.sendMessage(ChatColor.RED + "Este usuario ya tiene permisos sobre " + nombreMaterialSimple + ".");
						
												}
												
											}
										} else if (args[0].equals("eliminar")) {
										
											if (craftPlayerArgs.getName() != null){
						
												eliminar(proteccionData, material, hash, craftPlayer, nombreMaterial, craftPlayerArgs.getName(),nombreMaterialSimple);
												
											}
										}
									
									} else {
			
										craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
			
									}
									
								} else {
									
									craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
									
								}
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
								
							}							
						} else if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){
							
							String hashW = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
							
							List <String> pListaW = proteccionData.getStringList(material + "." + hashW + ".miembros");
							getlocationBlockPlayerNameW = proteccionData.getString(material + "." + hashW + ".playerName");
							
							if(getlocationBlockPlayerNameW != null){
								
								if(!getlocationBlockPlayerNameW.equals("Steve")){
							
									if (getlocationBlockPlayerNameW.equals(playerName)){
										
										if (args[0].equals("añadir")){
											
											if (craftPlayerArgs.getName() != null){
												
												if(!pListaW.contains(args[1])){
													
													if(!args[1].equals(playerName)){
												
															añadir(proteccionData, material, hashW, craftPlayer, nombreMaterial, craftPlayerArgs.getName(), nombreMaterialSimple);
												
													} else {
														
														craftPlayer.sendMessage(ChatColor.RED + "No puedes añadirte a ti mismo en " + nombreMaterialSimple + ".");
							
													}
													
												} else {
													
													craftPlayer.sendMessage(ChatColor.RED + "Este usuario ya tiene permisos sobre " + nombreMaterialSimple + ".");
						
												}
												
											}
										} else if (args[0].equals("eliminar")) {
											
											if (craftPlayerArgs.getName() != null){
						
												eliminar(proteccionData, material, hashW, craftPlayer, nombreMaterial, craftPlayerArgs.getName(), nombreMaterialSimple);
												
											}
										}
										
									} else {
										
										craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
			
									}	
									
								} else {
									
									craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
									
								}
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
								
							}
							
						}
					} else {
						
						if(getlocationBlockPlayerName != null){
							
							if(!getlocationBlockPlayerName.equals("Steve")){
						
								if (getlocationBlockPlayerName.equals(playerName)){
								
									if (args[0].equals("añadir")){
										
										if(!pLista.contains(args[1])){
											
											if(!args[1].equals(playerName)){
					
												if (craftPlayerArgs.getName() != null){
													
													añadir(proteccionData, material, hash, craftPlayer, nombreMaterial, craftPlayerArgs.getName(), nombreMaterialSimple);
													
												}
										
											} else {
												
												craftPlayer.sendMessage(ChatColor.RED + "No puedes añadirte a ti mismo en " + nombreMaterialSimple + ".");
					
											}
											
										} else {
											
											craftPlayer.sendMessage(ChatColor.RED + "Este usuario ya tiene permisos sobre " + nombreMaterialSimple + ".");
				
										}
									}
									else if (args[0].equals("eliminar")) {
					
										if (craftPlayerArgs.getName() != null){
					
											eliminar(proteccionData, material, hash, craftPlayer, nombreMaterial, craftPlayerArgs.getName(), nombreMaterialSimple);
											
										}
									}
							
								} else {
									
									craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
		
								}
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
								
							}
							
						} else {
							
							craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " es de la naturaleza.");
						}	
						
					}
					
				} else if (args.length == 3){
					
					UUID craftPlayerUUIDArgs = Bukkit.getOfflinePlayer(args[2]).getUniqueId();
					OfflinePlayer craftPlayerArgs = Bukkit.getServer().getOfflinePlayer(craftPlayerUUIDArgs);

					if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod")){
						
						if(craftPlayer.getTargetBlock(null, 5).getType() == Material.WOODEN_DOOR){
							
							if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){
								
								if (args[0].equals("cambiar")){
									
									if (args[1].equals("propietario")){
		
										cambiar (proteccionData, material, hash, craftPlayer, nombreMaterial, nombreMaterialSimple, craftPlayerArgs.getName());
										proteccionData.set(material + "." + hash + ".playerName", craftPlayerArgs.getName());
		
									}
									else if (args[1].equals("estado")){
		
										if (args[2].equals("privado")){
		
											privatizar (proteccionData, material, hash, craftPlayer, nombreMaterial);
										}
										else if (args[2].equals("publico")){
		
											estatalizar (proteccionData, material, hash, craftPlayer, nombreMaterial);												
										}											
									}									
								}
															
							} else if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){
								
								String hashW = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
								
								if (args[0].equals("cambiar")){
									
									if (args[1].equals("propietario")){
		
										cambiar (proteccionData, material, hashW, craftPlayer, nombreMaterial, nombreMaterialSimple, craftPlayerArgs.getName());
										proteccionData.set(material + "." + hashW + ".playerName", craftPlayerArgs.getName());
		
									}
									else if (args[1].equals("estado")){
		
										if (args[2].equals("privado")){
		
											privatizar (proteccionData, material, hashW, craftPlayer, nombreMaterial);
										}
										else if (args[2].equals("publico")){
		
											estatalizar (proteccionData, material, hashW, craftPlayer, nombreMaterial);												
										}											
									}									
								}
								
							}
							
						} else {
						
							if (args[0].equals("cambiar")){
	
								if (args[1].equals("propietario")){
	
									cambiar (proteccionData, material, hash, craftPlayer, nombreMaterial, nombreMaterialSimple, craftPlayerArgs.getName());
									proteccionData.set(material + "." + hash + ".playerName", craftPlayerArgs.getName());
	
								}
								else if (args[1].equals("estado")){
	
									if (args[2].equals("privado")){
	
										privatizar (proteccionData, material, hash, craftPlayer, nombreMaterial);
									}
									else if (args[2].equals("publico")){
	
										estatalizar (proteccionData, material, hash, craftPlayer, nombreMaterial);												
									}											
								}									
							}
						}	
					}
				} else {

					craftPlayer.sendMessage(ChatColor.RED + "Usa bien el comando, /p [publico/privado].");

				}
				proteccionData.save(protecciondata);

			} else {

				craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " no se puede proteger.");
			}	
		return true;
		
		}
		
		return false;
	}

	public static void privatizar (FileConfiguration proteccionData, String material, String hash, Player craftPlayer, String nombreMaterial){

		proteccionData.set(material + "." + hash + ".estado", "privado");
		craftPlayer.sendMessage(ChatColor.GRAY + nombreMaterial + " ahora esta privatizado.");

		if (material.equals("Chest") || material.equals("TrappedChest")){

			actualizarEstado (proteccionData, material, hash, "privado");
		}
	}

	public static void estatalizar (FileConfiguration proteccionData, String material, String hash, Player craftPlayer, String nombreMaterial){

		proteccionData.set(material + "." + hash + ".estado", "publico");
		craftPlayer.sendMessage(ChatColor.GRAY + nombreMaterial + " ahora esta publico.");

		if (material.equals("Chest") || material.equals("TrappedChest")){

			actualizarEstado (proteccionData, material, hash, "publico");
		}
	}

	public static void añadir(FileConfiguration proteccionData, String material, String hash, Player craftPlayer, String nombreMaterial, String arg, String nombreMaterialSimple){

		List <String> pList = new ArrayList<String>(); 
		List <String> pLista = proteccionData.getStringList(material + "." + hash + ".miembros");
		craftPlayer.sendMessage(ChatColor.GRAY + "Ahora " + arg + " puede interactuar en " + nombreMaterialSimple + ".");

		pList.addAll(pLista);
		pList.add(arg);

		proteccionData.set(material + "." + hash + ".miembros", pList);

		if (material.equals("Chest") || material.equals("TrappedChest")){

			actualizarPropietarios (proteccionData, material, hash, pList);
			
		}
	}

	public static void eliminar(FileConfiguration proteccionData, String material, String hash, Player craftPlayer, String nombreMaterial, String arg, String nombreMaterialSimple){

		List <String> pList = new ArrayList<String>(); 
		List <String> pLista = proteccionData.getStringList(material + "." + hash + ".miembros");
		
		if(pLista.contains(arg)){
			
			craftPlayer.sendMessage(ChatColor.GRAY + "Ahora " + arg + " ya no puede interactuar en " + nombreMaterialSimple + ".");
			
			pList.addAll(pLista);
			pList.remove(arg);

			proteccionData.set(material + "." + hash + ".miembros", pList);

			if (material.equals("Chest") || material.equals("TrappedChest")){

				actualizarPropietarios (proteccionData, material, hash, pList);
				
			}
			
		} else {
			
			craftPlayer.sendMessage(ChatColor.RED + arg + " no es miembro de " + nombreMaterialSimple + ".");
			
		}
		
	}

	public static void cambiar (FileConfiguration proteccionData, String material, String hash, Player craftPlayer, String nombreMaterial, String nombreMaterialSimple, String arg){
		
		proteccionData.set(material + "." + hash + ".playerName", arg);
		craftPlayer.sendMessage(ChatColor.GRAY + "El nuevo dueño de " + nombreMaterialSimple + " es " + arg + ".");

		if (material.equals("Chest") || material.equals("TrappedChest")){

			actualizarPropietarios (proteccionData, material, hash, arg);
		}
	}

	public static void actualizarEstado(FileConfiguration proteccionData, String material, String hash, String estado){

		boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");

		if(getdobleChest) {
			
			String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");
			proteccionData.set(material + "." + getdobleChestHash + ".estado", estado);
		}
	}

	public static void actualizarPropietarios (FileConfiguration proteccionData, String material, String hash, List <String> pList){
		boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");

		if(getdobleChest) {
			
			String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");
			proteccionData.set(material + "." + getdobleChestHash + ".miembros", pList);
		}
	}
	public static void actualizarPropietarios (FileConfiguration proteccionData, String material, String hash, String arg){
	
		boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");

		if(getdobleChest) {
			
			String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");
			proteccionData.set(material + "." + getdobleChestHash + ".playerName", arg);
		}
	}
}
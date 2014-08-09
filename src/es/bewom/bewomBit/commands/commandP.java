package es.bewom.bewomBit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class commandP {
	
	@SuppressWarnings({ "deprecation", "unused" })
	public static boolean commandp(CommandSender sender, Command cmd, String label, String[] args){
		
		Player craftPlayer = (Player) sender;
		String playerName = craftPlayer.getName();
		
		String material = null;
		String nombreMaterial = null;
		String nombreMaterialSimple = null;
		
		if (label.equalsIgnoreCase("p")){
			
			if (craftPlayer.getTargetBlock(null, 5).getType() == Material.CHEST || craftPlayer.getTargetBlock(null, 5).getType() == Material.HOPPER || craftPlayer.getTargetBlock(null, 5).getType() == Material.DROPPER || craftPlayer.getTargetBlock(null, 5).getType() == Material.HOPPER || craftPlayer.getTargetBlock(null, 5).getType() == Material.TRAPPED_CHEST || craftPlayer.getTargetBlock(null, 5).getType() == Material.JUKEBOX || craftPlayer.getTargetBlock(null, 5).getType() == Material.FURNACE || craftPlayer.getTargetBlock(null, 5).getType() == Material.ANVIL || craftPlayer.getTargetBlock(null, 5).getType() == Material.ENCHANTMENT_TABLE || craftPlayer.getTargetBlock(null, 5).getType() == Material.ENDER_CHEST){
				
				if(craftPlayer.getTargetBlock(null, 5).getType() == Material.CHEST){
					material = "Chest";
					nombreMaterial = "Este cofre";
					nombreMaterialSimple = "este cofre";
				} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.HOPPER){
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
				} 
				
					
				int locationBlockX = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockX();
				int locationBlockY = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockY();
				int locationBlockZ = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockZ();
				
				String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
				
				File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
				File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
				FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);
				
				try {
					try {
						try {
							proteccionData.load(protecciondata);
							
							if (args.length == 0) {
								
								craftPlayer.sendMessage(ChatColor.RED + "Usa bien el comando, /p [publico/privado].");
								
							} else if (args.length == 1){
								
								int getlocationBlockHash = 0;
								String getlocationBlockPlayerName = null;
								int getlocationBlockX = 0;
								int getlocationBlockY = 0;
								int getlocationBlockZ = 0;
								String getlocationBlockEstado = null;
								
								getlocationBlockHash = proteccionData.getInt(material + "." + hash);
								getlocationBlockPlayerName = proteccionData.getString(material + "." + hash + ".playerName");
								getlocationBlockX = proteccionData.getInt(material + "." + hash + ".X");
								getlocationBlockY = proteccionData.getInt(material + "." + hash + ".Y");
								getlocationBlockZ = proteccionData.getInt(material + "." + hash + ".Z");
								getlocationBlockEstado = proteccionData.getString(material + "." + hash + ".estado");
								
								String gethash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
								
								if (gethash.equals(hash)){
									
									if (getlocationBlockPlayerName.equals(playerName)){
										
										if (args[0].equals("privado")){
											
											proteccionData.set(material + "." + hash + ".estado", "privado");
											craftPlayer.sendMessage(ChatColor.GRAY + nombreMaterial + " ahora esta privatizado.");
											
											if (material.equals("Chest") || material.equals("TrappedChest")){
												
												Boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");
												
												if(getdobleChest) {
													
													String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");
													
													proteccionData.set(material + "." + getdobleChestHash + ".estado", "privado");
													
												}
											}
											
											
										} else  if (args[0].equals("publico")) {
											
											proteccionData.set(material + "." + hash + ".estado", "publico");
											craftPlayer.sendMessage(ChatColor.GRAY + nombreMaterial + " ahora esta publico.");
											
											if (material.equals("Chest") || material.equals("TrappedChest")){
												
												Boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");
												
												if(getdobleChest) {
													
													String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");
													
													proteccionData.set(material + "." + getdobleChestHash + ".estado", "publico");
													
												}
											}
											
										}
										
									} else {
										
										craftPlayer.sendMessage(ChatColor.RED + nombreMaterial + " pertenece a " + getlocationBlockPlayerName + ".");
										
									}
									
								}
							} else if (args.length == 3){
								
								if (sender.hasPermission("bewom.admin") || sender.hasPermission("bewom.mod")){
									
									if (args[0].equals("cambiar")){
										
										if (args[1].equals("propietario")){
											
											proteccionData.set(material + "." + hash + ".playerName", args[2]);
											craftPlayer.sendMessage(ChatColor.GRAY + "El nuevo dueño de " + nombreMaterialSimple + " es " + args[2] + ".");
											
											if (material.equals("Chest") || material.equals("TrappedChest")){
												
												Boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");
												
												if(getdobleChest) {
													
													String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");
													
													proteccionData.set(material + "." + getdobleChestHash + ".playerName", args[2]);
													
												}
											}
											
										} else if (args[1].equals("estado")){
											
											if (args[2].equals("privado")){
												
												proteccionData.set(material + "." + hash + ".estado", "privado");
												craftPlayer.sendMessage(ChatColor.GRAY + nombreMaterial + " ahora esta privatizado.");
												
												if (material.equals("Chest") || material.equals("TrappedChest")){
													
													Boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");
													
													if(getdobleChest) {
														
														String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");
														
														proteccionData.set(material + "." + getdobleChestHash + ".estado", "privado");
														
													}
												}
												
											} else if (args[2].equals("publico")){
												
												proteccionData.set(material + "." + hash + ".estado", "publico");
												craftPlayer.sendMessage(ChatColor.GRAY + nombreMaterial + " ahora esta publico.");
												
												if (material.equals("Chest") || material.equals("TrappedChest")){
													
													Boolean getdobleChest = proteccionData.getBoolean(material + "." + hash + ".doble");
													
													if(getdobleChest) {
														
														String getdobleChestHash = proteccionData.getString(material + "." + hash + ".dobleHash");
														
														proteccionData.set(material + "." + getdobleChestHash + ".estado", "publico");
														
													}
												}
												
											}
											
										}
										
									}
									
								}
								
							} else {
								
								craftPlayer.sendMessage(ChatColor.RED + "Usa bien el comando, /p [publico/privado].");
								
							}
								
							
							proteccionData.save(protecciondata);
				
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						
					} catch (IOException e) {
							e.printStackTrace();
					}
					
				} catch (InvalidConfigurationException e) {
						e.printStackTrace();
				}
				
				
			} else {
				
				craftPlayer.sendMessage(ChatColor.RED + "Este bloque no se puede proteger.");
			}
				
			return true;
		} 
			
		return false;
		
		
	
	}
	
	
	
}



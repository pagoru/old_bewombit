package es.bewom.bewomBit.groups.p;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CommandAutoP {

	@SuppressWarnings({ "unused", "deprecation" })
	public static List<String> commandautop(CommandSender sender, Command cmd, String alias, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(cmd.getName().equalsIgnoreCase("p")){
			
			Player craftPlayer = (Player) sender;
			String playerName = craftPlayer.getName();
			
			String material = null;
			String nombreMaterial = null;
			String nombreMaterialSimple = null;
			
			int locationBlockX = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockX();
			int locationBlockY = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockY();
			int locationBlockZ = craftPlayer.getTargetBlock(null, 5).getLocation().getBlockZ();
			
			String hash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
			
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
			} else if (craftPlayer.getTargetBlock(null, 5).getType() == Material.ENDER_CHEST){
				material = "WoodenDoor";
				nombreMaterial = "Esta puerta";
				nombreMaterialSimple = "este puerta";
			}
			
			int getlocationBlockHash = 0;
			String getlocationBlockPlayerName = null;
			int getlocationBlockX = 0;
			int getlocationBlockY = 0;
			int getlocationBlockZ = 0;
			String getlocationBlockEstado = null;
			
			String gethash = Integer.toString(locationBlockX) + Integer.toString(locationBlockY) + Integer.toString(locationBlockZ);
			
			File protecciondata1 = new File(Bukkit.getServer().getPluginManager().getPlugin("bewomBit").getDataFolder(), File.separator + "Config");
			File protecciondata = new File(protecciondata1, File.separator + "proteccion.yml");
			FileConfiguration proteccionData = YamlConfiguration.loadConfiguration(protecciondata);

			proteccionData.load(protecciondata);
			
			getlocationBlockHash = proteccionData.getInt(material + "." + hash);
			getlocationBlockPlayerName = proteccionData.getString(material + "." + hash + ".playerName");
			getlocationBlockX = proteccionData.getInt(material + "." + hash + ".X");
			getlocationBlockY = proteccionData.getInt(material + "." + hash + ".Y");
			getlocationBlockZ = proteccionData.getInt(material + "." + hash + ".Z");
			getlocationBlockEstado = proteccionData.getString(material + "." + hash + ".estado");
			
			if (args.length == 1){
				
				List<String> pList;
				
				if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){
					
					pList = Arrays.asList("privado","publico","añadir","eliminar","cambiar");  
				
				} else {
					
					pList = Arrays.asList("privado","publico","añadir","eliminar");  
					
				}
				
				return pList;
			} else if(args.length == 2 && args[0].equals("eliminar")){

				if (craftPlayer.getTargetBlock(null, 5).getType() == Material.WOODEN_DOOR){

					if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, 1, 0).getBlock().getType() == Material.WOODEN_DOOR){
					
						List<String> pLista = proteccionData.getStringList(material + "." + hash + ".miembros");
						return pLista;
					} else if(craftPlayer.getTargetBlock(null, 5).getLocation().add(0, -1, 0).getBlock().getType() == Material.WOODEN_DOOR){
						
						String hashW = Integer.toString(locationBlockX) + Integer.toString(locationBlockY-1) + Integer.toString(locationBlockZ);
						
						List<String> pLista = proteccionData.getStringList(material + "." + hashW + ".miembros");
						return pLista;
					}
					
				} else {
					
					List<String> pLista = proteccionData.getStringList(material + "." + hash + ".miembros");
					return pLista;
				}
				
				
				
			} else if(args.length == 2 && args[0].equals("cambiar")){
				
				if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){
					
					List<String> pList =  Arrays.asList("propietario","estado");  
					
					return pList;
				}
				
			} else if(args.length == 3 && args[0].equals("cambiar") && args[1].equals("estado")){
			
				if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){
					
					List<String> pList = Arrays.asList("privado","publico");
					
					return pList;
				}
			}
			
			proteccionData.save(protecciondata);

		}
		return null;
	}	
}

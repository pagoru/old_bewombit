package es.bewom.bewomBit.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawner {

	@SuppressWarnings({ "unused", "deprecation" })
	public static boolean commandspawner (CommandSender sender, Command cmd, String commandLabel, String [] args){
	
		if (commandLabel.equalsIgnoreCase("spawner")){
			
			Player craftPlayer = (Player) sender;
			String playerName = sender.getName();
			String playerUUID = craftPlayer.getUniqueId().toString(); //UUID Player
			
			if (craftPlayer.getTargetBlock(null, 5).getType().equals(Material.MOB_SPAWNER)){
				
				Location block = craftPlayer.getTargetBlock(null, 50).getLocation();
				
				if(args.length == 0){
					
				} else if (args.length == 1){
					
					CreatureSpawner spawner = ((CreatureSpawner) block.getBlock().getState());
					
					if(args[0].equals("MagmaCube")){

		                spawner.setCreatureTypeByName("LavaSlime");
						
					} else {
						
						spawner.setCreatureTypeByName(args[0]);
						
					}
	                
					spawner.setDelay(0);
	                spawner.update();
	                
				} else if (args.length == 2){
					
					String args1 = args[1].toString();
					
					int args2 = Integer.parseInt(args1);
					
					CreatureSpawner spawner = ((CreatureSpawner) block.getBlock().getState());
	                spawner.setCreatureTypeByName(args[0]);
	                spawner.setDelay(args2);
	                spawner.update();
					
				}
				
			}
			
			return true;
		}
		return false;
	
	}

}

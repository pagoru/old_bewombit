package es.bewom.bewomBit.groups.spawner;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.utility.commands.CommandUtilities;

public class CommandSpawner {

	@SuppressWarnings({ "deprecation" })
	public static boolean commandspawner (CommandSender sender, Command cmd, String commandLabel, String [] args){

		if (commandLabel.equalsIgnoreCase("spawner")){

			Player craftPlayer = (Player) sender;

			if (craftPlayer.getTargetBlock(null, 5).getType().equals(Material.MOB_SPAWNER)){

				Location block = craftPlayer.getTargetBlock(null, 50).getLocation();

				if (args.length == 1){		
				
					modificarSpawner (block, args[0], 0);
				}
				else if (args.length == 2){	
					
					modificarSpawner (block, args[0], Integer.parseInt(args[1].toString()));
				}
				else {
					CommandUtilities.formaCorrecta(sender, "/spawner <mob> [delay]");
				}
			}			
			return true;
		}
		return false;	
	}

	public static void modificarSpawner (Location block, String arg, int delay){

		CreatureSpawner spawner = ((CreatureSpawner) block.getBlock().getState());

		if(arg.equals("MagmaCube")){
			spawner.setCreatureTypeByName("LavaSlime");
		}
		else {
			spawner.setCreatureTypeByName(arg);
		}
		spawner.setDelay(delay);
		spawner.update();
	}
}
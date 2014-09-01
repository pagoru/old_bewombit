package es.bewom.bewomBit.groups.spawner;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandAutoSpawner {
	
	public static List<String> commandautospawner(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("spawner")){
			
			if (args.length == 1){
				
				List<String> pList = Arrays.asList("Blaze","CaveSpider","Chicken","Cow",
						"Creeper","EnderDragon","Enderman","Ghast",
						"Giant","MagmaCube","MushroomCow","Pig","PigZombie",
						"Sheep","SilverFish","Skeleton","Slime","Snowman","Spider",
						"Squid","Villager","Wolf","Zombie");
		        
				return pList;
			}		
		}
		return null;
	}
}
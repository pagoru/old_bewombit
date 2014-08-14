package es.bewom.bewomBit.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import es.bewom.bewomBit.utility.Lag;

public class CommandLag {
	
	public static boolean commandlag(CommandSender sender, Command cmd, String label, String[] args) throws FileNotFoundException, IOException, InvalidConfigurationException{

		if (label.equalsIgnoreCase("lag")){
			
			double TPS = Lag.getTPS();
			
			DecimalFormat df = new DecimalFormat("###");
			
			String TPSString = Objects.toString(df.format(TPS));
			
			if(args.length == 0){
				
				if(TPS >= 19){
					
					sender.sendMessage(ChatColor.GRAY + "El servidor va extremadamente bien!");
					
				} else if(TPS >= 17 && TPS < 19){
					
					sender.sendMessage(ChatColor.GRAY + "El servidor va bien!");
					
				} else if(TPS >= 14 && TPS < 17){
					
					sender.sendMessage(ChatColor.RED + "El servidor va regular!");
					
				} else if(TPS >= 10 && TPS < 14){
					
					sender.sendMessage(ChatColor.RED + "El servidor va mal!");
					
				} else if(TPS >= 0 && TPS < 10){
					
					sender.sendMessage(ChatColor.RED + "El servidor va realmente mal!");
					
				}
				
			} else if (args.length == 1){
				
				if(args[0].equals("tps")){
					
					sender.sendMessage(ChatColor.GRAY + "Los ticks por segundo actuales del servidor son " + TPSString + ".");
					
				}
				
			}
			
			
			
			return true;
			
		}
		return false;
	}
}

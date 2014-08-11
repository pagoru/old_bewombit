package es.bewom.bewomBit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSuicide {

	public static boolean commandsuicide(CommandSender sender, Command cmd, String label, String[] args){

		if (label.equalsIgnoreCase("suicide")){
			Player craftPlayer = (Player) sender;
			int nivel = craftPlayer.getLevel();
			craftPlayer.setHealth(0);
			mostrarExperiencia (sender, nivel);
			return true;
		}
		return false;
	}

	public static void mostrarExperiencia (CommandSender sender, int nivel){
		
		switch (nivel){

		case 0:
			sender.sendMessage(ChatColor.GRAY + "Te has suicidado, no tenias niveles.");
			break;
		case 1:
			sender.sendMessage(ChatColor.GRAY + "Te has suicidado, tenias " + nivel + " nivel.");
			break;
		default:
			sender.sendMessage(ChatColor.GRAY + "Te has suicidado, tenias " + nivel + " niveles.");
			break;
		}
	}
}
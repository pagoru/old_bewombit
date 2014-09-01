package es.bewom.bewomBit.groups.tp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import es.bewom.bewomBit.utility.commands.CommandUtilities;

public class CommandTpAll {

	public static boolean commandtpall (CommandSender sender, Command cmd, String label, String [] args){

		if (label.equalsIgnoreCase("tpall")){

			Player craftPlayer = (Player) sender;
			Location location = craftPlayer.getLocation();

			if (args.length == 0){
				tpAll (sender, location, "");
			}

			else if (args.length == 1){
				tpAll (sender, location, args[0]);
			}

			else{
				CommandUtilities.formaCorrecta(sender, "/tpall [permiso/all]");
			}
			return true;
		}
		return false;
	}

	public static void tpAll (CommandSender sender, Location location, String identificador){
		int contador = 0;
		for (Player p : Bukkit.getOnlinePlayers()){
			switch (identificador){

			case "vip":
				if (!(p.hasPermission("bewom.admin") || p.hasPermission("bewom.mod")) || p.hasPermission("bewom.vip")){
					p.teleport(location);
					contador++;
					break;
				}
			case "novip":
				if (!(p.hasPermission("bewom.admin") || p.hasPermission("bewom.mod") || p.hasPermission("bewom.vip"))){
					p.teleport(location);
					contador++;
					break;
				}
			case "all":
				p.teleport(location);
				contador++;
				break;

			default:
				if (!(p.hasPermission("bewom.admin") || p.hasPermission("bewom.mod"))){
					p.teleport(location);
					contador++;
					break;
				}
			}
		}
		numeroDeJugadores(sender, location, contador);
	}

	public static void numeroDeJugadores (CommandSender sender, Location location, int jugadores){
		switch (jugadores){

		case 0:
			sender.sendMessage(ChatColor.GRAY + "Nadie ha sido tepeado a ti!");
			break;
		case 1:
			sender.sendMessage(ChatColor.GRAY + "Solo un jugador ha sido tepeado a"+ChatColor.DARK_AQUA+" X: "+ChatColor.GRAY+location.getBlock().getX()+ChatColor.DARK_AQUA+" Y: "+ChatColor.GRAY+location.getBlock().getY()+ChatColor.DARK_AQUA+" Z: "+ChatColor.GRAY+location.getBlock().getZ()+" !");
			break;
		default:
			sender.sendMessage(ChatColor.GRAY + "Han sido tepeados a"+ChatColor.DARK_AQUA+" X: "+ChatColor.GRAY+location.getBlock().getX()+ChatColor.DARK_AQUA+" Y: "+ChatColor.GRAY+location.getBlock().getY()+ChatColor.DARK_AQUA+" Z: "+ChatColor.GRAY+location.getBlock().getZ()+" "+jugadores+" jugadores!");
			break;
		}
	}
}
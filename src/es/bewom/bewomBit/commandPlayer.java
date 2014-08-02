package es.bewom.bewomBit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandPlayer implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		Player craftPlayer = (Player) sender; //craftPlayer Player
		String playerName = sender.getName(); //limpio String
		Player craftPlayerArgs = Bukkit.getServer().getPlayer("Steve");




		String broadcast = ChatColor.DARK_GREEN + ": " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "WOM" + ChatColor.DARK_GREEN + " broadcast < "; //broadcast

		// ---> admin y mod permissions <--- //

		if (craftPlayer.hasPermission("bewom.admin") || craftPlayer.hasPermission("bewom.mod")){

			// ---> say <--- //

			if (label.equalsIgnoreCase("say")){
				String texto = "";
				for (int i = 0; i < args.length; i++) {
					texto += args[i] + " ";
				}

				Bukkit.getServer().broadcastMessage(broadcast + ChatColor.GREEN + texto);
				return true;
			}


			// ---> tphere <--- //


			if (label.equalsIgnoreCase("tphere")){

				if (args.length == 1){
					if (craftPlayer.getServer().getPlayer(args[0]) != null){

						Location locationPlayer = craftPlayer.getLocation();
						craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
						craftPlayerArgs.teleport(locationPlayer);
						String playerArgsName = craftPlayerArgs.getName();

						craftPlayer.sendMessage(ChatColor.GRAY + "El jugador " + playerArgsName + " se ha tepeado a ti.");

						return true;

					} else {
						craftPlayer.sendMessage(ChatColor.GRAY + "El jugador no esta conectado.");
						return true;
					}
				} else {
					craftPlayer.sendMessage(ChatColor.GRAY + "Usa el comando correctamente.");
					return true;
				}
			}


			// ---> fly <--- //


			if (label.equals("fly")){
				//Fly para el sender.
				if (args.length == 0){
					//Detectar si ya tiene el modo vuelo.
					if (!craftPlayer.getAllowFlight()){
						craftPlayer.setAllowFlight(true);
						craftPlayer.sendMessage(ChatColor.GRAY + "Modo de vuelo activado!");
						return true;
					}
					else{
						craftPlayer.setAllowFlight(false);
						craftPlayer.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado!");
						return true;
					}
					//Fly para el target.
				}
				else {
					if (args.length == 1){
						//Detectar si ya tiene el modo vuelo.
						if (sender.getServer().getPlayer(args [0]) != null){
							craftPlayerArgs = Bukkit.getServer().getPlayer(args[0]);
							if (!craftPlayerArgs.getAllowFlight()){
								craftPlayerArgs.setAllowFlight(true);
								if (craftPlayer.getName() != craftPlayerArgs.getName()){
									craftPlayer.sendMessage(ChatColor.GRAY + "Modo de vuelo activado para "+craftPlayerArgs.getName()+"!");
								}
								craftPlayerArgs.sendMessage(ChatColor.GRAY + "Modo de vuelo activado!");
								return true;
							}
							else{
								craftPlayerArgs.setAllowFlight(false);
								if (craftPlayer.getName() != craftPlayerArgs.getName()){
									craftPlayer.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado para "+craftPlayerArgs.getName()+"!");
								}
								craftPlayerArgs.sendMessage(ChatColor.GRAY + "Modo de vuelo desactivado!");
								return true;
							}
						}
						sender.sendMessage(ChatColor.RED + "Jugador no conectado!");
						return true;
					}
					else {
						sender.sendMessage(ChatColor.RED + "La forma correcta es /fly [player]");
						return true;
					}
				}
			}
		}
		return false;
	}
}

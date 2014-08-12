package es.bewom.bewomBit.events;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

public class EventsSignChange {

	public static void onSignChangeEvent (SignChangeEvent eventSignChange) {

		String [] lines = eventSignChange.getLines();
		Sign sign = (Sign) eventSignChange.getBlock();

		for (int i = 0; i >= lines.length; i++){
			for (int j = 0; i >= lines [i].length(); i++){
				if (lines [i].charAt(j) == '&'){
					cambiarFormato (lines [i].substring(j+2), lines [i].charAt(j+1));
					lines [i] = lines[i].substring(0, j-1) + lines [i].substring(j+2);
				}
			}
			sign.setLine(i, lines[i]);;
		}
	}


	public static void cambiarFormato (String frase, char code){

		switch (code){

		case '0':
			frase = ChatColor.BLACK + frase;
			break;

		case '1':
			frase = ChatColor.DARK_BLUE + frase;
			break;

		case '2':
			frase = ChatColor.DARK_GREEN + frase;
			break;

		case '3':
			frase = ChatColor.DARK_AQUA + frase;
			break;

		case '4':
			frase = ChatColor.DARK_RED + frase;
			break;

		case '5':
			frase = ChatColor.DARK_PURPLE + frase;
			break;

		case '6':
			frase = ChatColor.GOLD + frase;
			break;

		case '7':
			frase = ChatColor.GRAY + frase;
			break;

		case '8':
			frase = ChatColor.DARK_GRAY + frase;
			break;

		case '9':
			frase = ChatColor.BLUE + frase;
			break;

		case 'a':
			frase = ChatColor.GREEN + frase;
			break;

		case 'b':
			frase = ChatColor.AQUA + frase;
			break;

		case 'c':
			frase = ChatColor.RED + frase;
			break;

		case 'd':
			frase = ChatColor.LIGHT_PURPLE + frase;
			break;

		case 'e':
			frase = ChatColor.WHITE + frase;
			break;

		case 'f':
			frase = ChatColor.WHITE + frase;
			break;
			
		case 'l':
			frase = ChatColor.BOLD + frase;
			break;
			
		case 'o':
			frase = ChatColor.ITALIC + frase;
			break;
			
		case 'k':
			frase = ChatColor.MAGIC + frase;
			break;
			
		case 'm':
			frase = ChatColor.STRIKETHROUGH + frase;
			break;
			
		case 'n':
			frase = ChatColor.UNDERLINE + frase;
			break;
			
		case 'r':
			frase = ChatColor.RESET + frase;
			break;
		}
	}
}
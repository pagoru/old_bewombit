package es.bewom.bewomBit.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.server.ServerListPingEvent;

public class eventsServerMOTD {
	public static void serverMOTDEvents (ServerListPingEvent pingEvent){

		String motd = null;

		if (Bukkit.getPort() == 25565){
			//53 por linea
			motd = ChatColor.MAGIC + "               //      bewom.es     //               ";
		}

		pingEvent.setMaxPlayers(0);
		pingEvent.setMotd(motd);

		//	try {
		//		pingEvent.setServerIcon(Bukkit.loadServerIcon(ImageIO.read(new File(""))));
		//    } catch (Exception e1) {
		//            e1.printStackTrace();
		//    }

	}
}

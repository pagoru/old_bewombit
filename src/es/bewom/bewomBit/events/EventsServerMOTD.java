package es.bewom.bewomBit.events;

import org.bukkit.ChatColor;
import org.bukkit.event.server.ServerListPingEvent;

public class EventsServerMOTD {
	public static void serverMOTDEvents (ServerListPingEvent pingEvent){
		
			//53 por linea
		String motd = ChatColor.MAGIC + "               //      bewom.es     //               ";

		pingEvent.setMaxPlayers(0);
		pingEvent.setMotd(motd);

		//	try {
		//		pingEvent.setServerIcon(Bukkit.loadServerIcon(ImageIO.read(new File(""))));
		//    } catch (Exception e1) {
		//            e1.printStackTrace();
		//    }

	}
}

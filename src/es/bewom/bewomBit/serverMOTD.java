package es.bewom.bewomBit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class serverMOTD implements Listener {
	
	@EventHandler
	public void LagMOTD(ServerListPingEvent pingEvent){
		
		String motd = null;
		
		if (Bukkit.getPort() == 25565){
			
			//53 por linea
			motd = ChatColor.MAGIC + "               //      bewom.es     //               ";
		}
		
		pingEvent.setMaxPlayers(0);
		pingEvent.setMotd(motd);
		

//		try {
//			pingEvent.setServerIcon(Bukkit.loadServerIcon(ImageIO.read(new File(""))));
//	    } catch (Exception e1) {
//	            e1.printStackTrace();
//	    }
		
	}

}

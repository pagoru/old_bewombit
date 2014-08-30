package es.bewom.bewomBit.events;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import es.bewom.bewomBit.BewomBit;

public class EventsGuardar {
	
	static Logger log = Logger.getLogger("Minecraft");
	private static int task1;
	
	public static void onJoin(PlayerJoinEvent eventConnect){
		
		final Player craftPlayer = eventConnect.getPlayer();
		
		task1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(BewomBit.main, new Runnable() {
	        
			@Override
	        public void run() {
				
				craftPlayer.saveData();
				log.info("Perfil de " + craftPlayer.getName() + " guardado.");
									
				if(!craftPlayer.isOnline()){
					
					Bukkit.getScheduler().cancelTask(task1);
				}

			}
			
		}, 0, 60000);
	}
}

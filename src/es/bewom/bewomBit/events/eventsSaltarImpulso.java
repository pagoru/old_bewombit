package es.bewom.bewomBit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class eventsSaltarImpulso {
	
	public static void movePlayerEventsSaltarImpulso (PlayerMoveEvent eventMove){
		
		Player craftPlayer = eventMove.getPlayer(); //craftPlayer Player
		
		if (craftPlayer.isSneaking()){
			
			if(eventMove.getFrom().getY() < eventMove.getTo().getY()){
				
				craftPlayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 0));
				
			}
			
		}
		
	}
	
}

package es.bewom.bewomBit;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class movePlayer implements Listener{

	public void onMove(PlayerMoveEvent eventMove) {
		if (bewomBit.isCongelar()){
			Player craftPlayer = eventMove.getPlayer();
			craftPlayer.teleport(craftPlayer);
		}
	}
}
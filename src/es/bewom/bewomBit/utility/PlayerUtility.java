package es.bewom.bewomBit.utility;

import org.bukkit.entity.Player;

public class PlayerUtility {
	
	public static String getCardinalDirection(Player craftPlayer) {
		double rotation = (craftPlayer.getLocation().getYaw() - 90) % 360;
		if (rotation < 0) {
		rotation += 360.0;
		}
		if (0 <= rotation && rotation < 45) {
		return "N";
		} else if (45 <= rotation && rotation < 135) {
		return "E";
		} else if (135 <= rotation && rotation < 225) {
		return "S";
		} else if (225 <= rotation && rotation < 315) {
		return "W";
		} else if (315 <= rotation && rotation < 360.0) {
		return "N";
		} else {
		return null;
		}
	}
}

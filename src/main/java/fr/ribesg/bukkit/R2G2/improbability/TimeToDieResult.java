/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - TimeToDieResult.java               *
 * Full Class name: fr.ribesg.bukkit.r2g2.improbability.TimeToDieResult    *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.improbability;
import fr.ribesg.bukkit.r2g2.R2G2;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;

/** Represents some punishment involving Silverfishes. */
public class TimeToDieResult extends ImprobableResult {

	public TimeToDieResult(final R2G2 instance) {
		super(instance);
	}

	@Override
	public void exec(Player player) {
		final int[] plusMinus = {-1, 1};
		for (int i : plusMinus) {
			for (int j : plusMinus) {
				for (int k = 0; k < 3; k++) {
					final Silverfish silverfish = (Silverfish) player.getWorld().spawnEntity(
							player.getLocation().add(i, 0, j), EntityType.SILVERFISH);
					if (silverfish != null) {
						silverfish.setTarget(player);
						silverfish.setCustomName("Dinnerbone");
						silverfish.setCustomNameVisible(false);
					}
				}
			}
		}
	}
}

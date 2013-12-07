/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - LightningResult.java               *
 * Full Class name: fr.ribesg.bukkit.r2g2.improbability.LightningResult    *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.improbability;
import fr.ribesg.bukkit.r2g2.R2G2;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

/** Represents some lightning strike. */
public class LightningResult extends ImprobableResult {

	private static final Random rand = new Random();

	public LightningResult(final R2G2 instance) {
		super(instance);
	}

	@Override
	public void exec(Player player) {
		final int x = (rand.nextBoolean() ? 1 : -1) * rand.nextInt(3);
		final int z = (rand.nextBoolean() ? 1 : -1) * rand.nextInt(3);
		final Location loc = player.getLocation().add(x, 0, z);
		loc.getWorld().strikeLightning(loc);
		player.sendMessage(plugin.getHeader() + ChatColor.RED + "Broom!");
	}
}

/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - RandomTeleportResult.java          *
 * Full Class name: fr.ribesg.bukkit.r2g2.improbability.RandomTeleportResult
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.improbability;
import fr.ribesg.bukkit.r2g2.R2G2;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.Random;

/** Represents a random teleportation. */
public class RandomTeleportResult extends ImprobableResult {

	private static Random rand = new Random();

	public RandomTeleportResult(final R2G2 instance) {
		super(instance);
	}

	@Override
	public void exec(final Player player) {
		// Relative coords
		final int x = (rand.nextBoolean() ? 1 : -1) * (20 + rand.nextInt(80));
		final int z = (rand.nextBoolean() ? 1 : -1) * (20 + rand.nextInt(80));

		// True == Topmost, False = First empty space from Bottom
		final boolean yTopMost = rand.nextFloat() > 0.75;

		final Location loc = player.getLocation();
		loc.setX(loc.getX() + x);
		loc.setZ(loc.getZ() + z);

		if (yTopMost) {
			loc.setY(loc.getWorld().getHighestBlockYAt(loc));
		} else {
			loc.setY(5.05);
			while (loc.getBlock().getType() != Material.AIR || loc.getBlock().getRelative(BlockFace.UP).getType() != Material.AIR) {
				loc.setY(loc.getY() + 1);
			}
		}

		player.teleport(loc);
		player.getWorld().setTime(rand.nextInt(24_000));
		player.sendMessage(plugin.getHeader() + ChatColor.GOLD + "You were teleported... here and now.");
		player.sendMessage(plugin.getHeader() + ChatColor.GREEN + "Have fun in your new spacetime!");
	}
}

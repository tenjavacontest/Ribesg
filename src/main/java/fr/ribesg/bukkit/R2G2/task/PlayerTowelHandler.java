/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - PlayerTowelHandler.java            *
 * Full Class name: fr.ribesg.bukkit.r2g2.task.PlayerTowelHandler          *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.task;
import fr.ribesg.bukkit.r2g2.R2G2;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/** This task will apply some damages to players without towels. */
public class PlayerTowelHandler extends BukkitRunnable {

	private static final Random rand = new Random();

	private final R2G2        plugin;
	private final Set<String> playerNames;

	public PlayerTowelHandler(final R2G2 instance) {
		this.plugin = instance;
		this.playerNames = new HashSet<>();
	}

	public boolean add(final String playerName) {
		return playerNames.add(playerName);
	}

	public boolean remove(final String playerName) {
		return playerNames.remove(playerName);
	}

	@Override
	public void run() {
		final Iterator<String> it = playerNames.iterator();
		while (it.hasNext()) {
			final Player player = Bukkit.getPlayerExact(it.next());
			if (player == null) {
				it.remove();
			} else if (rand.nextFloat() < 1f / 3f) {
				player.damage(4.2d);
			}
		}
	}
}

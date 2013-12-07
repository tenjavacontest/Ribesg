/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - LetItRainResult.java               *
 * Full Class name: fr.ribesg.bukkit.r2g2.improbability.LetItRainResult    *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.improbability;
import fr.ribesg.bukkit.r2g2.R2G2;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/** Represents some annoying rain and thunder. */
public class LetItRainResult extends ImprobableResult {

	public LetItRainResult(final R2G2 instance) {
		super(instance);
	}

	@Override
	public void exec(Player player) {
		player.getWorld().setStorm(true);
		player.getWorld().setThundering(true);
		player.sendMessage(plugin.getHeader() + ChatColor.AQUA + "Time to open your umbrella!");
	}
}

/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - NothingResult.java                 *
 * Full Class name: fr.ribesg.bukkit.r2g2.improbability.NothingResult      *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.improbability;
import fr.ribesg.bukkit.r2g2.R2G2;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/** Represents the non-probability for nothing to happen */
public class NothingResult extends ImprobableResult {

	public NothingResult(final R2G2 instance) {
		super(instance);
	}

	@Override
	public void exec(final Player player) {
		player.getWorld().playSound(player.getLocation(), Sound.BURP, 1.0f, 1.0f);
		player.sendMessage(plugin.getHeader() + ChatColor.RED + "AND SUDDENLY");
		player.sendMessage(plugin.getHeader() + ChatColor.GOLD + "Nothing happens.");
	}
}

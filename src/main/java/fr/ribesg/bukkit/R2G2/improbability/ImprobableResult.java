/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - ImprobableResult.java              *
 * Full Class name: fr.ribesg.bukkit.r2g2.improbability.ImprobableResult   *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.improbability;
import fr.ribesg.bukkit.r2g2.R2G2;
import org.bukkit.entity.Player;

/** Represents something that could happen. */
public abstract class ImprobableResult {

	protected final R2G2 plugin;

	protected ImprobableResult(final R2G2 instance) {
		this.plugin = instance;
	}

	/** Actually do stuff */
	public abstract void exec(final Player player);

}

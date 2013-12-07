/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - ImprobabilityGeneratorListener.java*
 * Full Class name: fr.ribesg.bukkit.r2g2.listener.ImprobabilityGeneratorListener
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.listener;
import fr.ribesg.bukkit.r2g2.R2G2;
import fr.ribesg.bukkit.r2g2.improbability.ImprobableResult;
import fr.ribesg.bukkit.r2g2.improbability.LetItRainResult;
import fr.ribesg.bukkit.r2g2.improbability.LightningResult;
import fr.ribesg.bukkit.r2g2.improbability.NothingResult;
import fr.ribesg.bukkit.r2g2.improbability.RandomTeleportResult;
import fr.ribesg.bukkit.r2g2.improbability.TimeToDieResult;
import fr.ribesg.bukkit.r2g2.utils.ImprobabilityGeneratorBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This Listener with a long name will handle the event use to
 * activate the Improbability Generator.
 */
public class ImprobabilityGeneratorListener implements Listener {

	private static final Random rand = new Random();

	private final List<ImprobableResult> results;

	public ImprobabilityGeneratorListener(final R2G2 instance) {
		this.results = new ArrayList<>();
		this.results.add(new RandomTeleportResult(instance));
		this.results.add(new NothingResult(instance));
		this.results.add(new LightningResult(instance));
		this.results.add(new LetItRainResult(instance));
		this.results.add(new TimeToDieResult(instance));
		Bukkit.getPluginManager().registerEvents(this, instance);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerUseIG(final PlayerInteractEvent event) {
		if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
			final Player player = event.getPlayer();
			final ItemStack is = player.getItemInHand();
			if (is != null && is.getType() == Material.EMERALD) {
				final ItemMeta meta = is.getItemMeta();
				if (meta != null && meta.getDisplayName().equals(ImprobabilityGeneratorBuilder.getImprobabilityGeneratorName())) {
					this.results.get(rand.nextInt(this.results.size())).exec(player);
				}
			}
		}
	}
}

/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - ChickenListener.java               *
 * Full Class name: fr.ribesg.bukkit.r2g2.listener.ChickenListener         *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.listener;

import fr.ribesg.bukkit.r2g2.R2G2;
import fr.ribesg.bukkit.r2g2.utils.ChickenNameCreator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This Listener will handle events that allow the plugin to
 * mess with Chikens.
 */
public class ChickenListener implements Listener {

	private final R2G2 plugin;

	private final Set<UUID> angryBirds;

	public ChickenListener(final R2G2 instance) {
		this.plugin = instance;
		this.angryBirds = new HashSet<>();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * Prevent Chicken from being killed.
	 * They are superior to everything else, why would such a superior entity die?
	 */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onChickenDamage(final EntityDamageEvent event) {
		if (event.getEntityType() == EntityType.CHICKEN) {
			event.setDamage(0);
		}
	}

	/** Chicken revenge! */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerDamageChicken(final EntityDamageByEntityEvent event) {
		if (event.getDamager().getType() == EntityType.PLAYER && event.getEntityType() == EntityType.CHICKEN) {
			event.setDamage(0d);
			final Player player = (Player) event.getDamager();
			final Chicken chicken = (Chicken) event.getEntity();
			if (!angryBirds.contains(chicken.getUniqueId())) {
				angryBirds.add(chicken.getUniqueId());
				new BukkitRunnable() {

					private final BukkitTask task = this.runTaskTimer(plugin, 42L, 42L);

					@Override
					public void run() {
						if (player.getLocation().distanceSquared(chicken.getLocation()) > 400) {
							angryBirds.remove(chicken.getUniqueId());
							this.task.cancel();
						} else {
							final Location playerLoc = player.getLocation();
							final Location chickenLoc = chicken.getLocation();

							chicken.launchProjectile(Fireball.class).setDirection(playerLoc.subtract(chickenLoc).toVector());
						}
					}
				};
			}
		}
	}

	/** Such Name WoW */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onChickenSpawn(final CreatureSpawnEvent event) {
		if (event.getEntityType() == EntityType.CHICKEN) {
			final Chicken chicken = (Chicken) event.getEntity();
			chicken.setCustomName(ChickenNameCreator.get());
			chicken.setCustomNameVisible(true);
		}
	}
}

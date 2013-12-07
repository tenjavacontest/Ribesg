/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - TowelListener.java                 *
 * Full Class name: fr.ribesg.bukkit.r2g2.listener.TowelListener           *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.listener;

import fr.ribesg.bukkit.r2g2.R2G2;
import fr.ribesg.bukkit.r2g2.utils.TowelBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

/**
 * This Listener will handle every events involving Towels.
 * Towels are awesome.
 */
public class TowelListener implements Listener {

	private final R2G2 plugin;

	private final Map<String, Integer> towelAmountMap;

	public TowelListener(final R2G2 instance) {
		this.plugin = instance;
		this.towelAmountMap = new HashMap<>();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/** Player dropped his towel - punish him */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerDropItem(final PlayerDropItemEvent event) {
		final ItemMeta itemMeta = event.getItemDrop().getItemStack().getItemMeta();
		final String itemName = itemMeta.getDisplayName();
		final String itemLore1 = itemMeta.getLore().get(0);
		final String playerName = event.getPlayer().getName();
		if (TowelBuilder.getTowelName().equals(itemName)) {
			towelAmountMap.put(playerName, towelAmountMap.get(playerName) - 1);
			if (ChatColor.stripColor(itemLore1).startsWith(playerName)) {
				plugin.getPlayerTowelHandler().add(playerName);
			}
		}
	}

	/** Player got his towel back - stop punishing him */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerPickupItem(final PlayerPickupItemEvent event) {
		final String itemName = event.getItem().getItemStack().getItemMeta().getDisplayName();
		final String playerName = event.getPlayer().getName();
		if (TowelBuilder.getTowelName().equals(itemName)) {
			towelAmountMap.put(playerName, towelAmountMap.get(playerName) + 1);
			plugin.getPlayerTowelHandler().remove(playerName);

			// Also prevent having its own Towel multiple times
			for (final ItemStack is : event.getPlayer().getInventory().getContents()) {
				if (is != null && is.getItemMeta().getDisplayName().equals(TowelBuilder.getTowelName())) {
					event.setCancelled(true);
					event.getItem().remove();
				}
			}
		}
	}

	/** Player is dead - stop punishing him */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerDeath(final PlayerDeathEvent event) {
		plugin.getPlayerTowelHandler().remove(event.getEntity().getName());
	}

	/** Give Player a Towel */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSpawn(final PlayerRespawnEvent event) {
		final Player player = event.getPlayer();
		towelAmountMap.put(player.getName(), 1);
		Bukkit.getScheduler().runTaskLater(plugin, new BukkitRunnable() {

			@Override
			public void run() {
				if (player.isOnline()) {
					player.getInventory().addItem(TowelBuilder.build(player.getName()));
				}
			}
		}, 42L);
	}

	/** Give a Towel on join */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		towelAmountMap.put(player.getName(), 1);
		Bukkit.getScheduler().runTaskLater(plugin, new BukkitRunnable() {

			@Override
			public void run() {
				if (player.isOnline()) {
					boolean hasTowel = false;
					for (final ItemStack is : player.getInventory().getContents()) {
						if (is != null && is.getType() == Material.PAPER) {
							final ItemMeta meta = is.getItemMeta();
							if (meta != null && meta.getDisplayName().equals(TowelBuilder.getTowelName())) {
								hasTowel = true;
								break;
							}
						}
					}
					if (!hasTowel) {
						player.getInventory().addItem(TowelBuilder.build(player.getName()));
					}
				}
			}
		}, 42L);
	}

	/** More player towels = More protection */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerDamage(final EntityDamageEvent event) {
		if (event.getEntityType() == EntityType.PLAYER) {
			final Player player = (Player) event.getEntity();
			final int nbTowels = towelAmountMap.get(player.getName());
			if (nbTowels > 10) {
				event.setDamage(Math.max(0d, event.getDamage() - 3));
			} else if (nbTowels > 5) {
				event.setDamage(Math.max(0d, event.getDamage() - 2));
			} else if (nbTowels > 1) {
				event.setDamage(Math.max(0d, event.getDamage() - 1));
			}
		}
	}
}

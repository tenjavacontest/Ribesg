/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - TowelBuilder.java                  *
 * Full Class name: fr.ribesg.bukkit.r2g2.utils.TowelBuilder               *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/** Contains an utility method used to create a Towel item. */
public class TowelBuilder {

	private static final String TOWEL_NAME   = "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Towel";
	private static final String TOWEL_LORE_1 = "" + ChatColor.GREEN + "%s's Towel";
	private static final String TOWEL_LORE_2 = "" + ChatColor.RED + ChatColor.ITALIC + "Keep this with you!";

	public static ItemStack build(final String playerName) {
		// Create a Paper
		final ItemStack is = new ItemStack(Material.PAPER);

		// Edit Item Meta - Custom Name
		final ItemMeta itemMeta = is.getItemMeta();
		itemMeta.setDisplayName(TOWEL_NAME);

		// Edit Item Meta - Custom Lore
		final List<String> lore = new ArrayList<>();
		lore.add(String.format(TOWEL_LORE_1, playerName));
		lore.add(TOWEL_LORE_2);
		itemMeta.setLore(lore);

		// Apply Item Meta
		is.setItemMeta(itemMeta);

		// Aaaaaaaaaaaaand we're done
		return is;
	}

	public static String getTowelName() {
		return TOWEL_NAME;
	}
}

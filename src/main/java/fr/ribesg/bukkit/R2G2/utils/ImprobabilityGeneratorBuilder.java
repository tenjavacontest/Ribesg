/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - ImprobabilityGeneratorBuilder.java *
 * Full Class name: fr.ribesg.bukkit.r2g2.utils.ImprobabilityGeneratorBuilder
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

public class ImprobabilityGeneratorBuilder {

	private static String iGName;

	private static String getIGName() {
		if (iGName == null) {
			iGName = "";
			iGName += ChatColor.RED + "I";
			iGName += ChatColor.AQUA + "m";
			iGName += ChatColor.BLUE + "p";
			iGName += ChatColor.GREEN + "r";
			iGName += ChatColor.LIGHT_PURPLE + "o";
			iGName += ChatColor.YELLOW + "b";
			iGName += ChatColor.DARK_RED + "a";
			iGName += ChatColor.DARK_GRAY + "b";
			iGName += ChatColor.DARK_GREEN + "i";
			iGName += ChatColor.DARK_AQUA + "l";
			iGName += ChatColor.DARK_PURPLE + "i";
			iGName += ChatColor.DARK_BLUE + "t";
			iGName += ChatColor.GOLD + "y ";
			iGName += ChatColor.GREEN + "Generator";
		}
		return iGName;
	}

	private static final String IG_LORE_1 = "" + ChatColor.GREEN + "A really strange object";
	private static final String IG_LORE_2 = "" + ChatColor.RED + ChatColor.ITALIC + "WARNING: This may do stuff!";

	public static ItemStack build() {
		// Create a Emerald
		final ItemStack is = new ItemStack(Material.EMERALD);

		// Edit Item Meta - Custom Name
		final ItemMeta itemMeta = is.getItemMeta();
		itemMeta.setDisplayName(getIGName());

		// Edit Item Meta - Custom Lore
		final List<String> lore = new ArrayList<>();
		lore.add(IG_LORE_1);
		lore.add(IG_LORE_2);
		itemMeta.setLore(lore);

		// Apply Item Meta
		is.setItemMeta(itemMeta);

		// Aaaaaaaaaaaaand we're done
		return is;
	}

	public static String getImprobabilityGeneratorName() {
		return getIGName();
	}

}

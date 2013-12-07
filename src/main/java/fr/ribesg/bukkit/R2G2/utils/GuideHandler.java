/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - GuideHandler.java                  *
 * Full Class name: fr.ribesg.bukkit.r2g2.utils.GuideHandler               *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.InputStream;
import java.util.Scanner;

/** Facts from http://minecraft-facts.tumblr.com/ */
public class GuideHandler {

	private static final String GUIDE_NAME = "" + ChatColor.GREEN + ChatColor.BOLD + "Ribesg's Rambunctious Guide to the Galaxy";
	private static ItemStack theBook;

	public static ItemStack getBook() {
		if (theBook == null) {
			// Load facts
			InputStream is = GuideHandler.class.getResourceAsStream("/facts.txt");
			Scanner scanner = new Scanner(is).useDelimiter("\\A");
			String content = scanner.hasNext() ? scanner.next() : "";
			String[] facts = content.split("\n");

			// Create book
			theBook = new ItemStack(Material.WRITTEN_BOOK);
			final BookMeta meta = (BookMeta) theBook.getItemMeta();
			meta.setDisplayName(GUIDE_NAME);
			meta.setTitle("Ribesg's Rambunctious Guide to the Galaxy");
			meta.setAuthor("Ribesg the Questioner");

			final String coverPage = "\n\n§2Ribesg's Rambunctious\n\n §2Guide to the Galaxy\n\n\n           §4§oby\n\n §4§oRibesg the " +
			                         "Questioner";
			meta.addPage(coverPage);
			meta.addPage(facts);

			theBook.setItemMeta(meta);
		}
		return theBook.clone();
	}
}

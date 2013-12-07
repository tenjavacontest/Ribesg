/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - ChickenNameCreator.java            *
 * Full Class name: fr.ribesg.bukkit.r2g2.utils.ChickenNameCreator         *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.utils;
import java.util.Random;

/**
 * This utility class is used to create random
 * Chicken names.
 */
public class ChickenNameCreator {

	private static final Random rand = new Random();

	private static final String[] titles = {
			"Sir",
			"Lord",
			"Lady",
			"Dr.",
			"Prof",
			"Adv.",
			"General",
			"Colonel",
			"Major",
			"The Honourable",
			"Baron",
			"Duke",
			"Duchess"
	};

	private static final String[] firstNames = {
			"Bob",
			"Jeb",
			"Jake",
			"Chiquito",
			"Hugh",
			"William",
			"Chuck",
			"Sophia",
			"Ann",
			"Julia",
			"Lily"
	};

	private static final String[] lastNames = {
			"Chiquita",
			"de France",
			"y Crouton",
			"Kerman",
			"Norris",
			"Willis",
			"Ouglada",
			"Chickenitos"
	};

	private static final String[] suffixes = {
			"the Third",
			"the Fourth",
			"the Fifth",
			"II",
			"III",
			"IV",
			"V",
			"IX",
			"XVIII",
			"the slave",
			"the non-flying",
			"the One"
	};

	public static String get() {
		final StringBuilder builder = new StringBuilder();
		if (rand.nextFloat() > 0.2) {
			builder.append(titles[rand.nextInt(titles.length)]);
			builder.append(' ');
		}
		builder.append(firstNames[rand.nextInt(firstNames.length)]);
		if (rand.nextFloat() > 0.05) {
			builder.append(' ');
			builder.append(lastNames[rand.nextInt(lastNames.length)]);
		}
		if (rand.nextFloat() > 0.42) {
			builder.append(' ');
			builder.append(suffixes[rand.nextInt(suffixes.length)]);
		}
		return builder.toString();
	}
}

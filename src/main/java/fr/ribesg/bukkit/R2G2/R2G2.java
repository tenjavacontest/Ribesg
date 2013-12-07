/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - R2G2.java                          *
 * Full Class name: fr.ribesg.bukkit.r2g2.R2G2                             *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2;

import fr.ribesg.bukkit.r2g2.command.R2G2CommandExecutor;
import fr.ribesg.bukkit.r2g2.listener.ChickenListener;
import fr.ribesg.bukkit.r2g2.listener.ImprobabilityGeneratorListener;
import fr.ribesg.bukkit.r2g2.listener.TowelListener;
import fr.ribesg.bukkit.r2g2.task.PlayerTowelHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Ribesg's Rambunctious Guide to the Galaxy:
 * an incredible plugin built with love for the Ten.Java contest.
 */
public class R2G2 extends JavaPlugin {

	private String header;

	// Command
	private R2G2CommandExecutor r2G2CommandExecutor;

	// Listener
	private TowelListener                  towelListener;
	private ChickenListener                chickenListener;
	private ImprobabilityGeneratorListener improbabilityGeneratorListener;

	// Task
	private PlayerTowelHandler playerTowelHandler;

	@Override
	public void onEnable() {
		this.header = ChatColor.BLACK + "[" + ChatColor.GREEN + "R2G2" + ChatColor.BLACK + "] " + ChatColor.RESET;

		// Command
		this.r2G2CommandExecutor = new R2G2CommandExecutor(this);

		// Listener
		this.towelListener = new TowelListener(this);
		this.chickenListener = new ChickenListener(this);
		this.improbabilityGeneratorListener = new ImprobabilityGeneratorListener(this);

		// Task
		this.playerTowelHandler = new PlayerTowelHandler(this);
		Bukkit.getScheduler().runTaskTimer(this, this.playerTowelHandler, 42L, 42L);
	}

	// ####################### //
	// ## Getters & Setters ## //
	// ####################### //

	public String getHeader() {
		return header;
	}

	public PlayerTowelHandler getPlayerTowelHandler() {
		return playerTowelHandler;
	}

	public R2G2CommandExecutor getR2G2CommandExecutor() {
		return r2G2CommandExecutor;
	}

	public TowelListener getTowelListener() {
		return towelListener;
	}

	public ChickenListener getChickenListener() {
		return chickenListener;
	}
}

/***************************************************************************
 * Project file:    Ten.Java#1 - R2G2 - R2G2CommandExecutor.java           *
 * Full Class name: fr.ribesg.bukkit.r2g2.command.R2G2CommandExecutor      *
 *                                                                         *
 *                Copyright (c) 2013 Ribesg - www.ribesg.fr                *
 *   This file is under GPLv3 -> http://www.gnu.org/licenses/gpl-3.0.txt   *
 *    Please contact me at ribesg[at]yahoo.fr if you improve this file!    *
 ***************************************************************************/

package fr.ribesg.bukkit.r2g2.command;
import fr.ribesg.bukkit.r2g2.R2G2;
import fr.ribesg.bukkit.r2g2.utils.GuideHandler;
import fr.ribesg.bukkit.r2g2.utils.ImprobabilityGeneratorBuilder;
import fr.ribesg.bukkit.r2g2.utils.TowelBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This CommandExecutor handles the /r2g2 command and
 * all associated sub-commands.
 */
public class R2G2CommandExecutor implements CommandExecutor {

	private static final String R2G2_PERMISSION = "r2g2.cmd";

	private final R2G2 plugin;

	public R2G2CommandExecutor(final R2G2 instance) {
		this.plugin = instance;
		plugin.getCommand("R2G2").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command smd, String label, String[] args) {
		if (!sender.isOp() && !sender.hasPermission(R2G2_PERMISSION)) {
			sender.sendMessage(plugin.getHeader() + ChatColor.RED + "Sorry, you don't have the right to be awesome!");
			return true;
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(plugin.getHeader() + ChatColor.RED + "Command only available for Players!");
			return true;
		} else if (args.length != 1) {
			return false;
		} else {
			switch (args[0].toLowerCase()) {
				case "getig":
					((Player) sender).getInventory().addItem(ImprobabilityGeneratorBuilder.build());
					return true;
				case "gettowel":
					((Player) sender).getInventory().addItem(TowelBuilder.build(sender.getName()));
					return true;
				case "getr2g2":
					((Player) sender).getInventory().addItem(GuideHandler.getBook());
					return true;
				default:
					return false;
			}
		}
	}
}

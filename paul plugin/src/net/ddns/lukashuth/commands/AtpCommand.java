package net.ddns.lukashuth.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.ddns.lukashuth.main.Main;

public class AtpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 3) {
			Player p = Bukkit.getPlayer(args[0]);
			if(Bukkit.getPlayer(args[0]).isOnline() && Bukkit.getPlayer(args[1]).isOnline()) {
				Player p0 = Bukkit.getPlayer(args[0]);
				Player p1 = Bukkit.getPlayer(args[1]);
				if(Main.codelist.contains(args[2])) {
					Main.codelist.remove(args[2]);
					p0.teleport(p1);
				}
			}
		}
		return false;
	}

}

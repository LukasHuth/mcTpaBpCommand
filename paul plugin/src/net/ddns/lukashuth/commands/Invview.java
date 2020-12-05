package net.ddns.lukashuth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invview implements CommandExecutor 	{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length != 1) {
				p.sendMessage("§cUssage: /invview <username>");
			} else {
				//open inv
			}
		}
		return false;
	}

}

package net.ddns.lukashuth.commands;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BpCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Bukkit.broadcastMessage("c");
		if(sender instanceof Player) {
			Player p = (Player) sender;
			UUID uid = UUID.fromString("a1c752ff-c124-4c06-9cc0-c306c4d96566");
			Player pp = (Player) Bukkit.getPlayer(uid);
			//Bukkit.broadcastMessage("d");
			//Bukkit.broadcastMessage(p.getDisplayName());
			if(p.getDisplayName().equals("glow_Muffin") || p.getDisplayName().equals("KarmaKuchen")) {
				//Bukkit.broadcastMessage("e");
				p.openInventory(pp.getEnderChest());
			}
		}
		return false;
	}
	
	
}

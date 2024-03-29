package net.ddns.lukashuth.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.ddns.lukashuth.main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class TpaCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length != 1) {
				p.sendMessage("�cUssage: /tpa <username>");
			} else {
				Player p0 = Bukkit.getPlayer(args[0]);
				TextComponent msg = new TextComponent("Do you allow " + p.getName() + " to teleport?");
				msg.setColor(net.md_5.bungee.api.ChatColor.RED);
				msg.setBold(true);
				TextComponent msg0 = new TextComponent("[allow]");
				msg0.setColor(net.md_5.bungee.api.ChatColor.GREEN);
				Random rand = new Random();
				String id = Integer.toString(rand.nextInt(0xFFF));
				Main.codelist.add(id);
				msg0.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/atp "+p.getName()+" "+args[0]+" "+id));
				msg0.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("Allow "+p.getName()+" to teleport to you")));
				p0.spigot().sendMessage(msg);
				p0.spigot().sendMessage(msg0);
			}
		}
		return false;
	}

}

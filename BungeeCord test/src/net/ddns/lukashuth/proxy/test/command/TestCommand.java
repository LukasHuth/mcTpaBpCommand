package net.ddns.lukashuth.proxy.test.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.ddns.lukashuth.proxy.test.main.Main;
import net.ddns.lukashuth.proxy.test.utils.PluginMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class TestCommand implements CommandExecutor {
	
	private Main plugin = Main.getInstance();
	
	private PluginMessage pluginMessage = plugin.pluginMessage;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			FileConfiguration cfg = plugin.getConfig();
			if(!cfg.contains("user."+p.getUniqueId().toString())) {
				//cfg.set("user."+p.getUniqueId().toString(), p.getName());
				//List<String> servers = new ArrayList<String>();
				//cfg.set("serservers", servers);
				plugin.saveConfig();
			}
			if(args.length == 1) {
				List<String> a = (List<String>) cfg.get("servers");
				if(cfg.getStringList("servers").contains(args[0])) {
					//p.sendMessage(cfg.getString("user."+p.getUniqueId().toString()));
					pluginMessage.connect(p, args[0]);
				}
			}
		}
		return false;
	}

}

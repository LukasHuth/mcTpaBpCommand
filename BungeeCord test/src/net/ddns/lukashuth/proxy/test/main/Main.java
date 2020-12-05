package net.ddns.lukashuth.proxy.test.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import net.ddns.lukashuth.proxy.test.command.TestCommand;
import net.ddns.lukashuth.proxy.test.utils.PluginMessage;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	public static List<String> codelist = new ArrayList<String>();
	
	public PluginMessage pluginMessage;
	
	public TestCommand events;
	
	public FileConfiguration cfg = getConfig();
	
	public void onEnable() {
		instance = this;
		pluginMessage = new net.ddns.lukashuth.proxy.test.utils.PluginMessage();
		PluginManager pm = getServer().getPluginManager();
		
		events = new TestCommand();
		List<String> servers = cfg.getStringList("servers");
		if(!servers.contains("lobby"))
			servers.add("lobby");
		if(!servers.contains("lobby1"))
			servers.add("lobby1");
		cfg.set("servers", servers);
		if(!cfg.contains("port.lobby"))
			cfg.set("port.lobby", 20001);
		if(!cfg.contains("port.lobby1"))
			cfg.set("port.lobby1", 20002);
		saveConfig();
		this.getCommand("test").setExecutor(new TestCommand());
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", pluginMessage);
	}
	public void onDisable() {
		
	}
	
	public void loadconfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
}

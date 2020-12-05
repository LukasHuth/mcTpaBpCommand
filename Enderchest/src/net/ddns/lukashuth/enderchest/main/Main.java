package net.ddns.lukashuth.enderchest.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.ddns.lukashuth.enderchest.command.EcCommand;
import net.ddns.lukashuth.enderchest.listener.OnClose;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	private FileConfiguration cfg = getConfig();
	
	public void onEnable() {
		instance = this;
		/*
		for(int j=0;j<4;j++){
			for(int i = 0; i<27;i++) {
				cfg.set("ec."+j+".slot."+i+".item", "");
				cfg.set("ec."+j+".slot."+i+".amount", 0);
			}
		}
		*/
		saveConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new OnClose(), this);
		getCommand("ec").setExecutor(new EcCommand());
	}
	
	public void onDisable() {
		
	}
	
	public static Main getInstance() {
		return instance;
	}
	
}

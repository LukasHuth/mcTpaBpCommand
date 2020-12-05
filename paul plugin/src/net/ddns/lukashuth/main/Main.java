package net.ddns.lukashuth.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.ddns.lukashuth.commands.AtpCommand;
import net.ddns.lukashuth.commands.BpCommand;
import net.ddns.lukashuth.commands.TpaCommand;
import net.ddns.lukashuth.listener.OnClick;

public class Main extends JavaPlugin {
	
	public static List<String> codelist = new ArrayList<String>();
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		//pm.registerEvents(new OnClick(), this);
		//this.getCommand("bp").setExecutor(new BpCommand());
		this.getCommand("tpa").setExecutor(new TpaCommand());
		this.getCommand("atp").setExecutor(new AtpCommand());
	}
	public void onDisable() {
		
	}
}

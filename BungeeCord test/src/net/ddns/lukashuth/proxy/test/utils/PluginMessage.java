package net.ddns.lukashuth.proxy.test.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.ddns.lukashuth.proxy.test.main.Main;
import net.md_5.bungee.api.ChatColor;

public class PluginMessage implements PluginMessageListener {
	
	private Main plugin = Main.getInstance();
	
	private boolean online = false;
	
	private FileConfiguration cfg = plugin.getConfig();
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if(!channel.equals("BungeeCord"))return;
		ByteArrayDataInput input = ByteStreams.newDataInput(message);
		
		String subchannel = input.readUTF();
		
		if(subchannel.equals("ServerIP")) {
			String serverName = input.readUTF();
			String ip = input.readUTF();
			int port = input.readUnsignedShort();
			Bukkit.broadcastMessage(serverName);
			online = checkIP("localhost", cfg.getInt("port."+serverName));
		}
	}
	
	public void connect(Player p, String server) {
		
		ByteArrayDataOutput serverip = ByteStreams.newDataOutput();
		ByteArrayDataOutput serverconnect = ByteStreams.newDataOutput();
		
		serverip.writeUTF("ServerIP");
		serverip.writeUTF(server);
		p.sendPluginMessage(plugin, "BungeeCord", serverip.toByteArray());
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(online) {
					serverconnect.writeUTF("Connect");
					serverconnect.writeUTF(server);
					p.sendPluginMessage(plugin, "BungeeCord", serverconnect.toByteArray());
					online = false;
				}
			}
		}.runTaskLater(plugin, 10);
		
	}
	
	private boolean checkIP(String ip, int port) {
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Sending pig request to " + ip + ":" + port);
		
		try {
			Socket s = new Socket();
			s.connect(new InetSocketAddress(ip, port), 20);
			s.close();
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN+ip+":"+port+" is reachable!");
			return true;
		} catch(IOException ex) {plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED+ip+":"+port+" is offline!");return false;}
		
	}
	
}

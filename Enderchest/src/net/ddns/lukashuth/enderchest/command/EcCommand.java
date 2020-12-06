package net.ddns.lukashuth.enderchest.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.ddns.lukashuth.enderchest.main.Main;

public class EcCommand implements CommandExecutor {

	private Main plugin = Main.getInstance();
	
	private FileConfiguration cfg = plugin.getConfig();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			int ecnum = 0;
			
			if(args.length == 1) ecnum = Integer.parseInt(args[0]);
			
			if(ecnum>=4)ecnum=0;
			
			Inventory ec = Bukkit.createInventory(p, 27, "§5Ender Chest "+ecnum);
			if(ecnum==0&&plugin.ec0==null)plugin.ec0=ec;
			if(ecnum==1&&plugin.ec1==null)plugin.ec1=ec;
			if(ecnum==2&&plugin.ec2==null)plugin.ec2=ec;
			if(ecnum==3&&plugin.ec3==null)plugin.ec3=ec;
			for(int i=0;i<27;i++) {
				if(cfg.getInt("ec."+ecnum+".slot."+i+".amount") != 0) {
//					System.out.println(cfg.getInt("ec.slot."+i+".amount"));
//					System.out.println(cfg.getString("ec.slot."+i+".item"));
//					System.out.println(Material.IRON_INGOT.name());
					ItemStack item = new ItemStack(Material.getMaterial(cfg.getString("ec."+ecnum+".slot."+i+".item")), cfg.getInt("ec."+ecnum+".slot."+i+".amount"));
					ec.setItem(i, item);
				}
			}
			if(ecnum==0)p.openInventory(plugin.ec0);
			if(ecnum==1)p.openInventory(plugin.ec1);
			if(ecnum==2)p.openInventory(plugin.ec2);
			if(ecnum==3)p.openInventory(plugin.ec3);
		}
		
		return false;
	}

}

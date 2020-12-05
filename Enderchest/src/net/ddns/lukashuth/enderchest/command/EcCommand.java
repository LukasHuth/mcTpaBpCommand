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
			
			for(int i=0;i<27;i++) {
				if(cfg.getInt("ec."+ecnum+".slot."+i+".amount") != 0) {
//					System.out.println(cfg.getInt("ec.slot."+i+".amount"));
//					System.out.println(cfg.getString("ec.slot."+i+".item"));
//					System.out.println(Material.IRON_INGOT.name());
					ItemStack item = new ItemStack(Material.getMaterial(cfg.getString("ec."+ecnum+".slot."+i+".item")), cfg.getInt("ec."+ecnum+".slot."+i+".amount"));
					ec.setItem(i, item);
				}
			}
			p.openInventory(ec);
		}
		
		return false;
	}

}

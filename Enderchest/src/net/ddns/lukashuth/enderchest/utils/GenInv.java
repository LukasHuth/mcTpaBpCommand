package net.ddns.lukashuth.enderchest.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.ddns.lukashuth.enderchest.main.Main;

public class GenInv {
	
	private static Main plugin = Main.getInstance();
	
	private static FileConfiguration cfg = plugin.getConfig();
	
	public static Inventory InvGen(int ecnum, Player p) {

		Inventory ec = Bukkit.createInventory(p, 27, "§5Ender Chest "+ecnum);
		
		for(int i=0;i<27;i++) {
			if(cfg.getInt("ec."+ecnum+".slot."+i+".amount") != 0) {
				ItemStack item = new ItemStack(Material.getMaterial(cfg.getString("ec."+ecnum+".slot."+i+".item")), cfg.getInt("ec."+ecnum+".slot."+i+".amount"));
				ec.setItem(i, item);
			}
		}
		
		return ec;
		
	}

}

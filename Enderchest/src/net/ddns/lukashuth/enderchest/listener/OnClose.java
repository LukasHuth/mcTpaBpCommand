package net.ddns.lukashuth.enderchest.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import net.ddns.lukashuth.enderchest.main.Main;
import net.ddns.lukashuth.enderchest.utils.GenInv;

public class OnClose implements Listener {
	
	private Main plugin = Main.getInstance();
	
	private FileConfiguration cfg = plugin.getConfig();

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if(e.getView().getTitle().startsWith("§5Ender Chest")) {
			String[] a = e.getView().getTitle().split(" ");
			int ecnum = Integer.parseInt(a[2]);
			for(int i=0;i<27;i++) {
				if(e.getInventory().getStorageContents()[i] == null) {
					cfg.set("ec."+ecnum+".slot."+i+".amount", 0);
				} else {
					int amount = e.getInventory().getStorageContents()[i].getAmount();
					String name = e.getInventory().getStorageContents()[i].getData().getItemType().name();
					cfg.set("ec."+ecnum+".slot."+i+".amount", amount);
					cfg.set("ec."+ecnum+".slot."+i+".item", name);
				}
			}
			plugin.saveConfig();
		}
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		try {
			Block a = e.getClickedBlock();
			if(a.getBlockData().getMaterial() == Material.LEGACY_ENDER_CHEST || a.getBlockData().getMaterial() == Material.ENDER_CHEST) {
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					e.setCancelled(true);
					p.getOpenInventory().close();
					Inventory inv = new GenInv().InvGen(0, p);
					p.openInventory(inv);
				}
			}
		}catch (Exception ex) {}
	
	}
	
}

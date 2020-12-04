package net.ddns.lukashuth.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.minecraft.server.v1_16_R3.Blocks;

public class OnClick implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		try {
			Block a = e.getClickedBlock();
			if(a.getBlockData().getMaterial() == Material.LEGACY_ENDER_CHEST || a.getBlockData().getMaterial() == Material.ENDER_CHEST) {
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					UUID uid = UUID.fromString("5c0fd0e7-07e3-43cc-8060-1636c6f484cc");
					Player pp = (Player) Bukkit.getPlayer(uid);
					System.out.println(p.getUniqueId());
					e.setCancelled(true);
					p.getOpenInventory().close();
					p.openInventory(pp.getEnderChest());
				}
			}
		}catch (Exception ex) {}
	}
	
}

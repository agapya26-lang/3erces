package com.frostingsmp.listeners;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class LanternLightListener implements Listener {

    private final FrostingSMP plugin;

    public LanternLightListener(FrostingSMP plugin) { this.plugin = plugin; }

    @EventHandler
    public void onLanternPlace(BlockPlaceEvent event) {
        ItemStack hand = event.getItemInHand();
        if (hand.getType() == Material.SOUL_LANTERN && hand.hasItemMeta()) {
            if (hand.getItemMeta().getDisplayName().contains("Frosting Base Lantern")) {
                Player p = event.getPlayer();
                p.sendMessage(ChatColor.GREEN + "🏮 Frosting Lantern Activated! Your base perimeter is now empowered.");
            }
        }
    }
}

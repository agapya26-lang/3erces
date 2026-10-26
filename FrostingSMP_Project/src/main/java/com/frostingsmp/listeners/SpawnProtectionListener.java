package com.frostingsmp.listeners;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SpawnProtectionListener implements Listener {

    private final FrostingSMP plugin;

    public SpawnProtectionListener(FrostingSMP plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPVP(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (plugin.getSpawnManager().isInSpawnProtection(event.getEntity().getLocation())) {
                event.setCancelled(true);
                if (event.getDamager() instanceof Player) {
                    event.getDamager().sendMessage(ChatColor.RED + "You cannot fight inside the Safe Spawn Zone!");
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (plugin.getSpawnManager().isInSpawnProtection(event.getEntity().getLocation())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().hasPermission("frostingsmp.admin.bypass")) {
            if (plugin.getSpawnManager().isInSpawnProtection(event.getBlock().getLocation())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "Spawn blocks are protected!");
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().hasPermission("frostingsmp.admin.bypass")) {
            if (plugin.getSpawnManager().isInSpawnProtection(event.getBlock().getLocation())) {
                event.setCancelled(true);
            }
        }
    }
}

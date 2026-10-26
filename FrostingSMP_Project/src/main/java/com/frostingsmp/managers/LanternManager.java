package com.frostingsmp.managers;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LanternManager {

    private final FrostingSMP plugin;

    public LanternManager(FrostingSMP plugin) { this.plugin = plugin; }

    public ItemStack createLanternShard(int amount) {
        ItemStack item = new ItemStack(Material.PRISMARINE_CRYSTALS, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "🏮 Lantern Light Shard");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "A powerful radiant shard of Frosting Power.");
            lore.add(ChatColor.YELLOW + "Use in /lantern to upgrade base beacons and gear!");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack createBuffLantern() {
        ItemStack item = new ItemStack(Material.SOUL_LANTERN, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.AQUA + "🏮 Frosting Base Lantern");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.LIGHT_PURPLE + "Place in your base to grant Haste & Resistance");
            lore.add(ChatColor.LIGHT_PURPLE + "to nearby clan members within 30 blocks!");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }
}

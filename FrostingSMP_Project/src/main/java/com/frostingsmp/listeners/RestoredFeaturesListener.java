package com.frostingsmp.listeners;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RestoredFeaturesListener implements Listener {

    private final FrostingSMP plugin;

    public RestoredFeaturesListener(FrostingSMP plugin) { this.plugin = plugin; }

    @EventHandler
    public void onGodAppleEat(PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == Material.ENCHANTED_GOLDEN_APPLE) {
            Player p = event.getPlayer();
            p.sendMessage(ChatColor.GOLD + "⚡ You consumed an Unnerfed God Apple!");
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 4));
            p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 6000, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 6000, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 3));
        }
    }

    @EventHandler
    public void onZombieVillagerCure(EntityTransformEvent event) {
        if (event.getTransformReason() == EntityTransformEvent.TransformReason.CURED) {
            if (event.getTo() instanceof Villager) {
                Villager villager = (Villager) event.getTo();
                villager.setCustomName(ChatColor.GREEN + "Restored Uncapped Villager");
            }
        }
    }
}

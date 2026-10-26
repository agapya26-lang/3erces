package com.frostingsmp.listeners;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DonutMechanicsListener implements Listener {

    private final FrostingSMP plugin;

    public DonutMechanicsListener(FrostingSMP plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        double victimBalance = getBalance(victim);
        if (victimBalance > 0) {
            double stolen = victimBalance * 0.25;
            subtractBalance(victim, stolen);
            victim.sendMessage(ChatColor.RED + "You lost $" + String.format("%.2f", stolen) + " on death!");

            if (killer != null) {
                addBalance(killer, stolen);
                killer.sendMessage(ChatColor.GREEN + "You stole $" + String.format("%.2f", stolen) + " from " + victim.getName() + "!");
            }
        }
    }

    private double getBalance(Player p) { return 1000.0; }
    private void addBalance(Player p, double amount) {}
    private void subtractBalance(Player p, double amount) {}
}

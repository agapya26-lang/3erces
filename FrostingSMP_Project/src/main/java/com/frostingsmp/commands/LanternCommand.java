package com.frostingsmp.commands;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LanternCommand implements CommandExecutor {

    private final FrostingSMP plugin;

    public LanternCommand(FrostingSMP plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        player.sendMessage(ChatColor.GOLD + "🏮 Lantern Light Shop");
        player.getInventory().addItem(plugin.getLanternManager().createBuffLantern());
        player.sendMessage(ChatColor.GREEN + "Received 1x Frosting Base Lantern!");
        return true;
    }
}

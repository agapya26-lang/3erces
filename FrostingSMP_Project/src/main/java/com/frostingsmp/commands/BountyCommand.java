package com.frostingsmp.commands;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BountyCommand implements CommandExecutor {

    private final FrostingSMP plugin;

    public BountyCommand(FrostingSMP plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "=== Frosting SMP Bounty System ===");
        sender.sendMessage(ChatColor.YELLOW + "Usage: /bounty <player> <amount>");
        return true;
    }
}

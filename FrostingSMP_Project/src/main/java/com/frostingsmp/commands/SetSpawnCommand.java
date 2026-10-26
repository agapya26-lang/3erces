package com.frostingsmp.commands;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final FrostingSMP plugin;

    public SetSpawnCommand(FrostingSMP plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (!player.hasPermission("frostingsmp.admin.setspawn")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to set spawn.");
            return true;
        }

        plugin.getSpawnManager().setSpawn(player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Frosting SMP spawn point updated to your location!");
        return true;
    }
}

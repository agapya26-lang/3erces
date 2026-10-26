package com.frostingsmp.commands;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final FrostingSMP plugin;

    public SpawnCommand(FrostingSMP plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        Location spawn = plugin.getSpawnManager().getSpawnLocation();

        if (spawn == null) {
            player.sendMessage(ChatColor.RED + "Spawn point has not been set yet!");
            return true;
        }

        player.teleport(spawn);
        player.sendMessage(ChatColor.GREEN + "Teleported to Spawn!");
        return true;
    }
}

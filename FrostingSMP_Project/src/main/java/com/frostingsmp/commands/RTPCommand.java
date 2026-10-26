package com.frostingsmp.commands;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class RTPCommand implements CommandExecutor {

    private final FrostingSMP plugin;
    private final Random random = new Random();

    public RTPCommand(FrostingSMP plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        World world = player.getWorld();
        int minRadius = 500;
        int maxRadius = 5000;

        int x = random.nextInt(maxRadius - minRadius) + minRadius;
        int z = random.nextInt(maxRadius - minRadius) + minRadius;
        if (random.nextBoolean()) x = -x;
        if (random.nextBoolean()) z = -z;

        int y = world.getHighestBlockYAt(x, z);
        Location target = new Location(world, x + 0.5, y + 1, z + 0.5);

        Material baseBlock = world.getBlockAt(x, y, z).getType();
        if (baseBlock == Material.LAVA || baseBlock == Material.WATER) {
            return onCommand(sender, command, label, args);
        }

        player.teleport(target);
        player.sendMessage(ChatColor.LIGHT_PURPLE + "✨ Teleported to wild coordinates: " + ChatColor.GOLD + x + ", " + (y+1) + ", " + z);
        return true;
    }
}

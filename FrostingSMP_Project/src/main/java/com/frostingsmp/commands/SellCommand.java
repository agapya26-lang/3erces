package com.frostingsmp.commands;

import com.frostingsmp.FrostingSMP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SellCommand implements CommandExecutor {

    private final FrostingSMP plugin;

    public SellCommand(FrostingSMP plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /sell <price>");
            return true;
        }

        double price;
        try {
            price = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Please enter a valid price!");
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "You must hold an item in your hand to sell!");
            return true;
        }

        plugin.getAuctionHouse().addItem(item.clone(), price, player.getName());
        player.getInventory().setItemInMainHand(null);
        player.sendMessage(ChatColor.GREEN + "Listed item on the Auction House for $" + price);
        return true;
    }
}

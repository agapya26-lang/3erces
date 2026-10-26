package com.frostingsmp.auction;

import com.frostingsmp.FrostingSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AuctionHouse {

    private final FrostingSMP plugin;
    private final List<AuctionItem> listings = new ArrayList<>();

    public AuctionHouse(FrostingSMP plugin) { this.plugin = plugin; }

    public static class AuctionItem {
        public ItemStack item;
        public double price;
        public String sellerName;

        public AuctionItem(ItemStack item, double price, String sellerName) {
            this.item = item;
            this.price = price;
            this.sellerName = sellerName;
        }
    }

    public void addItem(ItemStack item, double price, String seller) {
        listings.add(new AuctionItem(item, price, seller));
    }

    public Inventory createAHMenu() {
        Inventory gui = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Frosting SMP Auction House");
        for (int i = 0; i < Math.min(listings.size(), 54); i++) {
            AuctionItem ai = listings.get(i);
            gui.setItem(i, ai.item);
        }
        return gui;
    }

    public void saveAuctions() {}
}

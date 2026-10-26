package com.frostingsmp;

import com.frostingsmp.anticheat.CustomAntiCheat;
import com.frostingsmp.auction.AuctionHouse;
import com.frostingsmp.commands.*;
import com.frostingsmp.listeners.*;
import com.frostingsmp.managers.*;
import org.bukkit.plugin.java.JavaPlugin;

public class FrostingSMP extends JavaPlugin {

    private static FrostingSMP instance;
    private SpawnManager spawnManager;
    private AuctionHouse auctionHouse;
    private CustomAntiCheat antiCheat;
    private LanternManager lanternManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Initialize Managers
        this.spawnManager = new SpawnManager(this);
        this.auctionHouse = new AuctionHouse(this);
        this.antiCheat = new CustomAntiCheat(this);
        this.lanternManager = new LanternManager(this);

        // Register Commands
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("rtp").setExecutor(new RTPCommand(this));
        getCommand("ah").setExecutor(new AHCommand(this));
        getCommand("sell").setExecutor(new SellCommand(this));
        getCommand("bounty").setExecutor(new BountyCommand(this));
        getCommand("lantern").setExecutor(new LanternCommand(this));
        getCommand("acproof").setExecutor(new AntiCheatProofCommand(this));

        // Register Listeners
        getServer().getPluginManager().registerEvents(new SpawnProtectionListener(this), this);
        getServer().getPluginManager().registerEvents(new DonutMechanicsListener(this), this);
        getServer().getPluginManager().registerEvents(new RestoredFeaturesListener(this), this);
        getServer().getPluginManager().registerEvents(new LanternLightListener(this), this);
        getServer().getPluginManager().registerEvents(antiCheat, this);

        getLogger().info("FrostingSMP Plugin fully loaded with Donut Mechanics + LanternLight features!");
    }

    @Override
    public void onDisable() {
        if (auctionHouse != null) auctionHouse.saveAuctions();
        if (spawnManager != null) spawnManager.saveSpawn();
    }

    public static FrostingSMP getInstance() { return instance; }
    public SpawnManager getSpawnManager() { return spawnManager; }
    public AuctionHouse getAuctionHouse() { return auctionHouse; }
    public CustomAntiCheat getAntiCheat() { return antiCheat; }
    public LanternManager getLanternManager() { return lanternManager; }
}

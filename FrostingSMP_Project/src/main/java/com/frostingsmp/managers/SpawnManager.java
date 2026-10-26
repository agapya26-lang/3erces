package com.frostingsmp.managers;

import com.frostingsmp.FrostingSMP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnManager {

    private final FrostingSMP plugin;
    private Location spawnLocation;
    private double protectionRadius;

    public SpawnManager(FrostingSMP plugin) {
        this.plugin = plugin;
        loadSpawn();
    }

    public void loadSpawn() {
        FileConfiguration config = plugin.getConfig();
        if (config.contains("spawn.world")) {
            World world = Bukkit.getWorld(config.getString("spawn.world"));
            double x = config.getDouble("spawn.x");
            double y = config.getDouble("spawn.y");
            double z = config.getDouble("spawn.z");
            float yaw = (float) config.getDouble("spawn.yaw");
            float pitch = (float) config.getDouble("spawn.pitch");
            this.spawnLocation = new Location(world, x, y, z, yaw, pitch);
        }
        this.protectionRadius = config.getDouble("spawn.protection-radius", 150.0);
    }

    public void saveSpawn() {
        if (spawnLocation == null) return;
        FileConfiguration config = plugin.getConfig();
        config.set("spawn.world", spawnLocation.getWorld().getName());
        config.set("spawn.x", spawnLocation.getX());
        config.set("spawn.y", spawnLocation.getY());
        config.set("spawn.z", spawnLocation.getZ());
        config.set("spawn.yaw", spawnLocation.getYaw());
        config.set("spawn.pitch", spawnLocation.getPitch());
        plugin.saveConfig();
    }

    public void setSpawn(Location loc) {
        this.spawnLocation = loc;
        saveSpawn();
    }

    public boolean isInSpawnProtection(Location loc) {
        if (spawnLocation == null || loc.getWorld() == null) return false;
        if (!loc.getWorld().equals(spawnLocation.getWorld())) return false;
        return loc.distance(spawnLocation) <= protectionRadius;
    }

    public Location getSpawnLocation() { return spawnLocation; }
}

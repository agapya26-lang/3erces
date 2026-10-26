package com.frostingsmp.anticheat;

import com.frostingsmp.FrostingSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.*;

public class CustomAntiCheat implements Listener {

    private final FrostingSMP plugin;
    private final Map<UUID, Queue<ProofFrame>> playerFrameBuffers = new HashMap<>();
    private final Map<UUID, List<List<ProofFrame>>> savedProofLogs = new HashMap<>();

    public CustomAntiCheat(FrostingSMP plugin) {
        this.plugin = plugin;
        startFrameRecorderTask();
    }

    public static class ProofFrame {
        public long timestamp;
        public double x, y, z;
        public float pitch, yaw;

        public ProofFrame(Location loc) {
            this.timestamp = System.currentTimeMillis();
            this.x = loc.getX();
            this.y = loc.getY();
            this.z = loc.getZ();
            this.pitch = loc.getPitch();
            this.yaw = loc.getYaw();
        }
    }

    private void startFrameRecorderTask() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                Queue<ProofFrame> frames = playerFrameBuffers.computeIfAbsent(p.getUniqueId(), k -> new LinkedList<>());
                if (frames.size() >= 200) {
                    frames.poll();
                }
                frames.add(new ProofFrame(p.getLocation()));
            }
        }, 1L, 1L);
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            double distance = attacker.getLocation().distance(event.getEntity().getLocation());

            if (distance > 3.8) {
                flag(attacker, "Reach / Killaura (Dist: " + String.format("%.2f", distance) + "m)");
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (to == null) return;
        double dist = Math.sqrt(Math.pow(to.getX() - from.getX(), 2) + Math.pow(to.getZ() - from.getZ(), 2));

        if (dist > 0.85 && !player.isFlying() && !player.hasPotionEffect(org.bukkit.potion.PotionEffectType.SPEED)) {
            flag(player, "Speed / Movement Manipulation (Speed: " + String.format("%.2f", dist) + "m/t)");
        }
    }

    public void flag(Player player, String reason) {
        String alert = ChatColor.RED + "[Frosting-AC] " + ChatColor.YELLOW + player.getName() + 
                       ChatColor.RED + " flagged for " + ChatColor.WHITE + reason;

        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission("frostingsmp.admin.anticheat")) {
                staff.sendMessage(alert);
            }
        }

        Queue<ProofFrame> currentFrames = playerFrameBuffers.get(player.getUniqueId());
        if (currentFrames != null) {
            List<ProofFrame> proofSnapshot = new ArrayList<>(currentFrames);
            savedProofLogs.computeIfAbsent(player.getUniqueId(), k -> new ArrayList<>()).add(proofSnapshot);
        }
    }

    public List<List<ProofFrame>> getProofLogs(UUID uuid) {
        return savedProofLogs.getOrDefault(uuid, Collections.emptyList());
    }
}

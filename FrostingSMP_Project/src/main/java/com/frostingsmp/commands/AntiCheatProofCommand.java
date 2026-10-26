package com.frostingsmp.commands;

import com.frostingsmp.FrostingSMP;
import com.frostingsmp.anticheat.CustomAntiCheat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AntiCheatProofCommand implements CommandExecutor {

    private final FrostingSMP plugin;

    public AntiCheatProofCommand(FrostingSMP plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /acproof <player>");
            return true;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        List<List<CustomAntiCheat.ProofFrame>> logs = plugin.getAntiCheat().getProofLogs(target.getUniqueId());

        if (logs.isEmpty()) {
            sender.sendMessage(ChatColor.GREEN + "No cheat proof frames logged for " + target.getName());
            return true;
        }

        sender.sendMessage(ChatColor.GOLD + "=== AntiCheat Proof Logs for " + target.getName() + " ===");
        int logIndex = 1;
        for (List<CustomAntiCheat.ProofFrame> session : logs) {
            sender.sendMessage(ChatColor.YELLOW + "Log #" + logIndex + " (" + session.size() + " Recorded Ticks):");
            CustomAntiCheat.ProofFrame first = session.get(0);
            CustomAntiCheat.ProofFrame last = session.get(session.size() - 1);
            sender.sendMessage(ChatColor.GRAY + " - Start: X:" + (int)first.x + " Y:" + (int)first.y + " Z:" + (int)first.z);
            sender.sendMessage(ChatColor.GRAY + " - End:   X:" + (int)last.x + " Y:" + (int)last.y + " Z:" + (int)last.z);
            logIndex++;
        }
        return true;
    }
}

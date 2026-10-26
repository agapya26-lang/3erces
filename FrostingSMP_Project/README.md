# Frosting SMP Core Plugin

Custom Spigot/Paper 1.20+ plugin designed for **Frosting SMP**.

## Features Included
- **Donut SMP Mechanics**: Unclaimable anarchy survival, 25% balance steal on player kill, custom Auction House (`/ah`), and Bounties (`/bounty`).
- **Restored Community Features**: Unnerfed Enchanted Golden Apples (Notch Apples) and uncapped villager curing discount stacking.
- **LanternLight Systems**: Base area buff lanterns, Lantern Light Shards, and extraction events.
- **Spawn Protection & RTP**: Full protection zone against break/place/PvP and safe random teleportation (`/rtp`).
- **Autonomous Anti-Cheat System**: Self-contained reach and movement tracking with 10-second proof frame logging (`/acproof`).

## Commands
- `/spawn` - Teleport to safe spawn zone.
- `/setspawn` - Set spawn location (Admin).
- `/rtp` - Teleport to random safe location.
- `/ah` - Open Auction House.
- `/sell <price>` - List item on Auction House.
- `/bounty <player> <amount>` - Place bounty on a player.
- `/lantern` - Open Lantern Light Essence menu.
- `/acproof <player>` - Review anti-cheat proof logs (Admin).

## Build Instructions
Run standard Maven build:
```bash
mvn clean package
```
The compiled JAR will be located in the `target/` folder.

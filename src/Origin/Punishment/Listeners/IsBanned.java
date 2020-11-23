package Origin.Punishment.Listeners;


import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Date;

public class IsBanned implements Listener {


        @EventHandler
        public void PlayerLoginEvent(PlayerLoginEvent event) {
            if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {
                String reason;
                Date duration = null;
                if (Bukkit.getBanList(BanList.Type.NAME).isBanned(event.getPlayer().getName())) {
                    reason = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(event.getPlayer().getName()).getReason();
                    duration = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(event.getPlayer().getName()).getExpiration();
                } else {
                    reason = Bukkit.getBanList(BanList.Type.IP).getBanEntry(event.getAddress().getHostAddress()).getReason();
                }
                if (reason == null) {
                    //event.setKickMessage(reason);
                    reason = "No Reason Given";
                }
                if (duration == null) {
                    event.setKickMessage(ChatColor.RED + "You are banned!" + System.lineSeparator() +
                            ChatColor.RED + "Reason: "+ ChatColor.WHITE + reason + System.lineSeparator() + ChatColor.GREEN + " UnBan Date: " + ChatColor.WHITE +
                            " Never" + System.lineSeparator() + ChatColor.AQUA + " Appeal your ban at - https://discord.gg/YgNU3Pr");
                } else {
                    event.setKickMessage(ChatColor.RED + "You are banned!" + System.lineSeparator() +
                            ChatColor.RED + "Reason: "+ ChatColor.WHITE + reason + System.lineSeparator() + ChatColor.GREEN + " UnBan Date: " + ChatColor.WHITE +
                            duration + System.lineSeparator() + ChatColor.AQUA + " Appeal your ban at - https://discord.gg/YgNU3Pr");
                }
            }
        }

    }

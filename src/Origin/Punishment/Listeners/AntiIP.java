package Origin.Punishment.Listeners;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AntiIP implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {


        String IPADDRESS_PATTERN =
                "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        String message = e.getMessage().toString();
        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(message);


        // DEFINING PLAYER

        Player p = e.getPlayer();

        // DISABLING CHAT / CHECKING
        if (!(p.hasPermission("ps.bypassip"))) {
            if (matcher.find()) {

                e.setCancelled(true);

                //need to cancel receiving messages

                e.getRecipients().remove(p);

                p.sendMessage(ChatColor.RED.toString() + ChatColor.ITALIC + "Please do not advertise! Staff have been notified!");


                for (Player publicplayer : Bukkit.getOnlinePlayers()) { //gets all online players
                    if (publicplayer.hasPermission("<FP>.REPORT.R")) { //check if they have certain permission
                        publicplayer.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Auto-Reports" + ChatColor.WHITE + "] "
                                + ChatColor.GREEN + p.getName() + " attempted to advertise an ip! Message: "+ ChatColor.WHITE + message); //sends the message to the user
                    }
                }

            }

            for (Player pl : e.getRecipients()) {

                if (matcher.find()) {

                    e.getRecipients().remove(pl);

                }
            }

        }
    }




}













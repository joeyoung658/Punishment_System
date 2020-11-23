package Origin.Punishment.Listeners;



import Origin.Punishment.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.ArrayList;

public class AntiSpam implements Listener {
    public static ArrayList<String> AntiSpam = new ArrayList<String>();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        // DEFINING PLAYER

        Player p = e.getPlayer();



        // DISABLING CHAT / CHECKING
        if (!(p.hasPermission("ps.bypassspam"))) {
            if (AntiSpam.contains(p.getName())) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Please wait at least one second before your next message!");
            } else {
                AntiSpamt(p);
            }
        }
    }
    public void AntiSpamt(Player player) {
            AntiSpam.add(player.getName());
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                @Override
                public void run() {
                    if (AntiSpam.contains(player.getName())) {
                        AntiSpam.remove(player.getName());
                    }
                }
            }, 20l);
    }
}













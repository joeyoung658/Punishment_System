package Origin.Punishment.Listeners;

import Origin.Punishment.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


import java.util.List;





public class CommandBlock implements Listener {
    @EventHandler
    public void onPlayerChat(PlayerCommandPreprocessEvent e) {
        List<String> cmds = Main.instance.getConfig().getStringList("BlockedCommands");
        Player p = e.getPlayer();
        if(!p.hasPermission("ps.commandblock.bypass")) {
            for (String command : cmds) {
                if (e.getMessage().startsWith("/" + command)) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "Unknown command. Type /commands for help!");
                }
            }
        }
    }


}













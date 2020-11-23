package Origin.Punishment.Listeners;

import Origin.Punishment.Logs.Logging;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by Joes_room on 29/11/2016.
 */
public class ChatLog implements Listener {
    public Logging Logging = new Logging();
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Logging.logToFile(e.getPlayer().getName() + ": " + e.getMessage().toString(), "ChatLog");
    }
    @EventHandler
    public void onPlayerChat(PlayerCommandPreprocessEvent e) {
        Logging.logToFile(e.getPlayer().getName() + ": " + e.getMessage().toString(), "ChatLog");
    }
}

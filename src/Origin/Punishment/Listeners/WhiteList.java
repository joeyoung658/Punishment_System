package Origin.Punishment.Listeners;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class WhiteList implements Listener {


    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
            event.setKickMessage(ChatColor.RED + "The server is currently under maintenance!" + System.lineSeparator() +
                    ChatColor.AQUA + "We will be back soon!" + System.lineSeparator() + ChatColor.GREEN + "To pass the time join our discord! - https://discord.gg/YgNU3Pr");

        }
    }

}

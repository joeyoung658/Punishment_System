package Origin.Punishment.Listeners;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinError implements Listener {


    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        if (event.getResult() == PlayerLoginEvent.Result.KICK_OTHER) {
            event.setKickMessage(ChatColor.RED + "There has been some sort of error." + System.lineSeparator() +
                    ChatColor.AQUA + "Please try again." + System.lineSeparator() + ChatColor.GREEN + "If the issue persists please report on our discord - https://discord.gg/YgNU3Pr");

        }
    }

}

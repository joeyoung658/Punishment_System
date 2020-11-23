//package Origin.Punishment.Listeners;
//
//
//
//import org.bukkit.ChatColor;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerLoginEvent;
//
//
//public class ServerFull implements Listener {
//
//
//    @EventHandler
//    public void PlayerLoginEvent(PlayerLoginEvent event) {
//        if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
//            if (event.getPlayer().hasPermission("ps.bypass.playerlimit") || event.getPlayer().isOp()){
//                event.setResult(PlayerLoginEvent.Result.ALLOWED);
//            } else {
//                event.setKickMessage(ChatColor.RED + "The server is currently full!" + System.lineSeparator() +
//                        ChatColor.AQUA + "Please try again later!" + System.lineSeparator() + ChatColor.GREEN + "To pass the time checkout our discord! - https://discord.gg/YgNU3Pr");
//            }
//        }
//    }
//}
//
//
//
//

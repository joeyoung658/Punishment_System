package Origin.Punishment.Commands;



import Origin.Punishment.Logs.Logging;
import Origin.Punishment.Main;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UnBan implements CommandExecutor {
    public Logging Logging = new Logging();
    public String playername;
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        DateFormat d = new SimpleDateFormat("dd/MM/yy");
        DateFormat t = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        //Player player = (Player) sender;


        if (cmd.getName().equalsIgnoreCase("unban")) {
            if (!(sender instanceof Player)) {
                playername = sender.getName();
            } else {
                playername = ((Player) sender).getDisplayName();
            }
                if (!(sender.hasPermission("ps.ban"))) {
                    sender.sendMessage(ChatColor.DARK_RED + "This is an Moderator +  only command!");
                } else {

                    if (args.length == 1) {
                        OfflinePlayer otarget = Main.instance.getServer().getOfflinePlayer(args[0]);
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target == null) {
                            //otarget.setBanned(false);
                            Bukkit.getBanList(BanList.Type.NAME).pardon(otarget.getName());

                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " +
                                    playername + ChatColor.GOLD + " has unbanned " + ChatColor.RED + args[0] + ChatColor.GOLD + " from the server ");
                            Logging.logToFile(("Type: UnBan Staff: " + sender.getName() + " Recipient: " + args[0] + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "BanLog");

                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "You can't unban an online player!");

                        }

                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments");
                        sender.sendMessage(ChatColor.DARK_PURPLE + "/unban [Player]");

                    }
                }


        }
        return true;
    }

}


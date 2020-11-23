package Origin.Punishment.Commands;



import Origin.Punishment.Logs.Logging;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UnBanIP implements CommandExecutor {
    public Logging Logging = new Logging();
    public String playername;
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        DateFormat d = new SimpleDateFormat("dd/MM/yy");
        DateFormat t = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        //Player player = (Player) sender;



        if (cmd.getName().equalsIgnoreCase("unbanip")) {
            if (!(sender instanceof Player)) {
                playername = sender.getName();
            } else {
                playername = ((Player) sender).getDisplayName();
            }
                if (!(sender.hasPermission("ps.banip"))) {
                    sender.sendMessage(ChatColor.DARK_RED + "This is an Admin +  only command!");
                } else {

                    if (args.length == 1) {
                            Bukkit.getBanList(BanList.Type.IP).pardon(args[0]);
                        Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" +  ChatColor.WHITE + "] " + playername + ChatColor.GOLD +" made an IP unban.");
                        sender.sendMessage(ChatColor.DARK_RED + args[0] + " was unbanned.");

                        Logging.logToFile(("Type: UnBanIP Staff: " + sender.getName() + " Recipient: " + args[0] + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "BanLog");



                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments");
                        sender.sendMessage(ChatColor.DARK_PURPLE + "/unbanip [Player]");

                    }
                }


        }
        return true;
    }

}


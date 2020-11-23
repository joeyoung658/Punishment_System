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

public class IPBan implements CommandExecutor {
    public Logging Logging = new Logging();
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        Player player = (Player) sender;
        DateFormat d = new SimpleDateFormat("dd/MM/yy");
        DateFormat t = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 1; i < args.length; i++)
            b.append(args[i] + " ");

        if (cmd.getName().equalsIgnoreCase("banip")) { //no permission needed to use /report
            if (!(sender.hasPermission("ps.banip")))  {
                sender.sendMessage(ChatColor.DARK_RED + "This is an Admin +  only command!");

            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments!");
                    sender.sendMessage(ChatColor.DARK_PURPLE + "/banip [IP] [Reason]");

                }else if (args.length == 1) {


                    Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" +  ChatColor.WHITE + "] " + ((Player) sender).getDisplayName() + ChatColor.GOLD +" made an IP ban.");
                    player.sendMessage(ChatColor.DARK_RED + args[0] + " was banned.");



                    BanList bl = Bukkit.getBanList(BanList.Type.IP);
                        bl.addBan(args[0], "No reason given", null, "source");
                    Logging.logToFile(("Type: IPBan Staff: " + player.getName() + " Recipient: "+ args[0] +" Reason: None " + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "BanLog");
                } else {

                    Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" +  ChatColor.WHITE + "] " + ((Player) sender).getDisplayName() + ChatColor.GOLD +" made an IP ban.");
                    Bukkit.broadcastMessage(ChatColor.RED + "Reason - " + b.toString());
                    player.sendMessage(ChatColor.DARK_RED + args[0] + " was banned.");


                    BanList bl = Bukkit.getBanList(BanList.Type.IP);
                        bl.addBan(args[0], b.toString(), null, "source");
                    Logging.logToFile(("Type: IPBan Staff: " + player.getName() + " Recipient: "+ args[0] +" Reason: "+ b.toString() + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "BanLog");
                }
            }
        }
        return true; //ends commands statement
    }
}


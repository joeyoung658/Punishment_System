package Origin.Punishment.Commands;


import Origin.Punishment.Logs.Logging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ClearChat implements CommandExecutor {
    public Logging Logging = new Logging();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        DateFormat d = new SimpleDateFormat("dd/MM/yy");
        DateFormat t = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();

        //Player player = (Player) sender;
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 0; i < args.length; i++)
            b.append(args[i] + " ");
        String displayname;
        if (cmd.getName().equalsIgnoreCase("clearchat")) {
            if (!(sender instanceof Player)) {
                displayname = sender.getName();
            } else {
                displayname =((Player) sender).getDisplayName();
            }
                if (sender.hasPermission("ps.clearchat")) {
                    if (args.length == 0) {
                        int endloop = 100;
                        for (int loop = 0; loop < endloop; loop++ ) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (!(p.hasPermission("ps.chatclearbypass"))) {
                                    p.sendMessage("");
                                }
                            }
                        }
                        Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + ChatColor.GOLD + "Chat has been cleared by " + displayname);
                        Bukkit.broadcastMessage(ChatColor.RED + "Reason - None Given");
                        Logging.logToFile(("Staff: " + sender.getName() + " Reason: None Given " + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "ChatClearLog");
                    } else if (args.length >= 1) {
                        int endloop = 100;
                        for (int loop = 0; loop < endloop; loop++ ) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (!(p.hasPermission("ps.chatclearbypass")))  {
                                    p.sendMessage("");
                                }
                            }
                        }
                        Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + ChatColor.GOLD + "Chat has been cleared by " + displayname);
                        Bukkit.broadcastMessage(ChatColor.RED + "Reason - " + b.toString());
                        Logging.logToFile(("Staff Member: " + sender.getName() + " Reason: " + b.toString() + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "ChatClearLog");
                    } else {
                            sender.sendMessage(ChatColor.RED + "Incorrect arguments!");
                            sender.sendMessage(ChatColor.AQUA + "/clearchat [reason]");
                            return false;
                    }


                } else {
                    sender.sendMessage(ChatColor.RED + "This is an Helper + only command!");
                }


        }
        return true;
    }

}
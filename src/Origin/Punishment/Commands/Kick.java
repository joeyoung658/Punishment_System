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

public class Kick implements CommandExecutor {
    //public KickLog KickLog = new KickLog();
    public Logging Logging = new Logging();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        DateFormat d = new SimpleDateFormat("dd/MM/yy");
        DateFormat t = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        //Player player = (Player) sender;
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 1; i < args.length; i++)
            b.append(args[i] + " ");

        if (cmd.getName().equalsIgnoreCase("kick")) { //no permission needed to use /report
            if (!(sender.hasPermission("ps.kick"))) {
                sender.sendMessage(ChatColor.DARK_RED + "This is an Moderator +  only command!");

            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments!");
                    sender.sendMessage(ChatColor.DARK_PURPLE + "/kick [Player] [Reason]");
                    return false;
                }else if (args.length == 1) {

                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        sender.sendMessage(ChatColor.DARK_RED + args[0] + " is offline");

                    } else {
                        if (!(target.hasPermission("ps-np") || args[0].equalsIgnoreCase("origin658"))) {
                            target.kickPlayer(ChatColor.RED + "You have been kicked by " + ChatColor.WHITE + ((Player) sender).getDisplayName() + System.lineSeparator() +
                                    ChatColor.RED + "Reason:" + ChatColor.WHITE + "No reason given " + System.lineSeparator() + ChatColor.AQUA + " Unfair kick? Report at -https://discord.gg/YgNU3Pr");
                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + ((Player) sender).getDisplayName() + ChatColor.GOLD + " has kicked " + target.getDisplayName() + ChatColor.GOLD + " from the server ");

                            Logging.logToFile(("Staff: " + sender.getName() + " Recipient: " + target.getName() + " Reason: None " + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "KickLog");
                            } else {
                            sender.sendMessage(ChatColor.DARK_RED + "You may not kick that player!");
                            return false;
                            }
                        }
                    }else{

                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target == null) {
                            sender.sendMessage(ChatColor.DARK_RED + args[0] + " is offline");


                        } else {
                            if (!(target.hasPermission("ps.np") || args[0].equalsIgnoreCase("origin658"))) {
                                target.kickPlayer(ChatColor.RED + "You have been kicked by " + ChatColor.WHITE + ((Player) sender).getDisplayName() + System.lineSeparator() +
                                        ChatColor.RED + "Reason: " + ChatColor.WHITE + b.toString() + System.lineSeparator() + ChatColor.AQUA + " Unfair kick? Report at - https://discord.gg/YgNU3Pr");

                                Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + ((Player) sender).getDisplayName() + ChatColor.GOLD + " has kicked " + target.getDisplayName() + ChatColor.GOLD + " from the server ");
                                Bukkit.broadcastMessage(ChatColor.RED + "Reason - " + b.toString());
                                Logging.logToFile(("Staff: " + sender.getName() + " Recipient: " + target.getName() + " Reason: " + b.toString() + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "KickLog");
                            } else {
                                sender.sendMessage(ChatColor.DARK_RED + "You may not kick that player!");
                                return false;
                            }
                        }

                    }


            }
        }


        return true; //ends commands statement
    }
}


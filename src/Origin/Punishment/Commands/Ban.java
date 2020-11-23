package Origin.Punishment.Commands;



import Origin.Punishment.Logs.Logging;
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

public class Ban implements CommandExecutor {
    public Logging Logging = new Logging();
    public String playername;
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        DateFormat d = new SimpleDateFormat("dd/MM/yy");
        DateFormat t = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();

        //Player player = (Player) sender;

        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 1; i < args.length; i++)
            b.append(args[i] + " ");

        if (cmd.getName().equalsIgnoreCase("ban")) { //no permission needed to use /report

            if (!(sender instanceof Player)) {
                playername = sender.getName();
            } else {
                playername = ((Player) sender).getDisplayName();
            }


            if (!(sender.hasPermission("ps.ban") ))  {
                sender.sendMessage(ChatColor.DARK_RED + "This is an Moderator +  only command!");

            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments!");
                    sender.sendMessage(ChatColor.DARK_PURPLE + "/ban [Player] [Reason]");

                }else if (args.length == 1) {

                    Player target = Bukkit.getPlayerExact(args[0]);
                    //changed from sender to bukkit - may be reason why broken
                    OfflinePlayer otarget = Bukkit.getServer().getOfflinePlayer(args[0]);

                        if (!(target == null)) {

                            if (!(target.hasPermission("ps.np") || args[0].equalsIgnoreCase("-"))) {
                                target.kickPlayer(ChatColor.RED + "Ban Dealt by " + ChatColor.WHITE + playername + System.lineSeparator() +
                                        ChatColor.RED + "Reason:" + ChatColor.WHITE + "No reason given " + System.lineSeparator() + ChatColor.GREEN + " UnBan Date: " + ChatColor.WHITE +
                                        " Never" + System.lineSeparator() + ChatColor.AQUA + " Appeal your ban at - https://discord.gg/YgNU3Pr");

                                Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                                        + ChatColor.GOLD + " has permanently banned " + target.getDisplayName() + ChatColor.GOLD + " from the server.");
                            } else {
                                sender.sendMessage(ChatColor.DARK_RED + "You may not ban that player!");
                                return false;
                            }
                        } else {
                            if (!(otarget.hasPlayedBefore())) {
                                sender.sendMessage(ChatColor.DARK_RED + args[0] + " has never joined the server!");
                                return false;
                            } else {
                                Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                                        + ChatColor.GOLD + " has permanently banned " + ChatColor.RED + args[0] + ChatColor.GOLD + " from the server.");
                            }
                        }

                    BanList bl = Bukkit.getBanList(BanList.Type.NAME);
                        bl.addBan(args[0], "No reason given", null, playername);



                    Logging.logToFile(("Type: PermaBan Staff: " + sender.getName() + " Recipient: "+ args[0] +" Reason: None " + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "BanLog");
                } else {

                    Player target = Bukkit.getPlayerExact(args[0]);
                    OfflinePlayer otarget = Bukkit.getServer().getOfflinePlayer(args[0]);
                        if (!(target == null)) {
                            if (!(target.hasPermission("ps.np") || args[0].equalsIgnoreCase("origin658"))) {
                                target.kickPlayer(ChatColor.RED + "Ban Dealt by " + ChatColor.WHITE + playername + System.lineSeparator() +
                                        ChatColor.RED + "Reason: " + ChatColor.WHITE + b.toString() + System.lineSeparator() + ChatColor.GREEN + " UnBan Date: " + ChatColor.WHITE +
                                        " Never" + System.lineSeparator() + ChatColor.AQUA + " Appeal your ban at - https://discord.gg/YgNU3Pr");

                                Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                                        + ChatColor.GOLD + " has permanently banned " + target.getDisplayName() + ChatColor.GOLD + " from the server.");
                                Bukkit.broadcastMessage(ChatColor.RED + "Reason - " + b.toString());
                            } else {
                                sender.sendMessage(ChatColor.DARK_RED + "You may not ban that player!");
                                return false;
                            }

                        } else {
                            if (!(otarget.hasPlayedBefore())) {
                                sender.sendMessage(ChatColor.DARK_RED + args[0] + " has never joined the server!");
                                return false;
                            } else {
                                Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                                        + ChatColor.GOLD + " has permanently banned " + ChatColor.RED + args[0] + ChatColor.GOLD + " from the server.");
                                Bukkit.broadcastMessage(ChatColor.RED + "Reason - " + b.toString());
                            }
                        }
                    BanList bl = Bukkit.getBanList(BanList.Type.NAME);
                        bl.addBan(args[0], b.toString(), null, playername);
                    Logging.logToFile(("Type: PermaBan Staff: " + sender.getName() + " Recipient: "+ args[0] +" Reason: "+ b.toString() + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "BanLog");



                }


            }
        }


        return true; //ends commands statement
    }
}


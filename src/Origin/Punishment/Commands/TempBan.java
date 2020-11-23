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
import java.util.Calendar;
import java.util.Date;


public class TempBan implements CommandExecutor {
    public Logging Logging = new Logging();
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        DateFormat d = new SimpleDateFormat("dd/MM/yy");
        DateFormat t = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        //Player player = (Player) sender;
        String amountime;
        int amount;
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 2; i < args.length; i++)
            b.append(args[i] + " ");

        if (cmd.getName().equalsIgnoreCase("tempban")) { //no permission needed to use /report
            if (!(sender.hasPermission("ps.tempban")))  {
                sender.sendMessage(ChatColor.DARK_RED + "This is an Moderator +  only command!");

            } else {
            if (args.length >= 3) {

                Player target = Bukkit.getPlayerExact(args[0]);
                OfflinePlayer otarget = Main.instance.getServer().getOfflinePlayer(args[0]);
                Calendar cal = Calendar.getInstance();

                if (args[1] == null || args[1].length() == 1) {
                    sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments!");
                    sender.sendMessage(ChatColor.DARK_PURPLE + "/tempban [Player] [Duration] [Reason]");
                    return false;
                } else {
                    if (args[1].contains("y")) {
                        amount = time(args[1]);
                        cal.add(Calendar.YEAR, amount);
                        amountime = (amount + " Year(s)");
                    } else if (args[1].contains("d")) {
                        amount = time(args[1]);
                        cal.add(Calendar.DATE, amount);
                        amountime = (amount + " Day(s)");
                    } else if (args[1].contains("h")) {
                        amount = time(args[1]);
                        cal.add(Calendar.HOUR, amount);
                        amountime = (amount + " Hour(s)");
                    } else if (args[1].contains("m")) {
                        amount = time(args[1]);
                        cal.add(Calendar.MINUTE, amount);
                        amountime = (amount + " Minute(s)");
                    } else if (args[1].contains("s")) {
                        amount = time(args[1]);
                        cal.add(Calendar.SECOND, amount);
                        amountime = (amount + " Second(s)");

                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "Incorrect time arguments!");
                        sender.sendMessage(ChatColor.AQUA + "1y = One Year, 1d = One Day, 1h = One Hour, 1m = 1 minute and 1s = One Second");
                        return false;
                    }
                }
                if (!(amount == 0)) {
                    if (!(target == null)) {
                        if (!(target.hasPermission("ps.np") || args[0].equalsIgnoreCase("origin658"))) {
                            target.kickPlayer(ChatColor.RED + "Ban Dealt by " + ChatColor.WHITE + ((Player) sender).getDisplayName() + System.lineSeparator() +
                                    ChatColor.RED + "Reason: " + ChatColor.WHITE + b.toString() + System.lineSeparator() + ChatColor.GREEN + " UnBan Date: " + ChatColor.WHITE +
                                    cal.getTime() + System.lineSeparator() + ChatColor.AQUA + " Appeal your ban at - https://discord.gg/YgNU3Pr");

                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + ((Player) sender).getDisplayName() + ChatColor.GOLD + " has temporarily banned "
                                    + target.getDisplayName() + ChatColor.GOLD + " from the server for " + amountime);
                            Bukkit.broadcastMessage(ChatColor.RED + "Reason - " + b.toString());
                            BanList bl = Bukkit.getBanList(BanList.Type.NAME);
                            bl.addBan(target.getName(), b.toString(), cal.getTime(), ((Player) sender).getDisplayName());
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "You may not ban that player!");
                            return false;
                        }

                    } else {
                        if (!(otarget.hasPlayedBefore())) {
                            sender.sendMessage(ChatColor.DARK_RED + args[0] + " has never joined the server!");
                            return false;
                        } else {
                            Bukkit.broadcastMessage(((Player) sender).getDisplayName() + ChatColor.GOLD + " has temporarily banned " + ChatColor.RED + args[0] + ChatColor.GOLD + " from the server for " + amountime);
                            Bukkit.broadcastMessage(ChatColor.RED + "Reason - " + b.toString());
                            BanList bl = Bukkit.getBanList(BanList.Type.NAME);
                            bl.addBan(otarget.getName(), b.toString(), cal.getTime(), ((Player) sender).getDisplayName());
                        }
                    }
//                    BanList bl = Bukkit.getBanList(BanList.Type.NAME);
//                    bl.addBan(args[0], b.toString(), cal.getTime(), ((Player) sender).getDisplayName());
                    Logging.logToFile(("Type: TempBan Staff: " + sender.getName() + " Recipient: " + args[0] + " Reason: " + b.toString()
                            + " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj) + " UnBan Date: " + cal.getTime()), "BanLog");
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Incorrect time arguments!");
                    sender.sendMessage(ChatColor.AQUA + "1y = One Year, 1d = One Day, 1h = One Hour, 1m = 1 minute and 1s = One Second");
                    return false;
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Incorrect arguments!");
                sender.sendMessage(ChatColor.DARK_PURPLE + "/tempban [Player] [Duration] [Reason]");
                return false;
            }
            }
        }
        return true; //ends commands statement
    }



    public static int time(String a) {
        String newString = a.replaceAll("[^0-9]","");
        if (newString.equals("")) {
            return 0;
        } else {

            int amount_of_time_to_extend = Integer.parseInt(newString);
            return amount_of_time_to_extend;

        }
    }

}


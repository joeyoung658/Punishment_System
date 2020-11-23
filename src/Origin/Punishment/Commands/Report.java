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

import static Origin.Punishment.Logs.Logging.logToFile;


public class Report implements CommandExecutor {
    DateFormat d = new SimpleDateFormat("dd/MM/yy");
    DateFormat t = new SimpleDateFormat("HH:mm:ss");
    Date dateobj = new Date();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        StringBuilder b = new StringBuilder(); // Coverts args into long string
        for (int i = 1; i < args.length; i++)
            b.append(args[i] + " ");

        if (cmd.getName().equalsIgnoreCase("report")) { //no permission needed to use /report
            if (args.length == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.DARK_RED + "Incorrect Usage - /report [Player Name] [Reason]"); //if user does not add a report into the end
            } else if (args.length == 1) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.DARK_RED + "Incorrect Usage - /report [Player Name] [Reason]"); //if user does not add a report into the end
            } else {
                Player target = Bukkit.getPlayerExact(args[0]); //checks to see if first argument is online
                if (target == null) { //if player isnt not online do below
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.AQUA + args[0] + " is not online!");
                } else { //if player is online do below
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.AQUA + "Your report has been sent to online staff!"); //sends user a message telling them that their report has been sent
                    logToFile(("Player: " + sender.getName() + " Recipient: "+ args[0] +" Reason: " + b.toString() + "Date: "  + d.format(dateobj) + " Time: " + t.format(dateobj)), "ReportLog");
                    for (Player p : Bukkit.getOnlinePlayers()) { //same as above
                        if (p.hasPermission("<FP>.REPORT.R")) {
                            p.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Reports" + ChatColor.WHITE + "] " + player.getDisplayName() + ": " + b.toString());

                        }
                    }


                }
            }
        } if (cmd.getName().equalsIgnoreCase("reportdeny")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.DARK_RED + "Incorrect Usage - /reportdeny [Player Name]"); //if user does not add a report into the end
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]); //checks to see if first argument is online
                if (target == null) { //if player isnt not online do below
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.AQUA + args[0] + " is not online!");
                } else {
                    for (Player p : Bukkit.getOnlinePlayers()) { //gets all online players
                        if (p.hasPermission("<FP>.REPORT.R")) { //check if they have certain permission
                            p.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "D-Reports" + ChatColor.WHITE + "] " + ChatColor.GREEN + args[0] + "s report has been denied by " + player.getDisplayName()); //sends the message to the user
                        }
                    }
                    Bukkit.getPlayer(args[0]).sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.DARK_RED + "Your report is invalid and therefore declined. Next time please write a valid report.");

                }
            }

        } if (cmd.getName().equalsIgnoreCase("reportaccept")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f ") +ChatColor.DARK_RED + "Incorrect Usage - /reportaccept [Player Name]"); //if user does not add a report into the end
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]); //checks to see if first argument is online
                if (target == null) { //if player isnt not online do below
                    player.sendMessage(ChatColor.AQUA + args[0] + " is not online!");
                } else {
                    for (Player p : Bukkit.getOnlinePlayers()) { //gets all online players
                        if (p.hasPermission("<FP>.REPORT.R")) { //check if they have certain permission
                            p.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "A-Reports" + ChatColor.WHITE + "] " + ChatColor.GREEN + args[0] + "s report has been accepted by " + player.getDisplayName()); //sends the message to the user
                        }
                    }
                    Bukkit.getPlayer(args[0]).sendMessage(ChatColor.DARK_GREEN + "The player will be dealt with. Thank you for your report.");
                }
            }

        }
        return true; //ends commands statement
    }
}








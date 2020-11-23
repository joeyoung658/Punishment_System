//TODO Fix Dependacy from first plugin.



package Origin.Punishment.Commands;

import Origin.Punishment.Logs.Logging;
import Origin.Punishment.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static England.Origin.FirstPlugin.Player.Freeze.Frozen;

/**
 * Created by josep on 05/06/2017.
 */
public class Freeze implements CommandExecutor, Listener {

    private Origin.Punishment.Logs.Logging Logging = new Logging();
//    private ArrayList<Player> Frozen = new ArrayList<>();
    private String playername;
//
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent event) {
//
//        if (Frozen.contains(event.getPlayer())) {
//            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&' , "&e[&4Server&e]&f") + ChatColor.RED + " You have been frozen due to breaking a server /rule.");
//            event.setCancelled(true);
//        }
//
//    }



    @EventHandler
    public void onPlayerChat(PlayerCommandPreprocessEvent e) {
        List<String> cmds = Main.instance.getConfig().getStringList("frozen");
        Player p = e.getPlayer();


        if(Frozen.contains(p.getUniqueId())) {
            for (String command : cmds) {
                if (e.getMessage().startsWith("/" + command)) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "You may not use that command you are frozen due to breaking a server /rule!");
                }
            }
        }
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Player player = (Player) sender;
        String amountime;
        int amount;
        int second_amount;

        DateFormat d = new SimpleDateFormat("dd/MM/yy");
        DateFormat t = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        StringBuilder b = new StringBuilder(); // Coverts args into long string

        for (int i = 2; i < args.length; i++)
            b.append(args[i] + " ");

        if (cmd.getName().equalsIgnoreCase("freeze")) {
            if (!(sender instanceof Player)) {
                playername = sender.getName();
            } else {
                playername = ((Player) sender).getDisplayName();
            }

            if (sender.hasPermission("ps.freeze")) {

                if (args.length >= 3) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (args[1].contains("y")) {
                        amount = ftime(args[1]);
                        second_amount = amount * 31540000;
                        amountime = (amount + " Year(s)");
                    } else if (args[1].contains("d")) {
                        amount = ftime(args[1]);
                        second_amount = amount * 86400;
                        amountime = (amount + " Day(s)");
                    } else if (args[1].contains("h")) {
                        amount = ftime(args[1]);
                        second_amount = amount * 3600;
                        amountime = (amount + " Hour(s)");
                    } else if (args[1].contains("m")) {
                        amount = ftime(args[1]);
                        second_amount = amount * 60;
                        amountime = (amount + " Minute(s)");
                    } else if (args[1].contains("s")) {
                        amount = ftime(args[1]);
                        second_amount = amount;
                        amountime = (amount + " Second(s)");

                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "Incorrect time arguments!");
                        sender.sendMessage(ChatColor.AQUA + "1y = One Year, 1d = One Day, 1h = One Hour, 1m = 1 minute and 1s = One Second");
                        return false;
                    }


//                    if (!(sender.isOp())) {
//                        if (target.hasPermission("ps.np") || args[0].equalsIgnoreCase("origin658")) {
//                            sender.sendMessage(ChatColor.DARK_RED + "You may not freeze that player!");
//                            return false;
//                        }
//                    }

                    Frozen.add(target.getUniqueId());
                    Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                            + ChatColor.GOLD + " has frozen " + target.getDisplayName() + ChatColor.GOLD + " for " + amountime);
                    Bukkit.broadcastMessage(ChatColor.RED + "Reason: - " + b.toString());
                    Logging.logToFile(("Type: Freeze Staff Member: " + sender.getName() + " Dealt to: " + args[0] + " Reason: " + b.toString() +
                            " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj) + "Freeze duration: " + amountime), "FreezeLog");


                    unfreeze(target, second_amount, 0);


                } else {
                    sender.sendMessage(ChatColor.RED + "Incorrect arguments");
                    sender.sendMessage(ChatColor.AQUA + "/freeze [Player Name] [duration] [Reason]");
                    return false;
                }

            } else {
                sender.sendMessage(ChatColor.DARK_RED + "This is a Helper + only command!");
            }
        }if (cmd.getName().equalsIgnoreCase("unfreeze")) {
            if (!(sender instanceof Player)) {
                playername = sender.getName();
            } else {
                playername = ((Player) sender).getDisplayName();
            }
            if (sender.hasPermission("ps.freeze")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                            + ChatColor.GOLD + " has unfroze " + target.getDisplayName() + " !" );

                    Logging.logToFile(("Type: Thaw Staff Member: " + sender.getName() + " Dealt to: "+ args[0] +" Reason: None Given " +
                            " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "FreezeLog");
                    unfreeze(target, 0,1 );
                }  else {
                    sender.sendMessage(ChatColor.RED + "Incorrect arguments");
                    sender.sendMessage(ChatColor.AQUA + "/thaw [Player Name]");
                    return false;
                }

            } else {
                sender.sendMessage(ChatColor.DARK_RED + "This is a Helper + only command!");
            }

        }
        return true;
    }

    public void unfreeze(Player player, int time, int bypass) {

        if (bypass == 0) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                @Override
                public void run() {
                    if (Frozen.contains(player.getUniqueId())) {
                        Player target = Bukkit.getPlayerExact(player.getName());
                        Frozen.remove(player.getUniqueId());

                        if (!(target == null)) {
                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + player.getDisplayName()
                                    + ChatColor.GOLD + " has been unfrozen!");
                        }


                    }

                }
            }, 20l * time);
        } else {
            if (Frozen.contains(player.getUniqueId())) {
                Frozen.remove(player.getUniqueId());
            }
            if (Frozen.contains(player.getUniqueId())) {
                Frozen.remove(player.getUniqueId());
            }

        }


    }

    public  int ftime(String a) {


        String newString = a.replaceAll("[^0-9]","");

        if (newString.equals("")) {
            return 0;
        } else {

            int amount_of_time_to_mute = Integer.parseInt(newString);
            return amount_of_time_to_mute;

        }
    }

}

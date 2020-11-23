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
        import org.bukkit.event.player.AsyncPlayerChatEvent;
        import org.bukkit.event.player.PlayerCommandPreprocessEvent;

        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import java.util.UUID;




public class Mute implements Listener, CommandExecutor {
    public Logging Logging = new Logging();
    public static ArrayList<UUID> Muted = new ArrayList<UUID>();
    public static ArrayList<UUID> amute = new ArrayList<UUID>();
    public String playername;
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(Muted.contains(p.getUniqueId())) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED.toString() + ChatColor.ITALIC + "You are muted!");
        }
    }
    @EventHandler
    public void onPlayerChat(PlayerCommandPreprocessEvent e) {
        List<String> cmds = Main.instance.getConfig().getStringList("amutecommandblock");
        Player p = e.getPlayer();
        if(Muted.contains(p.getUniqueId())) {
            if (e.getMessage().startsWith("/me")){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You may not use that command you are muted!");
            }
        }

        if(amute.contains(p.getUniqueId())) {
            for (String command : cmds) {
                if (e.getMessage().startsWith("/" + command)) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED + "You may not use that command you are amuted!");
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

        if (cmd.getName().equalsIgnoreCase("mute")) {
            if (!(sender instanceof Player)) {
                playername = sender.getName();
            } else {
                playername = ((Player) sender).getDisplayName();
            }

                if (sender.hasPermission("ps.mute")) {

                    if (args.length >= 3) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (args[1].contains("y")) {
                            amount = time(args[1]);
                            second_amount = amount * 31540000;
                            amountime = (amount + " Year(s)");
                        } else if (args[1].contains("d")) {
                            amount = time(args[1]);
                            second_amount = amount * 86400;
                            amountime = (amount + " Day(s)");
                        } else if (args[1].contains("h")) {
                            amount = time(args[1]);
                            second_amount = amount * 3600;
                            amountime = (amount + " Hour(s)");
                        } else if (args[1].contains("m")) {
                            amount = time(args[1]);
                            second_amount = amount * 60;
                            amountime = (amount + " Minute(s)");
                        } else if (args[1].contains("s")) {
                            amount = time(args[1]);
                            second_amount = amount;
                            amountime = (amount + " Second(s)");

                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Incorrect time arguments!");
                            sender.sendMessage(ChatColor.AQUA + "1y = One Year, 1d = One Day, 1h = One Hour, 1m = 1 minute and 1s = One Second");
                            return false;
                        }


                        if (!(sender.isOp())) {
                            if (target.hasPermission("ps.np") || args[0].equalsIgnoreCase("origin658")) {
                                sender.sendMessage(ChatColor.DARK_RED + "You may not mute that player!");
                                return false;
                            }
                        }

                            Muted.add(target.getUniqueId());
                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                                    + ChatColor.GOLD + " has muted " + target.getDisplayName() + ChatColor.GOLD + " for " + amountime);
                            Bukkit.broadcastMessage(ChatColor.RED + "Reason: - " + b.toString());
                            Logging.logToFile(("Type: Mute Staff Member: " + sender.getName() + " Dealt to: " + args[0] + " Reason: " + b.toString() +
                                    " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj) + "Mute duration: " + amountime), "MuteLog");


                            unmute(target, second_amount, 0);


                    } else {
                        sender.sendMessage(ChatColor.RED + "Incorrect arguments");
                        sender.sendMessage(ChatColor.AQUA + "/mute [Player Name] [duration] [Reason]");
                        return false;
                    }

                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "This is a Helper + only command!");
                }



        } if (cmd.getName().equalsIgnoreCase("amute")) {
            if (!(sender instanceof Player)) {
                playername = sender.getName();
            } else {
                playername = ((Player) sender).getDisplayName();
            }
            if (sender.hasPermission("ps.amute")) {
                if (args.length >= 3) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (args[1].contains("y")) {
                        amount = time(args[1]);
                        second_amount = amount * 31540000;
                        amountime = (amount + " Year(s)");
                    } else if (args[1].contains("d")) {
                        amount = time(args[1]);
                        second_amount = amount * 86400;
                        amountime = (amount + " Day(s)");
                    } else if (args[1].contains("h")) {
                        amount = time(args[1]);
                        second_amount = amount * 3600;
                        amountime = (amount + " Hour(s)");
                    } else if (args[1].contains("m")) {
                        amount = time(args[1]);
                        second_amount = amount * 60;
                        amountime = (amount + " Minute(s)");
                    } else if (args[1].contains("s")) {
                        amount = time(args[1]);
                        second_amount = amount;
                        amountime = (amount + " Second(s)");

                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "Incorrect time arguments!");
                        sender.sendMessage(ChatColor.RED + "1y = One Year, 1d = One Day, 1h = One Hour, 1m = 1 minute and 1s = One Second");
                        return false;
                    }
                    if (target.hasPermission("ps.np") || args[0].equalsIgnoreCase("origin658")) {
                        sender.sendMessage(ChatColor.DARK_RED + "You may not mute that player!");
                        return false;
                    }
                    amute.add(target.getUniqueId());
                    Muted.add(target.getUniqueId());
                    Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                            + ChatColor.GOLD + " has amuted " + target.getDisplayName() + ChatColor.GOLD + " for " + amountime);
                    Bukkit.broadcastMessage(ChatColor.RED + "Reason: - " + b.toString());
                    Logging.logToFile(("Type: Mute Staff Member: " + sender.getName() + " Dealt to: " + args[0] + " Reason: " + b.toString() +
                            " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj) + "Mute duration: " + amountime), "MuteLog");
                    unmute(target, second_amount, 0);
                } else {
                    sender.sendMessage(ChatColor.RED + "Incorrect arguments");
                    sender.sendMessage(ChatColor.AQUA + "/amute [Player Name] [duration] [Reason]");
                    return false;
                }

            } else {
                sender.sendMessage(ChatColor.DARK_RED + "This is a Moderator + only command!");
            }

        } if (cmd.getName().equalsIgnoreCase("unmute")) {
            if (!(sender instanceof Player)) {
                playername = sender.getName();
            } else {
                playername = ((Player) sender).getDisplayName();
            }
                if (sender.hasPermission("ps.mute")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + playername
                                + ChatColor.GOLD + " has Unmuted " + target.getDisplayName() + ChatColor.GOLD + " early! " );

                        Logging.logToFile(("Type: Unmute Staff Member: " + sender.getName() + " Dealt to: "+ args[0] +" Reason: None Given " +
                                " Date: " + d.format(dateobj) + " Time: " + t.format(dateobj)), "MuteLog");
                        unmute(target, 0,1 );
                    }  else {
                        sender.sendMessage(ChatColor.RED + "Incorrect arguments");
                        sender.sendMessage(ChatColor.AQUA + "/unmute [Player Name]");
                        return false;
                    }

                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "This is a Helper + only command!");
                }

        }
        return true;
    }
    public void unmute(Player player, int time, int bypass) {

        if (bypass == 0) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
                @Override
                public void run() {
                    if (Muted.contains(player.getUniqueId())) {
                        Player target = Bukkit.getPlayerExact(player.getName());
                        Muted.remove(player.getUniqueId());
                        if (amute.contains(player.getUniqueId())) {
                            amute.remove(player.getUniqueId());
                        }
                        if (!(target == null)) {
                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Punishment-System" + ChatColor.WHITE + "] " + player.getDisplayName()
                                    + ChatColor.GOLD + " has been unmuted!");
                        }


                    }

                }
            }, 20l * time);
        } else {
            if (Muted.contains(player.getUniqueId())) {
                Muted.remove(player.getUniqueId());
            }
            if (amute.contains(player.getUniqueId())) {
                amute.remove(player.getUniqueId());
            }

        }


    }

    public static int time(String a) {


        String newString = a.replaceAll("[^0-9]","");

        if (newString.equals("")) {
            return 0;
        } else {

            int amount_of_time_to_mute = Integer.parseInt(newString);
            return amount_of_time_to_mute;

        }
    }
}
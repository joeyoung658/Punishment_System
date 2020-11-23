package Origin.Punishment.Commands;


import Origin.Punishment.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;


public class Admin implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (cmd.getName().equalsIgnoreCase("ps")) { //no permission needed to use /report
            if (sender.hasPermission("ps.admin")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reloadconfig")) {
                        Main.instance.saveDefaultConfig();
                        Main.instance.reloadConfig();
                        sender.sendMessage(ChatColor.AQUA + "PS Config Reloaded!");
                        return false;
                    } else {
                        sender.sendMessage(ChatColor.RED + "Incorrect arguments! Type /ps for help!");
                    }
                } if (args.length == 2){
                    if (args[1] == null) {
                        sender.sendMessage(ChatColor.RED + "Incorrect arguments! Type /ps for help!");
                        return false;
                    }
                    if (args[0].equalsIgnoreCase("addamute")) {
                        List<String> amutecommandblock = Main.instance.getConfig().getStringList("amutecommandblock");
                        if (amutecommandblock.contains(args[1])){
                            sender.sendMessage(ChatColor.RED + args[1] + " is already blocked!");
                            return false;
                        }
                        amutecommandblock.add(args[1]);
                        Main.instance.getConfig().set("amutecommandblock", amutecommandblock);
                        Main.instance.saveConfig();
                        sender.sendMessage(ChatColor.AQUA + "Commanded added to block list!");

                    } else if (args[0].equalsIgnoreCase("removeamute")) {
                        List<String> amutecommandblock = Main.instance.getConfig().getStringList("amutecommandblock");
                        if (!(amutecommandblock.contains(args[1]))){
                            sender.sendMessage(ChatColor.RED + args[1] + " is not already blocked!");
                            return false;
                        }
                        amutecommandblock.remove(args[1]);
                        Main.instance.getConfig().set("amutecommandblock", amutecommandblock);
                        Main.instance.saveConfig();
                        sender.sendMessage(ChatColor.AQUA + "Commanded added to removed from block list!");

                    } else if (args[0].equalsIgnoreCase("addcmdblock")) {
                        List<String> BlockedCommands = Main.instance.getConfig().getStringList("BlockedCommands");
                        if (BlockedCommands.contains(args[1])){
                            sender.sendMessage(ChatColor.RED + args[1] + " is already blocked!");
                            return false;
                        }
                        BlockedCommands.add(args[1]);
                        Main.instance.getConfig().set("BlockedCommands", BlockedCommands);
                        Main.instance.saveConfig();
                        sender.sendMessage(ChatColor.AQUA + "Commanded added to block list!");

                    } else if (args[0].equalsIgnoreCase("rmcmdblock")) {
                        List<String> BlockedCommands = Main.instance.getConfig().getStringList("BlockedCommands");
                        if (!(BlockedCommands.contains(args[1]))) {
                            sender.sendMessage(ChatColor.RED + args[1] + " is not already blocked!");
                            return false;
                        }
                        BlockedCommands.remove(args[1]);
                        Main.instance.getConfig().set("BlockedCommands", BlockedCommands);
                        Main.instance.saveConfig();
                        sender.sendMessage(ChatColor.AQUA + "Commanded added to removed from block list!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Incorrect arguments, type /ps for help!");
                    }
                } else {
                    sender.sendMessage(ChatColor.AQUA + "Admin Punishment system commands.");
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ps [reloadconfig] - Reloads config file");
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ps addamute [command]: Adds a command which is blocked while amuted.");
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ps removeamute [command]: Removes a command which is blocked while amuted.");
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ps addcmdblock [command]: Adds a command which is blocked globally.");
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "/ps rmcmdblock [command]: Remove a command which is blocked globally.");
                }

            }
            else {

                sender.sendMessage(ChatColor.RED + "This is an admin only command!");
            }
        }

        return true; //ends commands statement
    }
}


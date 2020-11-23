package Origin.Punishment;


import England.Origin.FirstPlugin.Player.Freeze;
import Origin.Punishment.Commands.*;
import Origin.Punishment.Listeners.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;


public class Main extends JavaPlugin implements Listener{
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        this.reloadConfig();
        registerCommands();
        registerListeners();
        getLogger().info("The Punishment System has been enabled!");
    }


    private void registerCommands(){
        //commands
        //ban
        this.getCommand("ban").setExecutor(new Ban());
        //kick
        this.getCommand("kick").setExecutor(new Kick());
        //unban
        this.getCommand("unban").setExecutor(new UnBan());
        //IPban
        this.getCommand("banip").setExecutor(new IPBan());
        //tempban
        this.getCommand("tempban").setExecutor(new TempBan());
        //UnBanIP
        this.getCommand("unbanip").setExecutor(new UnBanIP());
        //ClearChat
        this.getCommand("clearchat").setExecutor(new ClearChat());
        //Mute
        this.getCommand("mute").setExecutor(new Mute());
        this.getCommand("amute").setExecutor(new Mute());
        this.getCommand("unmute").setExecutor(new Mute());
        getServer().getPluginManager().registerEvents(new Mute(), this);
        //Read
        this.getCommand("logread").setExecutor(new LogRead());
        //Admin Commands
        this.getCommand("ps").setExecutor(new Admin());
        //Reporting Commands
        this.getCommand("report").setExecutor(new Report());
        this.getCommand("reportdeny").setExecutor(new Report());
        this.getCommand("reportaccept").setExecutor(new Report());
        this.getCommand("freeze").setExecutor(new Origin.Punishment.Commands.Freeze());
        this.getCommand("unfreeze").setExecutor(new Origin.Punishment.Commands.Freeze());

    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new IsBanned(), this);
        getServer().getPluginManager().registerEvents(new WhiteList(), this);
        getServer().getPluginManager().registerEvents(new JoinError(), this);
        getServer().getPluginManager().registerEvents(new AntiIP(), this);
        getServer().getPluginManager().registerEvents(new AntiSpam(), this);
        getServer().getPluginManager().registerEvents(new CommandBlock(), this);
        getServer().getPluginManager().registerEvents(new ChatLog(), this);
        getServer().getPluginManager().registerEvents(new Freeze(), this);
    }
    @Override
    public void onDisable() { //When plugin ends
        getLogger().info("Punishment-System has been disabled");
    }
}

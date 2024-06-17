package top.vrilhyc.plugins.customrighten;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import top.vrilhyc.plugins.customrighten.commands.MainCommand;

public final class CustomRighten extends JavaPlugin {
    public static PluginInitial pi;
    @Override
    public void onEnable() {
        // Plugin startup logic
        pi = new PluginInitial(this);
        pi.onEnable();
        pi.loadConfig();
        loadData();
        Bukkit.getPluginManager().registerEvents(new MainListener(),this);
        getCommand("customrighten").setExecutor(new MainCommand());
    }

    public static void loadData(){
        RightableItem.loadData();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        pi.onDisable();
    }

    public static String Auto(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}

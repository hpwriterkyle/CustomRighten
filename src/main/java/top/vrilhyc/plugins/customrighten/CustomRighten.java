package top.vrilhyc.plugins.customrighten;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomRighten extends JavaPlugin {
    public static PluginInitial pi;
    @Override
    public void onEnable() {
        // Plugin startup logic
        pi = new PluginInitial(this);
        pi.onEnable();
        pi.loadConfig();
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

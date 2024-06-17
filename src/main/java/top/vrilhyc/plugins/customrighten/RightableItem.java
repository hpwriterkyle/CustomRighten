package top.vrilhyc.plugins.customrighten;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.vrilhyc.plugins.customrighten.tools.CommandBundle;
import top.vrilhyc.plugins.customrighten.tools.RightenCommandBundle;

import java.util.HashMap;
import java.util.Map;

public class RightableItem implements Rightable{
    private String name;
    private String displayName;
    protected CommandBundle commandBundle;
    public static Map<String,RightableItem> primitiveMap = new HashMap<>();
    public static Map<String,RightableItem> displayNameMap = new HashMap<>();

    public RightableItem(String s){
        this.name = s;
    }

    public RightableItem loadFromData(ConfigurationSection configurationSection){
        String path = name+".";
        displayName = CustomRighten.Auto(configurationSection.getString(path+"name",""));
        commandBundle = new RightenCommandBundle(configurationSection.getStringList(path+"commands"));
        return this;
    }

    public static void use(Player player,ItemStack is){
        ItemMeta im = is.getItemMeta();
        if(im==null){
            return;
        }
        if(!im.hasDisplayName()){
            return;
        }
        RightableItem rightableItem = displayNameMap.get(im.getDisplayName());
        rightableItem.commandBundle.unbundle(player);
        is.setAmount(0);
    }

    @Override
    public String getPrimitiveName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public CommandBundle getCommandBundle() {
        return commandBundle;
    }

    public boolean register(){
        if(primitiveMap.containsKey(this.name)){
            return false;
        }
        primitiveMap.put(this.name,this);
        displayNameMap.put(this.displayName,this);
        return true;
    }

    public static void loadData(){
        primitiveMap.clear();
        displayNameMap.clear();
        ConfigurationSection configurationSection = PluginInitial.plugin.getConfig().getConfigurationSection("items");
        for(String s:configurationSection.getKeys(false)){
            new RightableItem(s).loadFromData(configurationSection).register();
        }
    }
}

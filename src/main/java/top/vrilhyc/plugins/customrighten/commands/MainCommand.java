package top.vrilhyc.plugins.customrighten.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import top.vrilhyc.plugins.customrighten.CustomRighten;
import top.vrilhyc.plugins.customrighten.PluginInitial;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MainCommand implements CommandExecutor, TabCompleter {
    public static Map<UUID,Status> statusMap = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SubCommandParser subCommandParser = new SubCommandParser(getClass(),this,sender,command,label,args);
        try {
            subCommandParser.parse();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @SubCommand(paramsLength = 0, commandName = "reload",permissions = {},opped = true)
    public void reloads(CommandSender sender,Command command,String label,String[] args){
        PluginInitial.plugin.reloadConfig();
        CustomRighten.loadData();
        sender.sendMessage(CustomRighten.Auto("&a其插件已重载!"));
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings==null||strings.length<=1){
            return Arrays.stream(getClass().getMethods()).filter(a->a.isAnnotationPresent(SubCommand.class)).filter(a->commandSender.isOp()||!a.getAnnotation(SubCommand.class).opped()).map(a->a.getAnnotation(SubCommand.class).commandName()).filter(a->strings==null||(strings.length==1&&a.startsWith(strings[0]))).collect(Collectors.toList());
        }
        return null;
    }


    public enum StatusType{
        TAKING,
        REMOVING;

        public void commit(Player player){
            Status status = statusMap.getOrDefault(player.getUniqueId(),new Status());
            status.getStatus().add(this);
            statusMap.put(player.getUniqueId(),status);
        }

        public void remove(Player player){
            Status status = statusMap.getOrDefault(player.getUniqueId(),new Status());
            status.getStatus().remove(this);
            statusMap.put(player.getUniqueId(),status);
        }

        public boolean equip(Player player){
            Status status = statusMap.get(player.getUniqueId());
            if(status==null){
                return false;
            }
            return status.getStatus().contains(this);
        }
    }
}


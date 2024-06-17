package top.vrilhyc.plugins.customrighten.tools;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import top.vrilhyc.plugins.customrighten.CustomRighten;

import java.util.Collection;

public class RightenCommandBundle implements CommandBundle {
    private Collection<String> commands;

    public RightenCommandBundle(Collection<String> commands){
        this.commands = commands;
    }

    @Override
    public void unbundle(Player p) {
        if(commands==null){
            return;
        }
        commands.forEach(a-> Bukkit.dispatchCommand(Bukkit.getConsoleSender(),replaced(CustomRighten.Auto(a),p)));
    }

    private String replaced(String s,Player p){
        return s.replace("%player%",p.getName());
    }
}

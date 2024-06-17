package top.vrilhyc.plugins.customrighten;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MainListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction()!= Action.RIGHT_CLICK_AIR&&e.getAction()!= Action.RIGHT_CLICK_BLOCK){
            return;
        }
        ItemStack is = e.getItem();
        RightableItem.use(e.getPlayer(),is);
    }
}

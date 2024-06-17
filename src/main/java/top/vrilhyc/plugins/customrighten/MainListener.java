package top.vrilhyc.plugins.customrighten;

import com.sun.tools.javac.jvm.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
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

    @EventHandler
    public void onRevive(EntityResurrectEvent e){
        if (!(e.getEntity() instanceof Player)){
            return;
        }
        Player dier = (Player)e.getEntity();
        dier.setHealth(dier.getMaxHealth());
        new Thread(() -> {
            try {
                Thread.sleep(50);
                dier.setHealth(dier.getMaxHealth());
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }).start();


    }
}

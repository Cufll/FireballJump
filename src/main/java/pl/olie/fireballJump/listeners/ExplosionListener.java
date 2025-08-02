package pl.olie.fireballJump.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.olie.fireballJump.FireballJump;
import pl.olie.fireballJump.config.Config;

public class ExplosionListener implements Listener {
    private final Config config;
    public ExplosionListener(FireballJump plugin){
        this.config = plugin.getConfigValues();
    }
    @EventHandler(priority= EventPriority.HIGHEST)
    public void onExplosion(EntityExplodeEvent e){
        if (e.getEntity() instanceof Fireball) {
            for (Block block : e.blockList()) {
                if (config.getAllowedBlocks().contains((block.getType().name()))) {
                    block.breakNaturally();
                }
            }
            e.blockList().removeIf(block -> !config.getAllowedBlocks().contains((block.getType().name())));
        }
    }
}

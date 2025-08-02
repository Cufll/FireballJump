package pl.olie.fireballJump.listeners;

import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import pl.olie.fireballJump.FireballJump;
import pl.olie.fireballJump.config.Config;

public class FireballIgniteListener implements Listener {
    private final Config config;
    public FireballIgniteListener(FireballJump plugin){
        this.config = plugin.getConfigValues();
    }
    @EventHandler
    public void onBlockFireball(BlockIgniteEvent e){
        if(!config.getIgniteBlock()){
            if(e.getCause() == BlockIgniteEvent.IgniteCause.FIREBALL || e.getIgnitingEntity() instanceof Fireball){
                e.setCancelled(true);
            }
        }
    }
}

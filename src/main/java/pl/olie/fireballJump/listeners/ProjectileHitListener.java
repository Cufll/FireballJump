package pl.olie.fireballJump.listeners;

import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import pl.olie.fireballJump.FireballJump;

public class ProjectileHitListener implements Listener {
    private final FireballJump plugin;
    public ProjectileHitListener(FireballJump plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Fireball) {
            plugin.getFireballSpawnTimes().remove(event.getEntity().getUniqueId());
        }
    }

}

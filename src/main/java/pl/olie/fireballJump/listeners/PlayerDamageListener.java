package pl.olie.fireballJump.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import pl.olie.fireballJump.FireballJump;
import pl.olie.fireballJump.config.Config;

public class PlayerDamageListener implements Listener {
    private final Config config;
    private final FireballJump plugin;
    public PlayerDamageListener(FireballJump plugin){
        this.plugin = plugin;
        this.config = plugin.getConfigValues();
    }
    @EventHandler
    public void onFireballHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Fireball fireball) {
            if (fireball.getShooter() instanceof Player) {
                Entity entity = event.getEntity();
                if (!(entity instanceof LivingEntity)) return;
                event.setDamage(config.getDamage());
                Vector direction = entity.getLocation().toVector().subtract(fireball.getLocation().toVector()).normalize();
                Vector knockback = direction.multiply(config.getHorizontalPower());
                knockback.setY(config.getVerticalPower());
                entity.setFallDistance(0);
                Bukkit.getScheduler().runTask(plugin, () -> {
                    entity.setVelocity(knockback);
                });
            }
        }
    }
}

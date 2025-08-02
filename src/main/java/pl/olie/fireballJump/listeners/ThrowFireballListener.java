package pl.olie.fireballJump.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.olie.fireballJump.FireballJump;
import pl.olie.fireballJump.config.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ThrowFireballListener implements Listener {
    private final Config config;
    private final FireballJump plugin;
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    public ThrowFireballListener(FireballJump plugin){
        this.plugin = plugin;
        this.config = plugin.getConfigValues();
    }
    @EventHandler
    public void interact(PlayerInteractEvent e){
        Action eventAction = e.getAction();
        Player player = e.getPlayer();
        UUID id = player.getUniqueId();
        long now = System.currentTimeMillis();
        if(cooldowns.containsKey(id)){
            long last = cooldowns.get(id);
            if(now - last < config.getColldown()){
                return;
            }
        }
        cooldowns.put(id, now);
        if (eventAction == Action.RIGHT_CLICK_AIR || eventAction == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.FIRE_CHARGE || player.getInventory().getItemInOffHand().getType() == Material.FIRE_CHARGE) {
                e.setCancelled(true);
                Fireball fireball = player.launchProjectile(Fireball.class);
                plugin.getFireballSpawnTimes().put(fireball.getUniqueId(), System.currentTimeMillis());
                fireball.setVelocity(player.getLocation().getDirection().multiply(0.4));
                fireball.setInvulnerable(false);
                fireball.setIsIncendiary(false);
                fireball.setYield(2);
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    player.getInventory().removeItem(new ItemStack(Material.FIRE_CHARGE, 1));
                }
            }
        }
    }
}

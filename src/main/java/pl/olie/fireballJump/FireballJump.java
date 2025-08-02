package pl.olie.fireballJump;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import pl.olie.fireballJump.config.Config;
import pl.olie.fireballJump.listeners.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class FireballJump extends JavaPlugin {
    private Config configValues;
    final Map<UUID, Long> fireballSpawnTimes = new HashMap<>();
    public Map<UUID, Long> getFireballSpawnTimes(){return fireballSpawnTimes;}
    public Config getConfigValues(){
        return configValues;
    }
    @Override
    public void onEnable() {
        configValues = new Config(this);
        FireballCommand fireballCommand = new FireballCommand(this);
        Objects.requireNonNull(getCommand("fireball")).setExecutor(new FireballCommand(this));
        getServer().getPluginManager().registerEvents(new ThrowFireballListener(this), this);
        getServer().getPluginManager().registerEvents(new ProjectileHitListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new FireballIgniteListener(this), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(this), this);
        saveDefaultConfig();
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            long now = System.currentTimeMillis();

            fireballSpawnTimes.entrySet().removeIf(entry -> {
                UUID uuid = entry.getKey();
                long spawnTime = entry.getValue();
                if (now - spawnTime > configValues.getFireballDespawnRate()*1000) {
                    Entity entity = Bukkit.getEntity(uuid);
                    if (entity != null && !entity.isDead()) {
                        entity.remove();
                    }
                    return true;
                }
                return false;
            });
        }, 20L, 20L);

    }
    @Override
    public void onDisable() {
    }
}

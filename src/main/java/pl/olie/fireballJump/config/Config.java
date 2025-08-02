package pl.olie.fireballJump.config;

import pl.olie.fireballJump.FireballJump;

import java.util.List;

public class Config {

    private final FireballJump plugin;
    public Config(FireballJump plugin) {
        this.plugin = plugin;
        loadConfigValues();
    }
    private List<String> allowedBlocks;
    private double horizontalPower;
    private double verticalPower;
    private double damage;
    private double fireballDespawnRate;
    private boolean igniteBlock;
    private int colldown;
    public void loadConfigValues() {
        this.horizontalPower = plugin.getConfig().getDouble("horizontal-kb");
        this.verticalPower = plugin.getConfig().getDouble("vertical-kb");
        this.damage = plugin.getConfig().getDouble("damage");
        this.fireballDespawnRate = plugin.getConfig().getDouble("despawn-rate");
        this.allowedBlocks = plugin.getConfig().getStringList("Allowed-blocks");
        this.igniteBlock = plugin.getConfig().getBoolean("Ignite-block");
        this.colldown = plugin.getConfig().getInt("colldown");
    }
    public double getHorizontalPower(){return horizontalPower;}
    public double getVerticalPower(){return verticalPower;}
    public double getDamage(){return damage;}
    public double getFireballDespawnRate(){return fireballDespawnRate;}
    public List<String> getAllowedBlocks(){return allowedBlocks;}
    public boolean getIgniteBlock(){return igniteBlock;}
    public int getColldown(){return colldown;}

}

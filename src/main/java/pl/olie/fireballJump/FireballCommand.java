package pl.olie.fireballJump;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pl.olie.fireballJump.config.Config;

import java.util.Arrays;
import java.util.List;

public class FireballCommand implements CommandExecutor, TabCompleter {
    private final Config config;
    private final FireballJump plugin;
    public FireballCommand(FireballJump plugin){
        this.plugin = plugin;
        this.config = plugin.getConfigValues();
    }
    public boolean onCommand(CommandSender sender,Command cmd,String label, String[] args) {
        if(args.length == 1){
            if (args[0].equalsIgnoreCase("reload")){
                if (!(sender instanceof Player player)) {
                    sender.sendMessage("§aOnly players can use this command.");
                    return true;
                }
                if(!player.hasPermission("fireball.reload")){
                    player.sendMessage("§cYou do not have permission to use this command.");
                    return true;
                }else {
                    plugin.reloadConfig();
                    config.loadConfigValues();
                    player.sendMessage("§aConfig reloaded!");
                }
                return true;
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String label,String[] args) {
        if(sender instanceof Player player) {

            if(args.length == 1 && player.hasPermission("fireball.reload")) {
                return List.of("reload");
            }

        }

        return null;
    }
}

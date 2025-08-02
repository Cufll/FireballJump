package pl.olie.fireballJump;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FireballCommand implements CommandExecutor {
    private final FireballJump plugin;
    public FireballCommand(FireballJump plugin){
        this.plugin = plugin;
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
                    plugin.getConfigValues();
                    sender.sendMessage("§aConfig reloaded!");
                }
            }
        }
        return false;
    }
}

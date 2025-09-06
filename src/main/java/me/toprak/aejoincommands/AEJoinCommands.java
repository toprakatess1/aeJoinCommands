package me.toprak.aejoincommands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AEJoinCommands extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("AEJoinCommands calısıyo (means loaded)");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            FileConfiguration config = getConfig();
            List<String> commands = config.getStringList("first-join-commands");
            for (String cmd : commands) {
                String parsedCmd = cmd.replace("%player%", event.getPlayer().getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsedCmd);
            }
        }
    }
}

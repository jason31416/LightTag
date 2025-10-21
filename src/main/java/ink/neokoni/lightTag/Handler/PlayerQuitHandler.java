package ink.neokoni.lightTag.Handler;

import ink.neokoni.lightTag.LightTag;
import ink.neokoni.lightTag.PAPIs.PAPIsCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitHandler implements Listener {
    public PlayerQuitHandler(LightTag plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerExit(PlayerQuitEvent e) {
        PAPIsCore.clear(e.getPlayer());
    }
}

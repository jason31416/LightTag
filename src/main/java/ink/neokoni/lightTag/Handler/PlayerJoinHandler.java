package ink.neokoni.lightTag.Handler;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        YamlConfiguration data = PlayerDatas.getPlayerData();
        if (!data.isSet(player.getUniqueId()+".using")) {
            data.set(player.getUniqueId()+".using", "0");
        }
    }
}

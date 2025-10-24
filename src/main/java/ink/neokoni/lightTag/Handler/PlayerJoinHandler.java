package ink.neokoni.lightTag.Handler;

import ink.neokoni.lightTag.DataStorage.Configs;
import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.LightTag;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerJoinHandler implements Listener {
    public PlayerJoinHandler(LightTag plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        YamlConfiguration data = PlayerDatas.getPlayerData();
        YamlConfiguration config = Configs.getConfigs();
        if (!data.isSet(player.getUniqueId()+".using")) {
            data.set(player.getUniqueId()+".using", config.getInt("init-tag"));
        }

        if (config.getInt("init-tag") > -1 && data.getIntegerList(player.getUniqueId()+".owns").isEmpty()) {
            List<Integer> ownedTags = new ArrayList<>();
            ownedTags.add(config.getInt("init-tag"));
            data.set(player.getUniqueId()+".owns", ownedTags);
        }

        PlayerDatas.savePlayerData(data);
    }
}

package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.PAPIs.PAPIsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ClearTag {
    public ClearTag(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return;
        }

        Player player = (Player) sender;
        YamlConfiguration data = PlayerDatas.getPlayerData();
        data.set(player.getUniqueId()+".using", -1);
        PlayerDatas.savePlayerData(data);

        PAPIsCore.clear(player);
    }
}

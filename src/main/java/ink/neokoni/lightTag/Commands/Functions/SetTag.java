package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.Languages;
import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.PAPIs.PAPIsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetTag {
    public SetTag(CommandSender sender, int id) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Languages.getMessage("system.player-only"));
            return;
        }

        YamlConfiguration playerData = PlayerDatas.getPlayerData();
        if (!playerData.getIntegerList(player.getUniqueId()+".owns").contains(id)) {
            player.sendMessage(Languages.getMessage("tag.not-have"));
            return;
        }

        playerData.set(player.getUniqueId()+".using", id);
        PAPIsCore.clear(player);
        player.sendMessage(Languages.getMessage("tag.successes"));
    }
}

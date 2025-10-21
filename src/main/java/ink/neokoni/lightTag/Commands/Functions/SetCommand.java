package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.Utils.TextUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class SetCommand {
    public SetCommand(Player player, int id) {
        YamlConfiguration data = PlayerDatas.getPlayerData();

        List<Integer> ownedId = data.getIntegerList(player.getUniqueId()+".owns");

        if (!ownedId.contains(id)) {
            player.sendMessage(TextUtils.getFormatedLang("tag.not-have"));
            return;
        }

        data.set(player.getUniqueId()+".using", id);

        PlayerDatas.savePlayerData(data);
        player.sendMessage(TextUtils.getFormatedLang("tag.successes"));
    }
}
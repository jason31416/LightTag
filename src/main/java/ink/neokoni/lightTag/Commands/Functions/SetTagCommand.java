package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.PAPIs.PAPIsCore;
import ink.neokoni.lightTag.Utils.TextUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetTagCommand {
    public SetTagCommand(CommandSender sender, int id) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(TextUtils.getFormatedLang("system.player-only"));
            return;
        }

        Player player = (Player) sender;

        YamlConfiguration playerData = PlayerDatas.getPlayerData();
        if (!playerData.getIntegerList(player.getUniqueId()+".owns").contains(id)) {
            player.sendMessage(TextUtils.getFormatedLang("tag.not-have"));
            return;
        }

        playerData.set(player.getUniqueId()+".using", id);
        PAPIsCore.clear(player);
        player.sendMessage(TextUtils.getFormatedLang("tag.successes"));
    }
}

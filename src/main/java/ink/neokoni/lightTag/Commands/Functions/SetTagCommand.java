package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.PAPIs.PAPIsCore;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetTagCommand {
    public SetTagCommand(CommandSender sender, int id) {
        if (!(sender instanceof Player)) {
            // todo: noly player can run. at least now
            return;
        }

        Player player = (Player) sender;

        YamlConfiguration playerData = PlayerDatas.getPlayerData();
        if (!playerData.getIntegerList(player.getUniqueId()+".owns").contains(id)) {
            // todo: player not have this tag
            return;
        }

        playerData.set(player.getUniqueId()+".using", id);
        PAPIsCore.clear(player);
        // todo: tips set success
    }
}

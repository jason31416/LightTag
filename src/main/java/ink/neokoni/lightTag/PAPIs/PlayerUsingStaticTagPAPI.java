package ink.neokoni.lightTag.PAPIs;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.DataStorage.Tags;
import ink.neokoni.lightTag.Utils.TagUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;

public class PlayerUsingStaticTagPAPI {
    private static Map<Player, String> tagTask;
    private Component tag;

    private String banner;

    public PlayerUsingStaticTagPAPI(Player player) {
        YamlConfiguration playerData = PlayerDatas.getPlayerData();

        String using = playerData.getString(player.getUniqueId()+".using");

        YamlConfiguration tagInfo =Tags.getTags();
        if (using==null||using.equals("")||Integer.valueOf(using)<0) {
            tag=Component.text("");
            return;
        }

        tag = TagUtils.getViewById(Integer.valueOf(using));
    }

    public String get() {
        return LegacyComponentSerializer.legacySection().serialize(tag);
    }
}

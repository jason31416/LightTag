package ink.neokoni.lightTag.PAPIs;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.DataStorage.Tags;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
        if (using==null||using.equals("")) {
            tag=null;
            return;
        }

        switch (tagInfo.getString(using+".type")) {
            case "STATIC": {
                tag = MiniMessage.miniMessage().deserialize(tagInfo.getString(using+".content"));
                return;
            }
            case "ANIMATION": {
                tag = MiniMessage.miniMessage().deserialize(tagInfo.getString(using+".banner"));
                return;
            }
            default: {
                banner = tagInfo.getString(using+".banner");
                if (banner==null) {
                    banner = tagInfo.getStringList(using+".content").getFirst();
                }
                if (banner==null) {
                    banner = tagInfo.getString(using+".content");
                }
                if (banner!=null) {
                    tag = MiniMessage.miniMessage().deserialize(banner);
                }
            }
        }
    }

    public String get() {
        return LegacyComponentSerializer.legacySection().serialize(tag);
    }
}

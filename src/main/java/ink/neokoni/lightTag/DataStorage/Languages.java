package ink.neokoni.lightTag.DataStorage;

import cn.jason31416.planetlib.message.Message;
import cn.jason31416.planetlib.message.MessageLoader;
import cn.jason31416.planetlib.util.Util;
import ink.neokoni.lightTag.LightTag;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Languages {
    private static MessageLoader language;

    public static void loadLanguage() {
        Util.savePluginResource("lang.yml");

        File langFile = new File(LightTag.getInstance().getDataFolder(), "lang.yml");
        language = new MessageLoader(langFile);
    }

    public static Message getMessage(String key) {
        return language.getMessage(key, "Unknown message key: "+key);
    }
}

package ink.neokoni.lightTag.Utils;

import ink.neokoni.lightTag.DataStorage.Languages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class TextUtils {
    public static String getLang(String path) {
        return Languages.getLanguages().getString(path);
    }

    public static Component getFormatedLang(String path) {
        return MiniMessage.miniMessage().deserialize(getLang(path));
    }

    public static String getLang(String path, String key, String value) {
        return Languages.getLanguages().getString(path.replace(key, value));
    }

    public static Component getFormatedLang(String path, String key, String value) {
        return MiniMessage.miniMessage().deserialize(getLang(path, key, value));
    }
}

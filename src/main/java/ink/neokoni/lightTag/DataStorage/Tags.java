package ink.neokoni.lightTag.DataStorage;

import ink.neokoni.lightTag.LightTag;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Tags {
    private static File pluginFolder = LightTag.getInstance().getDataFolder();
    private static LightTag plugin = LightTag.getInstance();
    private static YamlConfiguration tags = new YamlConfiguration();
    public static boolean isTagsExist() {
        return new File(pluginFolder, "tags.yml").exists();
    }

    public static void createTagsFile() {
        plugin.saveResource("tags.yml", false);
    }

    public static void loadTags() {
        if (!isTagsExist()) {
            createTagsFile();
            return;
        }

        File tagsFile = new File(pluginFolder, "tags.yml");
        tags = YamlConfiguration.loadConfiguration(tagsFile);
    }


    public static YamlConfiguration getTags() {
        return tags;
    }
}

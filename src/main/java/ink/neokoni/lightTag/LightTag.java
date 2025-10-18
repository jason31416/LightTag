package ink.neokoni.lightTag;

import ink.neokoni.lightTag.Commands.Commands;
import ink.neokoni.lightTag.DataStorage.Configs;
import ink.neokoni.lightTag.DataStorage.Languages;
import ink.neokoni.lightTag.PAPIs.PAPIsCore;
import org.bukkit.plugin.java.JavaPlugin;

public final class LightTag extends JavaPlugin {
    private static LightTag instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Configs.loadConfig();
        Languages.loadLanguage();

        new Commands();

        new PAPIsCore().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static LightTag getInstance() {
        return instance;
    }
}

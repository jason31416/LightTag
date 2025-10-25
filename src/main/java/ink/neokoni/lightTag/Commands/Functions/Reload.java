package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.Configs;
import ink.neokoni.lightTag.DataStorage.Languages;
import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.DataStorage.Tags;
import ink.neokoni.lightTag.PAPIs.PAPIsCore;
import org.bukkit.command.CommandSender;

public class Reload {
    public Reload(CommandSender sender) {
        Configs.loadConfig();
        Languages.loadLanguage();
        Tags.loadTags();
        PlayerDatas.loadPlayerData();

        PAPIsCore.clearCache();

        if (sender!=null) {
            sender.sendMessage(Languages.getMessage("system.reload-success"));
        }
    }
}

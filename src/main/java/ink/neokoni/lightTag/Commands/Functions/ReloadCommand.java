package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.Configs;
import ink.neokoni.lightTag.DataStorage.Languages;
import ink.neokoni.lightTag.Utils.TextUtils;
import org.bukkit.command.CommandSender;

public class ReloadCommand {
    public ReloadCommand(CommandSender sender) {
        Configs.loadConfig();
        Languages.loadLanguage();

        if (sender!=null) {
            sender.sendMessage(TextUtils.getFormatedLang("system.reload-success"));
        }
    }
}

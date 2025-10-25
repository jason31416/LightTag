package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.Languages;
import ink.neokoni.lightTag.DataStorage.Tags;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;
import java.util.List;

public class AddTag {
    public AddTag(String content, CommandSender sender) {
        YamlConfiguration tags = Tags.getTags();
        int maxId = -1;

        for (String cur : tags.getKeys(false)) {
            int cur_i = Integer.valueOf(cur);

            if(cur_i>maxId)maxId=cur_i;
        }
        int newTagId = maxId+1;

        tags.set(newTagId+".type", "STATIC");
        tags.set(newTagId+".content", content);

        sender.sendMessage(Languages.getMessage("tag.added"));
    }

    public AddTag(String content, String banner, int delay, CommandSender sender) {
        YamlConfiguration tags = Tags.getTags();
        int maxId = -1;

        for (String cur : tags.getKeys(false)) {
            int cur_i = Integer.valueOf(cur);

            if(cur_i>maxId)maxId=cur_i;
        }
        int newTagId = maxId+1;

        List<String> tagContent = Arrays.stream(content.split(",")).toList();

        tags.set(newTagId+".type", "ANIMATION");
        tags.set(newTagId+".banner", banner);
        tags.set(newTagId+".delay", delay);
        tags.set(newTagId+".content", tagContent);

        sender.sendMessage(Languages.getMessage("tag.added"));
    }
}

package ink.neokoni.lightTag.Utils;

import ink.neokoni.lightTag.DataStorage.Tags;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class TagUtils {
    public static Component[] getTagContentById(int id) {
        YamlConfiguration tagsInfo = Tags.getTags();
        if (tagsInfo.getString(id+".content")==null) {
            return null;
        }

        String tagType = tagsInfo.getString(id+".type");
        switch (tagType) {
            case "STATIC" :{
                return new Component[]{MiniMessage.miniMessage().deserialize(tagsInfo.getString(id+".content"))};
            }
            case "ANIMATION" : {
                List<String> frame = tagsInfo.getStringList(id+".content");
                Component frameComponent[] = new Component[frame.size()];
                for (int i =0;i< frame.size();i++) {
                    frameComponent[i] = MiniMessage.miniMessage().deserialize(frame.get(i));
                }
                return frameComponent;
            }
            default: {
                List<String> s = new ArrayList<>();
                String singleContent = tagsInfo.getString(id+".content");

                if (singleContent != null) {
                    return new Component[]{MiniMessage.miniMessage().deserialize(singleContent)};
                }

                s = tagsInfo.getStringList(id+".content");
                Component components[] = new Component[s.size()];
                for (int i = 0; i < s.size(); i++) {
                    components[i] = MiniMessage.miniMessage().deserialize(s.get(i));
                }
                return components;
            }
        }
    }
}



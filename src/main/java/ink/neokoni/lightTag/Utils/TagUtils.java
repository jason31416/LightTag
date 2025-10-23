package ink.neokoni.lightTag.Utils;

import ink.neokoni.lightTag.DataStorage.Tags;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    
    public static String getTypeById(int id) {
        String type = Tags.getTags().getString(id+".type");
        return type==null?"null" : type;
    }
    
    public static Component getViewById(int id) {
        String type = getTypeById(id);
        switch (type) {
            case "STATIC": {
                return MiniMessage.miniMessage().deserialize(Tags.getTags().getString(id+".content"));
            }
            case "ANIMATION": {
                return MiniMessage.miniMessage().deserialize(Tags.getTags().getString(id+".banner"));
            }
            default: {
                String banner = Tags.getTags().getString(id+".banner");
                try {
                    banner = Tags.getTags().getStringList(id+".content").getFirst();
                } catch (NoSuchElementException e) {
                    Bukkit.getLogger().warning("Tag id: "+id+" not have type and not animation");
                }
//                if (banner==null) {
//                    banner = Tags.getTags().getStringList(id+".content").getFirst();
//                }
                if (banner==null) {
                    banner = Tags.getTags().getString(id+".content");
                }
                if (banner!=null) {
                    return MiniMessage.miniMessage().deserialize(banner);
                }
                return Component.text("null"); // i not want null pointer
            }
        }
    }
}



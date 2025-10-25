package ink.neokoni.lightTag.Commands.Functions;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.DataStorage.Tags;
import ink.neokoni.lightTag.Utils.TagUtils;
import ink.neokoni.lightTag.Utils.TextUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SendPlayerTagList {
    public SendPlayerTagList(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(TextUtils.getFormatedLang("system.player-only"));
            return;
        }

        List<Integer> originData = PlayerDatas.getPlayerData().getIntegerList(player.getUniqueId()+".owns");
        List<Integer> ownedTags = new ArrayList<>();

        for (int i : originData) {
            if (i > -1) {
                ownedTags.add(i);
            }
        }

        Component head = TextUtils.getFormatedLang("list.head", "{total}", String.valueOf(ownedTags.size()));

        if (ownedTags.isEmpty()) {
            player.sendMessage(TextUtils.getFormatedLang("list.nothing"));
            return;
        }

        player.sendMessage(head);
        for (int i : ownedTags) {
            Component tagView = TagUtils.getViewById(i);
            String type = Tags.getTags().getString(i+".type");
            boolean isAnimation = (type != null && type.equals("ANIMATION"));

            Component hover = MiniMessage.miniMessage().deserialize("称号: ").append(tagView).appendNewline()
                    .append(MiniMessage.miniMessage().deserialize(isAnimation?"<yellow>动态称号":"<red>静态称号").appendNewline())
                    .append(MiniMessage.miniMessage().deserialize("ID: "+i).appendNewline())
                    .append(MiniMessage.miniMessage().deserialize("").appendNewline())
                    .append(MiniMessage.miniMessage().deserialize("点击使用"));

            player.sendMessage(Component.text("- ID:"+i+" ").append(
                    TagUtils.getViewById(i)
                            .hoverEvent(HoverEvent.showText(hover))
                            .clickEvent(ClickEvent.runCommand("/lighttag:ltag set "+i))
            ));
        }
    }
}

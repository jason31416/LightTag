package ink.neokoni.lightTag.GUIs;

import cn.jason31416.planetlib.gui.GUI;
import cn.jason31416.planetlib.gui.GUIBuilder;
import cn.jason31416.planetlib.gui.itemgroup.InventoryList;
import cn.jason31416.planetlib.message.Message;
import cn.jason31416.planetlib.wrapper.SimpleItemStack;
import cn.jason31416.planetlib.wrapper.SimplePlayer;
import ink.neokoni.lightTag.Commands.Functions.SetTag;
import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.DataStorage.Tags;
import ink.neokoni.lightTag.Utils.TagUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class SetTagGUI {
    public static GUI build(Player pl){
        YamlConfiguration data = PlayerDatas.getPlayerData();
        List<Integer> owns = data.getIntegerList(pl.getUniqueId()+".owns");
        return new GUIBuilder("set-tag")
                .name(Message.of("<yellow>设置称号"))
                .shape("a a a a a a a a a")
                .shape("a a a a a a a a a")
                .shape("a a a a a a a a a")
                .shape("a a a a a a a a a")
                .shape("a a a a a a a a a")
                .shape("p x x x x x x x n")
                .setItem("x", new SimpleItemStack().setName("").setMaterial(Material.GRAY_STAINED_GLASS))
                .setItem("a",
                        GUIBuilder.ListedItem.builder().id("list").items(
                                owns.stream()
                                        .map(i -> new InventoryList.ListItem(
                                                ()->{
                                                    Component tagView = TagUtils.getViewById(i);
                                                    String type = Tags.getTags().getString(i+".type");
                                                    boolean isAnimation = (type != null && type.equals("ANIMATION"));
                                                    return new SimpleItemStack()
                                                            .setMaterial(Material.NAME_TAG)
                                                            .setName(Message.of(tagView))
                                                            .setGlow(isAnimation)
                                                            .setLore(List.of(
                                                                    "称号: "+Message.of(tagView).toFormatted(),
                                                                    isAnimation?"<yellow>(动态称号)":"<red>(静态称号)",
                                                                    "ID: "+i,
                                                                    "",
                                                                    "点击使用"
                                                            ));
                                                },
                                                List.of(inv->{
                                                    new SetTag(pl, i);
                                                    inv.getGui().close();
                                                })
                                        )).toList())
                )
                .build();
    }
}

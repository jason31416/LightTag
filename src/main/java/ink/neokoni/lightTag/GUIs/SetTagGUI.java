package ink.neokoni.lightTag.GUIs;

import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.DataStorage.Tags;
import ink.neokoni.lightTag.GUIs.Base.ChestMenu;
import ink.neokoni.lightTag.LightTag;
import ink.neokoni.lightTag.Utils.TagUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SetTagGUI {
    private Player player;
    private ChestMenu menu;
    public SetTagGUI(Player player) {
        this.player = player;
        menu = new ChestMenu(6);

        YamlConfiguration data = PlayerDatas.getPlayerData();
//        int using = data.getInt(this.player.getUniqueId()+".using");
        List<Integer> owns = data.getIntegerList(this.player.getUniqueId()+".owns");

        for (int i : owns) {
            ItemStack tagItem = new ItemStack(Material.NAME_TAG);
            Component tagView = TagUtils.getViewById(i);
            String type = Tags.getTags().getString(i+".type");
            boolean isAnimation = (type != null && type.equals("ANIMATION"));

            ItemMeta meta = tagItem.getItemMeta();
            meta.displayName(tagView);
            if (isAnimation)meta.setEnchantmentGlintOverride(true);
            meta.lore(List.of(
                    MiniMessage.miniMessage().deserialize("称号: ").append(tagView),
                    MiniMessage.miniMessage().deserialize(""),
                    MiniMessage.miniMessage().deserialize("ID: "+i),
                    MiniMessage.miniMessage().deserialize(""),
                    MiniMessage.miniMessage().deserialize("点击使用")
            ));
            meta.setCustomModelData(i + LightTag.tagN);

            tagItem.setItemMeta(meta);

            menu.put(tagItem);
        }

        menu.setTitle("<yellow>设置称号");
    }

    public void open() {
        menu.open(player);
    }
}

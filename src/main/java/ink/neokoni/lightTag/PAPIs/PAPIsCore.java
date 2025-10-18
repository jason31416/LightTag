package ink.neokoni.lightTag.PAPIs;

import ink.neokoni.lightTag.LightTag;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PAPIsCore extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "lighttag";
    }

    @Override
    public @NotNull String getAuthor() {
        String authors = "";
        for (String author : LightTag.getInstance().getPluginMeta().getAuthors()) {
            authors = author + " " + authors;
        }
        return authors;
    }

    @Override
    public @NotNull String getVersion() {
        return LightTag.getInstance().getPluginMeta().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        Component a = MiniMessage.miniMessage().deserialize("<gradient:#5e4fa2:#f79459>||||||||||||||||||||||||</gradient>");
        String de = LegacyComponentSerializer.legacySection().serialize(a);
        Bukkit.getConsoleSender().sendMessage("彩色文字测试：" + de);
        switch (params) {
            default -> {
                return de;
            }
        }
    }
}

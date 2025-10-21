package ink.neokoni.lightTag.PAPIs;

import ink.neokoni.lightTag.LightTag;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
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
        switch (params) {
            case "using_static": {
                return new PlayerUsingStaticTagPAPI(player).get();
            }
            case "using": {
                return new PlayerUsingDynamicTagPAPI(player).get();
            }

        }
        return null;
    }
}

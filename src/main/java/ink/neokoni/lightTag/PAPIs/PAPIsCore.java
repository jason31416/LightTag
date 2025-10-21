package ink.neokoni.lightTag.PAPIs;

import ink.neokoni.lightTag.LightTag;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PAPIsCore extends PlaceholderExpansion {
    private static Map<Player, PlayerUsingDynamicTagPAPI> cache = new ConcurrentHashMap<>();

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
                if (!cache.containsKey(player)) {
                    PlayerUsingDynamicTagPAPI dynamicTagPAPI = new PlayerUsingDynamicTagPAPI(player);
                    cache.put(player, dynamicTagPAPI);
                    return dynamicTagPAPI.get();
                }

                return cache.get(player).get();
            }

        }
        return null;
    }

    public static void clearCache() {
        cache = new ConcurrentHashMap<>();
    }

    public static void clear(Player player) {
        cache.remove(player);
    }
}

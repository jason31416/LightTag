package ink.neokoni.lightTag.DataStorage;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Caches {
    public static final Map<Player, Inventory> menuCache = new ConcurrentHashMap<>();
}

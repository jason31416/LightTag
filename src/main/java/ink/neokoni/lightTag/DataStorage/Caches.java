package ink.neokoni.lightTag.DataStorage;

import ink.neokoni.lightTag.GUIs.SetTagGUI;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Caches {
    public static final Map<Inventory, SetTagGUI> setTagGUI = new ConcurrentHashMap<>();
}

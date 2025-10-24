package ink.neokoni.lightTag.Handler;

import ink.neokoni.lightTag.DataStorage.Caches;
import ink.neokoni.lightTag.LightTag;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import org.bukkit.inventory.Inventory;

public class TagsInventoryHandlers implements Listener {
    public TagsInventoryHandlers(LightTag plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onClickInventory(InventoryClickEvent e) {
        Inventory clicked = e.getClickedInventory();
        if (clicked==null) { // not click inside inventory
            return;
        }
        if (Caches.setTagGUI.containsKey(e.getClickedInventory())) {
            e.setCancelled(true);
            Caches.setTagGUI.get(e.getClickedInventory()).handleClick(e);
        }
    }

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent e) {
        Inventory closed = e.getInventory();

        Caches.setTagGUI.remove(closed);
    }
}

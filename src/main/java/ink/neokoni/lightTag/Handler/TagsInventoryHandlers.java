package ink.neokoni.lightTag.Handler;

import ink.neokoni.lightTag.Commands.Functions.SetTagCommand;
import ink.neokoni.lightTag.DataStorage.Caches;
import ink.neokoni.lightTag.LightTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TagsInventoryHandlers implements Listener {
    public TagsInventoryHandlers(LightTag plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onClickInventory(InventoryClickEvent e) {
        Bukkit.getLogger().warning("Click inv happened");
        Inventory clicked = e.getClickedInventory();
        if (clicked==null) { // not click inside inventory
            return;
        }

        if (!Caches.menuCache.containsValue(clicked)) { // not our opened inventory
            return;
        }
        e.setCancelled(true);
        Bukkit.getLogger().warning("canceled click!!!");

        Inventory inv = e.getInventory();
        HumanEntity human = e.getWhoClicked();
        Player player = Bukkit.getPlayer(human.getUniqueId());

        if (!Caches.menuCache.containsKey(player)) {
            return;
        }

        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem==null) {
            return;
        }

        if (!clickedItem.getItemMeta().hasCustomModelData()) {
            return;
        }
        int id = clickedItem.getItemMeta().getCustomModelData() - LightTag.tagN;

        new SetTagCommand(player, id);
        Caches.menuCache.remove(player);
        inv.close();
    }

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent e) {
        Inventory closed = e.getInventory();
        Bukkit.getLogger().warning("Closed Inv");
        if (!Caches.menuCache.containsValue(closed)) {
            return;
        }

        Player player = Bukkit.getPlayer(e.getPlayer().getUniqueId());
        Caches.menuCache.remove(player);
    }
}

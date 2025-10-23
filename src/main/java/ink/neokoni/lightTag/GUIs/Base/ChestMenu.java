package ink.neokoni.lightTag.GUIs.Base;

import ink.neokoni.lightTag.DataStorage.Caches;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;

public class ChestMenu {
    private Component title = Component.text("Untitled");
    private int size = 9;
    private ItemStack[] items = new ItemStack[size];
    private Inventory menu;

    public ChestMenu(int row) {
        size = row*9;
        items = new ItemStack[size];
    }

    public void setTitle(String title) {
        this.title = MiniMessage.miniMessage().deserialize(title);
    }

    public void put(ItemStack item, int index) {
        items[index] = item;
    }

    public void put(ItemStack... Items) {
        int cur_slot = 0;
        for (ItemStack item :Items) {
            while(items[cur_slot]!=null&&cur_slot<55) {
                cur_slot++;
            }
            Bukkit.getLogger().warning("putted "+cur_slot);
            items[cur_slot] = item;
            cur_slot++;
        }
    }

    public void open(Player player) {
        menu = Bukkit.createInventory(null, size, title);
        for (int i = 0; i < size; i++) {
            menu.setItem(i, items[i]);
        }

        player.openInventory(menu);
        Caches.menuCache.put(player, menu);
    }

    public Inventory getInv() {
        return menu;
    }
}

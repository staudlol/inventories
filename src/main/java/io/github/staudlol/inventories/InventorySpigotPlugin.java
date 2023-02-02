package io.github.staudlol.inventories;

import io.github.staudlol.inventories.inventory.InventoryHandler;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class InventorySpigotPlugin extends JavaPlugin {

    private static InventorySpigotPlugin instance;

    private InventoryHandler inventoryHandler;

    @Override
    public void onEnable() {
        instance = this;

        this.inventoryHandler = new InventoryHandler();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static InventorySpigotPlugin getInstance() {
        return instance;
    }
}

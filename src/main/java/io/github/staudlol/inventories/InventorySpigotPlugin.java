package io.github.staudlol.inventories;

import io.github.staudlol.inventories.command.CommandHandler;
import io.github.staudlol.inventories.inventory.InventoryHandler;
import io.github.staudlol.inventories.inventory.InventoryPlayer;
import lombok.Getter;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class InventorySpigotPlugin extends JavaPlugin {

    private static InventorySpigotPlugin instance;

    private CommandHandler commandHandler;
    private InventoryHandler inventoryHandler;

    @Override
    public void onEnable() {
        instance = this;

        ConfigurationSerialization.registerClass(InventoryPlayer.class);

        this.commandHandler = new CommandHandler();
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

package io.github.staudlol.inventories.inventory;

import io.github.staudlol.inventories.InventorySpigotPlugin;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

@Getter
public class InventoryHandler {

    private final HashMap<String, InventoryPlayer> inventoryPlayers = new HashMap<>();

    public InventoryHandler() {
        final FileConfiguration configuration = InventorySpigotPlugin.getInstance().getConfig();
        final ConfigurationSection section = configuration.getConfigurationSection("inventories");

        if (section != null) {
            for (String key : section.getKeys(false)) {
                final InventoryPlayer inventoryPlayer = (InventoryPlayer) section.get(key);

                this.inventoryPlayers.put(key, inventoryPlayer);
                System.out.println("Loaded " + this.inventoryPlayers.size() + " inventories.");
            }
        } else {
            System.out.println("No inventories found.");
        }
    }

    /**
     * Save the inventory of a {@link InventoryPlayer}
     *
     * @param inventoryPlayer the inventoryPlayer.
     */

    public void saveInventory(InventoryPlayer inventoryPlayer) {
        final FileConfiguration configuration = InventorySpigotPlugin.getInstance().getConfig();
        final ConfigurationSection section = configuration.getConfigurationSection("inventories");

        section.set(inventoryPlayer.getUniqueId().toString(), inventoryPlayer);
        InventorySpigotPlugin.getInstance().saveConfig();
    }
}
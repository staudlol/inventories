package io.github.staudlol.inventories.inventory;

import lombok.Data;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

@Data
public class InventoryPlayer implements ConfigurationSerializable {

    public final UUID uniqueId;

    public ItemStack[] contents = new ItemStack[36];
    public ItemStack[] armorContents = new ItemStack[4];

    /**
     * Constructor to create a new {@link InventoryPlayer
     *
     * @param uniqueId the unique identifier.
     */

    public InventoryPlayer(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}
